package org.sziit.app.biz.transaction;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sziit.app.biz.convert.transaction.CollectionGiveRecordConvert;
import org.sziit.app.biz.convert.transaction.PayOrderConvert;
import org.sziit.app.biz.exception.BizException;
import org.sziit.app.biz.transaction.dto.giverecord.CollectionGiveRecordRespDTO;
import org.sziit.app.biz.transaction.dto.order.PayOrderRespDTO;
import org.sziit.app.biz.transaction.dto.trade.TradeStatisticDayRespDTO;
import org.sziit.app.biz.transaction.dto.trade.TradeStatisticRespDTO;
import org.sziit.infrastructure.common.*;
import org.sziit.infrastructure.dao.domain.CollectionEntity;
import org.sziit.infrastructure.dao.domain.CollectionGiveRecordEntity;
import org.sziit.infrastructure.dao.domain.PayOrderEntity;
import org.sziit.infrastructure.repository.impl.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 23:40
 */
@Service
public class TransactionService {

    private PayOrderRepositoryImpl payOrderRepository;
    private CollectionGiveRecordRepositoryImpl collectionGiveRecordRepository;
    private MemberRepositoryImpl memberRepository;
    private CollectionRepositoryImpl collectionRepository;
    private final MemberBalanceChangeLogRepositoryImpl memberBCLogRepository;

    @Autowired
    public TransactionService(PayOrderRepositoryImpl payOrderRepository, CollectionGiveRecordRepositoryImpl collectionGiveRecordRepository, CollectionRepositoryImpl collectionRepository, MemberRepositoryImpl memberRepository, MemberBalanceChangeLogRepositoryImpl memberBalanceChangeLogRepository) {
        this.payOrderRepository = payOrderRepository;
        this.collectionGiveRecordRepository = collectionGiveRecordRepository;
        this.collectionRepository = collectionRepository;
        this.memberRepository = memberRepository;
        this.memberBCLogRepository = memberBalanceChangeLogRepository;
    }

    public PageResult<PayOrderRespDTO> getMyPayOrder(long current, long pageSize, String status, String memberId) {
        IPage<PayOrderEntity> pageList = null;
        if (status == null) {
            pageList = payOrderRepository.getPayOrderPageListByMemberId(current, pageSize, memberId);
        } else {
            pageList = payOrderRepository.getPayOrderPageListByMemberIdAndStatus(current, pageSize, memberId, status);
        }
        List<PayOrderRespDTO> recordList = new ArrayList<>();
        pageList.getRecords().forEach(payOrderEntity -> {
            recordList.add(PayOrderConvert.INSTANCE.convertToDTO(payOrderEntity));
        });
        return PageResult.convertFor(pageList, pageSize, recordList);
    }

    public PageResult<CollectionGiveRecordRespDTO> getMyGiveRecord(long current, long pageSize, String memberId, String giveDirection) {
        IPage<CollectionGiveRecordEntity> pageList = null;
        if (giveDirection != null && giveDirection.equals("from")) {
            pageList = collectionGiveRecordRepository.getPageListByGiveFromId(current, pageSize, memberId);
        }
        if (giveDirection != null && giveDirection.equals("to")) {
            pageList = collectionGiveRecordRepository.getPageListByGiveToId(current, pageSize, memberId);
        } else {
            pageList = collectionGiveRecordRepository.getPageListByGiveToIdOrGiveFormId(current, pageSize, memberId, memberId);
        }
        List<CollectionGiveRecordRespDTO> recordList = new ArrayList<>();
        pageList.getRecords().forEach(collectionGiveRecordEntity -> {
            recordList.add(CollectionGiveRecordConvert.INSTANCE.convertToDTO(collectionGiveRecordEntity));
        });

        return PageResult.convertFor(pageList, pageSize, recordList);
    }

    public Map<String, String> latestCollectionCreateOrder(String collectionId, String memberId) {
        CollectionEntity collectionEntity = collectionRepository.getById(collectionId);
        if (Objects.isNull(collectionEntity)) {
            throw new BizException("不存在ID为" + collectionId + "藏品");
        }
        Double amount = collectionEntity.getPrice();
        PayOrderEntity entity = quickBuildPayOrder(NftConstants.支付订单业务模式_平台自营, amount, collectionId, memberId);
        if (Boolean.FALSE.equals(payOrderRepository.save(entity))) {
            throw new BizException("生成订单失败");
        }
        return Map.of("orderId", entity.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Boolean> confirmPay(String orderId, String memberId) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT));
        PayOrderEntity entity = payOrderRepository.getById(orderId);
        if (entity == null || !entity.getMemberId().equals(memberId)) {
            throw new BizException(CharSequenceUtil.format("[{}]订单不存在", orderId));
        }
        if (entity.getState().equals(NftConstants.支付订单状态_已付款)) {
            throw new BizException(CharSequenceUtil.format("[{}]订单已支付", orderId));
        }
        if (entity.getState().equals(NftConstants.支付订单状态_已取消)) {
            throw new BizException(CharSequenceUtil.format("[{}]订单取消", orderId));
        }
        if (entity.getOrderDeadline().before(now)) {
            throw new BizException(CharSequenceUtil.format("[{}]订单已过期", orderId));
        }
        if (Boolean.FALSE.equals(collectionRepository.reduceStock(entity.getCollectionId()))) {
            throw new BizException(CharSequenceUtil.format("[{}]库存不足", entity.getCollectionId()));
        }
        if (Boolean.FALSE.equals(memberRepository.reduceBalance(memberId, entity.getAmount()))) {
            throw new BizException(CharSequenceUtil.format("[{}]余额不足", memberId));
        }
        if (Boolean.FALSE.equals(payOrderRepository.updateStateById(entity.getId(), NftConstants.支付订单状态_已付款, now))) {
            throw new BizException(CharSequenceUtil.format("[{}]订单支付失败", orderId));
        }
        if (Boolean.FALSE.equals(memberBCLogRepository.createLog(
                memberId,
                entity.getAmount(),
                NftConstants.会员余额变动日志类型_购买藏品,
                entity.getOrderNo(),
                now))) {
            throw new BizException(CharSequenceUtil.format("[{}]流水日志记录失败", memberId));
        }
        return Map.of("result", true);
    }

    public PayOrderEntity quickBuildPayOrder(String bizMode, Double amount, String collectionId, String memberId) {
        LocalDateTime now = LocalDateTime.now();
        return PayOrderEntity.builder()
                .id(IdUtils.snowFlakeId())
                .collectionId(collectionId)
                .memberId(memberId)
                .orderNo(OrderNoUtil.generateOrderNo())
                .amount(amount)
                .state(NftConstants.支付订单状态_待付款)
                .bizMode(bizMode)
                .bizType(NftConstants.会员余额变动日志类型_购买藏品)
                .createTime(Timestamp.valueOf(now.format(NftConstants.DATE_FORMAT)))
                .orderDeadline(Timestamp.valueOf(now.plusMinutes(NftConstants.支付订单有效期).format(NftConstants.DATE_FORMAT)))
                .build();
    }

    public List<TradeStatisticDayRespDTO> getEverydayTradeData(String bizMode, String startDate, String endDate) {
        List<String> dateRange = DateUtil.generateDateRange(startDate, endDate);
        List<TradeStatisticDayRespDTO> result = new ArrayList<>();
        if (dateRange == null) {
            return result;
        }
        dateRange.forEach(date -> {
            result.add(TradeStatisticDayRespDTO.builder()
                    .everyday(date)
                    .successAmount(payOrderRepository.getSuccessAmountByDate(bizMode, date))
                    .successCount(payOrderRepository.getSuccessCountByDate(bizMode, date))
                    .build());
        });
        return result;
    }

    public TradeStatisticRespDTO getTradeStatisticData(String bizMode) {
        return TradeStatisticRespDTO.builder()
                .totalAmount(payOrderRepository.getSuccessAmountByDate(bizMode, null))
                .todayAmount(payOrderRepository.getSuccessAmountByDate(bizMode, DateUtil.getToday()))
                .todayCount(payOrderRepository.getSuccessCountByDate(bizMode, null))
                .yesterdayAmount(payOrderRepository.getSuccessAmountByDate(bizMode, DateUtil.getYesterday()))
                .build();
    }
}
