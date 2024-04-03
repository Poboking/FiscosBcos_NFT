package org.knight.app.biz.transaction;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.convert.transaction.CollectionGiveRecordConvert;
import org.knight.app.biz.convert.transaction.PayOrderConvert;
import org.knight.app.biz.exception.collection.CollectionAlreadySoldException;
import org.knight.app.biz.exception.collection.CollectionGiveFailedException;
import org.knight.app.biz.exception.collection.CollectionNotFoundException;
import org.knight.app.biz.exception.collection.InvalidCollectionPriceException;
import org.knight.app.biz.exception.log.LogCreationFailedException;
import org.knight.app.biz.exception.member.MemberNoFoundException;
import org.knight.app.biz.exception.member.ReceiverNotFoundException;
import org.knight.app.biz.exception.transaction.*;
import org.knight.app.biz.transaction.bo.OrderBO;
import org.knight.app.biz.transaction.dto.giverecord.CollectionGiveRecordRespDTO;
import org.knight.app.biz.transaction.dto.member.ReceiverInfoRespDTO;
import org.knight.app.biz.transaction.dto.order.PayOrderRespDTO;
import org.knight.app.biz.transaction.dto.trade.TradeStatisticDayRespDTO;
import org.knight.app.biz.transaction.dto.trade.TradeStatisticRespDTO;
import org.knight.infrastructure.common.*;
import org.knight.infrastructure.dao.domain.*;
import org.knight.infrastructure.repository.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final PayOrderRepositoryImpl payOrderRepository;
    private final CollectionGiveRecordRepositoryImpl collectionGiveRecordRepository;
    private final MemberRepositoryImpl memberRepository;
    private final CollectionRepositoryImpl collectionRepository;
    private final MemberBalanceChangeLogRepositoryImpl memberBCLogRepository;
    private final MemberHoldCollectionRepositoryImpl holdCollectionRepository;

    private final MemberResaleCollectionRepositoryImpl resaleCollectionRepository;

    @Autowired
    public TransactionService(PayOrderRepositoryImpl payOrderRepository, CollectionGiveRecordRepositoryImpl collectionGiveRecordRepository, CollectionRepositoryImpl collectionRepository, MemberRepositoryImpl memberRepository, MemberBalanceChangeLogRepositoryImpl memberBalanceChangeLogRepository, MemberHoldCollectionRepositoryImpl holdCollectionRepository, MemberResaleCollectionRepositoryImpl resaleCollectionRepository) {
        this.payOrderRepository = payOrderRepository;
        this.collectionGiveRecordRepository = collectionGiveRecordRepository;
        this.collectionRepository = collectionRepository;
        this.memberRepository = memberRepository;
        this.memberBCLogRepository = memberBalanceChangeLogRepository;
        this.holdCollectionRepository = holdCollectionRepository;
        this.resaleCollectionRepository = resaleCollectionRepository;
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
        PayOrderEntity orderEntity = payOrderRepository.checkExistByCollectionIdAndMemberId(collectionId, memberId);
        if (orderEntity != null) {
            return Map.of("orderId", orderEntity.getId());
        }
        if (Objects.isNull(collectionEntity)) {
            throw new CollectionNotFoundException("不存在ID为" + collectionId + "藏品");
        }
        Double amount = collectionEntity.getPrice();
        PayOrderEntity entity = quickBuildPayOrder(NftConstants.支付订单业务模式_平台自营, amount, collectionId, memberId);
        if (Boolean.FALSE.equals(payOrderRepository.save(entity))) {
            throw new OrderCreationFailedException("生成订单失败");
        }
        return Map.of("orderId", entity.getId());
    }


    @Transactional(rollbackFor = Exception.class)
    public Map<String, Boolean> confirmPay(String orderId, String memberId) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT));
        PayOrderEntity entity = payOrderRepository.getById(orderId);
        if (entity == null || !entity.getMemberId().equals(memberId)) {
            throw new OrderNotFoundException(CharSequenceUtil.format("[{}]订单不存在", orderId));
        }
        if (entity.getState().equals(NftConstants.支付订单状态_已付款)) {
            throw new OrderAlreadyPaidException(CharSequenceUtil.format("[{}]订单已支付", orderId));
        }
        if (entity.getState().equals(NftConstants.支付订单状态_已取消)) {
            throw new OrderCancelledException(CharSequenceUtil.format("[{}]订单取消", orderId));
        }
        if (entity.getOrderDeadline().before(now)) {
            throw new OrderExpiredException(CharSequenceUtil.format("[{}]订单已过期", orderId));
        }
        if (Boolean.FALSE.equals(collectionRepository.reduceStock(entity.getCollectionId()))) {
            throw new InsufficientStockException(CharSequenceUtil.format("[{}]库存不足", entity.getCollectionId()));
        }
        if (Boolean.FALSE.equals(memberRepository.reduceBalance(memberId, entity.getAmount()))) {
            throw new InsufficientBalanceException(CharSequenceUtil.format("[{}]余额不足", memberId));
        }
        if (Boolean.FALSE.equals(payOrderRepository.updateStateById(entity.getId(), NftConstants.支付订单状态_已付款, now))) {
            throw new OrderPaymentFailedException(CharSequenceUtil.format("[{}]订单支付失败", orderId));
        }
        if (Boolean.FALSE.equals(memberBCLogRepository.createLog(
                memberId,
                entity.getAmount(),
                NftConstants.会员余额变动日志类型_购买藏品,
                entity.getOrderNo(),
                now))) {
            throw new LogCreationFailedException(CharSequenceUtil.format("[{}]流水日志记录失败", memberId));
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

    public Map<String, String> resaleCollectionCreateOrder(String resaleCollectionId, String memberId) {
        CollectionEntity collectionEntity = collectionRepository.getById(resaleCollectionId);
        if (Objects.isNull(collectionEntity)) {
            throw new CollectionNotFoundException("不存在ID为" + resaleCollectionId + "藏品");
        }
        Double amount = collectionEntity.getPrice();
        PayOrderEntity entity = quickBuildPayOrder(NftConstants.支付订单业务模式_平台自营, amount, resaleCollectionId, memberId);
        if (Boolean.FALSE.equals(payOrderRepository.save(entity))) {
            throw new OrderCreationFailedException("生成订单失败");
        }
        return Map.of("orderId", entity.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean collectionResale(String memberId, String holdCollectionId, double resalePrice) {
        if (CharSequenceUtil.isBlank(memberId) || CharSequenceUtil.isBlank(holdCollectionId)) {
            return false;
        }
        if (resalePrice < 0 || resalePrice > 1000000) {
            throw new InvalidCollectionPriceException("藏品价格不合法");
        }
        MemberHoldCollectionEntity holdCollection = holdCollectionRepository.checkExist(holdCollectionId, memberId);
        if (Objects.isNull(holdCollection)) {
            throw new CollectionNotFoundException("藏品不存在");
        }
        MemberResaleCollectionEntity resaleCollection = new MemberResaleCollectionEntity();
        resaleCollection.setId(IdUtils.snowFlakeId());
        resaleCollection.setMemberHoldCollectionId(holdCollectionId);
        resaleCollection.setMemberId(memberId);
        resaleCollection.setCollectionId(holdCollection.getCollectionId());
        resaleCollection.setCover(holdCollection.getCover());
        resaleCollection.setIssuedCollectionId(holdCollection.getIssuedCollectionId());
        resaleCollection.setVersion(1L);
        resaleCollection.setResalePrice(resalePrice);
        resaleCollection.setState(NftConstants.持有藏品状态_转售中);
        resaleCollection.setResaleTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)));
        return resaleCollectionRepository.save(resaleCollection) && holdCollectionRepository.update(new UpdateWrapper<MemberHoldCollectionEntity>()
                .eq("member_id", memberId)
                .eq("collection_id", holdCollectionId)
                .set("state", NftConstants.持有藏品状态_转售中));
    }

    public ReceiverInfoRespDTO getCollectionReceiverInfo(String giveToAccount) {
        try {
            MemberEntity member = memberRepository.getOne(new QueryWrapper<MemberEntity>()
                    .or(item -> item.eq("mobile", giveToAccount)
                            .or().eq("block_chain_addr", giveToAccount)));
            if (member == null) {
                throw new MemberNoFoundException("收款方不存在");
            }
            return ReceiverInfoRespDTO.builder()
                    .mobile(member.getMobile())
                    .blockChainAddr(member.getBlockChainAddr())
                    .build();
        } catch (Exception e) {
            throw new ReceiverNotFoundException("收款方不存在");
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public Boolean collectionGive(String memberId, String collectionId, String giveToAccount) {
        MemberEntity member = memberRepository.getOne(new QueryWrapper<MemberEntity>()
                .or(item -> item.eq("mobile", giveToAccount)
                        .or().eq("block_chain_addr", giveToAccount)));
        if (member == null) {
            throw new MemberNoFoundException("收款方不存在");
        }
        if (memberId.equals(member.getId())) {
            throw new CantGiveSelfException("无法转赠自己");
        }
        MemberHoldCollectionEntity holdCollection = holdCollectionRepository.getOne(new QueryWrapper<MemberHoldCollectionEntity>()
                .eq("member_id", memberId)
                .eq("collection_id", collectionId));
        if (holdCollection == null || Boolean.FALSE.equals(collectionRepository.checkExist(collectionId))) {
            throw new CollectionNotFoundException("藏品不存在");
        }
        if (holdCollection.getPrice() == null) {
            throw new InvalidCollectionPriceException("藏品价格为空");
        }
        if (holdCollection.getPrice() <= 0) {
            throw new InvalidCollectionPriceException("藏品价格不合法");
        }
        if (holdCollection.getState().equals(NftConstants.持有藏品状态_转售中)) {
            throw new CollectionAlreadySoldException("藏品已转售");
        }
        if (Boolean.FALSE.equals(holdCollectionRepository.update(new UpdateWrapper<MemberHoldCollectionEntity>()
                .eq("member_id", memberId)
                .eq("collection_id", collectionId)
                .set("status", NftConstants.持有藏品状态_转售中)))) {
            throw new CollectionGiveFailedException("藏品转售失败");
        }
        try {
            CollectionGiveRecordEntity entity = new CollectionGiveRecordEntity();
            entity.setId(IdUtils.snowFlakeId());
            entity.setGiveFromId(memberId);
            entity.setGiveToId(member.getId());
            entity.setHoldCollectionId(collectionId);
            entity.setGiveTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)));
            return collectionGiveRecordRepository.save(entity);
        } catch (Exception e) {
            throw new CollectionGiveFailedException("藏品转赠失败");
        }
    }

    public List<OrderBO> findExpiredOrders() {
        List<PayOrderEntity> expiredOrders = payOrderRepository.list(new QueryWrapper<PayOrderEntity>()
                .eq("state", NftConstants.支付订单状态_待付款)
                .lt("order_deadline", Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT))));
        List<OrderBO> result = new ArrayList<>();
        expiredOrders.forEach(payOrderEntity -> {
            result.add(OrderBO.builder()
                    .id(payOrderEntity.getId())
                    .createTime(payOrderEntity.getCreateTime().toLocalDateTime())
                    .build());
        });
        return result;
    }

    public void cancelOrder(String orderId) {
        payOrderRepository.updateStateById(
                orderId,
                NftConstants.支付订单状态_已取消,
                Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)));
    }
}
