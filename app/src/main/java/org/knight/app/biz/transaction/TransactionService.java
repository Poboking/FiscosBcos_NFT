package org.knight.app.biz.transaction;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.convert.artwork.IssuedCollectionConvert;
import org.knight.app.biz.convert.transaction.CollectionGiveRecordConvert;
import org.knight.app.biz.convert.transaction.PayOrderConvert;
import org.knight.app.biz.exception.collection.*;
import org.knight.app.biz.exception.log.LogCreationFailedException;
import org.knight.app.biz.exception.member.MemberNotFoundException;
import org.knight.app.biz.exception.member.RealNameNotVerifiedException;
import org.knight.app.biz.exception.member.ReceiverNotFoundException;
import org.knight.app.biz.exception.transaction.*;
import org.knight.app.biz.transaction.bo.OrderBO;
import org.knight.app.biz.transaction.dto.giverecord.CollectionGiveRecordRespDTO;
import org.knight.app.biz.transaction.dto.issued.CastIssuedCollectionReqDTO;
import org.knight.app.biz.transaction.dto.member.ReceiverInfoRespDTO;
import org.knight.app.biz.transaction.dto.order.PayOrderRespDTO;
import org.knight.app.biz.transaction.dto.trade.TradeStatisticDayRespDTO;
import org.knight.app.biz.transaction.dto.trade.TradeStatisticRespDTO;
import org.knight.infrastructure.common.*;
import org.knight.infrastructure.dao.domain.*;
import org.knight.infrastructure.fisco.module.BlockChainNFT;
import org.knight.infrastructure.fisco.service.biz.ChainService;
import org.knight.infrastructure.repository.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
@Log4j2
public class TransactionService {

    private final PayOrderRepositoryImpl payOrderRepository;
    private final CollectionGiveRecordRepositoryImpl collectionGiveRecordRepository;
    private final MemberRepositoryImpl memberRepository;
    private final CreatorRepositoryImpl creatorRepository;
    private final CollectionRepositoryImpl collectionRepository;
    private final MemberBalanceChangeLogRepositoryImpl memberBCLogRepository;
    private final MemberHoldCollectionRepositoryImpl holdCollectionRepository;
    private final MemberResaleCollectionRepositoryImpl resaleCollectionRepository;
    private final IssuedCollectionRepositoryImpl issuedCollectionRepository;
    private final IssuedCollectionActionLogRepositoryImpl issuedCollectionActLogRepository;

    private final ChainService chainService;

    @Autowired
    public TransactionService(PayOrderRepositoryImpl payOrderRepository, CollectionGiveRecordRepositoryImpl collectionGiveRecordRepository, CollectionRepositoryImpl collectionRepository, MemberRepositoryImpl memberRepository, CreatorRepositoryImpl creatorRepository, MemberBalanceChangeLogRepositoryImpl memberBalanceChangeLogRepository, MemberHoldCollectionRepositoryImpl holdCollectionRepository, MemberResaleCollectionRepositoryImpl resaleCollectionRepository, IssuedCollectionRepositoryImpl issuedCollectionRepository, IssuedCollectionActionLogRepositoryImpl issuedCollectionActLogRepository, ChainService chainService) {
        this.payOrderRepository = payOrderRepository;
        this.collectionGiveRecordRepository = collectionGiveRecordRepository;
        this.collectionRepository = collectionRepository;
        this.memberRepository = memberRepository;
        this.creatorRepository = creatorRepository;
        this.memberBCLogRepository = memberBalanceChangeLogRepository;
        this.holdCollectionRepository = holdCollectionRepository;
        this.resaleCollectionRepository = resaleCollectionRepository;
        this.issuedCollectionRepository = issuedCollectionRepository;
        this.issuedCollectionActLogRepository = issuedCollectionActLogRepository;
        this.chainService = chainService;
    }

    public PageResult<PayOrderRespDTO> getMyPayOrder(long current, long pageSize, String status, String memberId) {
        IPage<PayOrderEntity> pageList = null;
        if (CharSequenceUtil.isBlank(status)) {
            pageList = payOrderRepository.getPayOrderPageListByMemberId(current, pageSize, memberId);
        } else {
            pageList = payOrderRepository.getPayOrderPageListByMemberIdAndStatus(current, pageSize, memberId, status);
        }
        List<PayOrderRespDTO> recordList = new ArrayList<>();
        pageList.getRecords().forEach(entity -> {
            PayOrderRespDTO respDTO = PayOrderConvert.INSTANCE.convertToDTO(entity);
            CollectionEntity collection = collectionRepository.getById(entity.getCollectionId());
            CreatorEntity creator = creatorRepository.getById(collection.getCreatorId());
            respDTO.setCommodityType(collection.getCommodityType());
            respDTO.setCollectionName(collection.getName());
            respDTO.setCollectionCover(collection.getCover());
            respDTO.setCreatorName(creator.getName());

            if (respDTO.getState().equals(NftConstants.支付订单状态_已付款)) {
                respDTO.setStateName("已付款");
            } else if (respDTO.getState().equals(NftConstants.支付订单状态_待付款)) {
                respDTO.setStateName("待付款");
            } else if (respDTO.getState().equals(NftConstants.支付订单状态_已取消)) {
                respDTO.setStateName("已取消");
            } else {
                respDTO.setStateName("未知");
            }
//            if (respDTO.getState().equals(NftConstants.支付订单状态_已退款)) {
//                respDTO.setStateName("已退款");
//            }
            recordList.add(respDTO);
        });
        return PageResult.convertFor(pageList, pageSize, recordList);
    }

    public PageResult<CollectionGiveRecordRespDTO> getMyGiveRecord(long current, long pageSize, String memberId, String giveDirection) {
        IPage<CollectionGiveRecordEntity> pageList = null;
        if (giveDirection != null && NftConstants.DIRECTION_FROM.equals(giveDirection)) {
            pageList = collectionGiveRecordRepository.getPageListByGiveFromId(current, pageSize, memberId);
        } else if (giveDirection != null && NftConstants.DIRECTION_TO.equals(giveDirection)) {
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

    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> resaleCollectionCreateOrder(String resaleCollectionId, int collectionSerialNumber, String memberId) {
        CollectionEntity collectionEntity = collectionRepository.getById(resaleCollectionId);
        String issuedCollectionId = issuedCollectionRepository.getIssuedIdByCollectionIdAndSerialNumber(resaleCollectionId, collectionSerialNumber);
        if (Objects.isNull(collectionEntity)) {
            throw new CollectionNotFoundException("不存在ID为" + resaleCollectionId + "藏品");
        }
        if (CharSequenceUtil.isBlank(issuedCollectionId)) {
            throw new IssuedCollectionNotFoundException("不存在ID为" + issuedCollectionId + "发行藏品");
        }
        Double amount = collectionEntity.getPrice();
        PayOrderEntity entity = quickBuildPayOrder(NftConstants.支付订单业务模式_平台自营, amount, resaleCollectionId, issuedCollectionId, memberId);
        if (Boolean.FALSE.equals(payOrderRepository.save(entity))) {
            throw new OrderCreationFailedException("生成订单失败");
        }
        return Map.of("orderId", entity.getId());
    }


    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> latestCollectionCreateOrder(String collectionId, String memberId) {
        if (memberRepository.checkRealName(memberId)) {
            throw new RealNameNotVerifiedException("未实名认证");
        }
        CollectionEntity collectionEntity = collectionRepository.getById(collectionId);
        PayOrderEntity orderEntity = payOrderRepository.checkExistByCollectionIdAndMemberId(collectionId, memberId);
        if (orderEntity != null) {
            return Map.of("orderId", orderEntity.getId());
        }
        if (Objects.isNull(collectionEntity)) {
            throw new CollectionNotFoundException("不存在ID为" + collectionId + "藏品");
        }
        // TODO: 2024/4/5 这里需要考虑分布式锁 或 多线程, 避免多线程访问出现异常 可以添加队列处理
        if (Boolean.FALSE.equals(collectionRepository.reduceStock(collectionId))) {
            throw new InsufficientStockException(CharSequenceUtil.format("[{}]库存不足", collectionId));
        }
        Integer collectionSerialNumber = collectionEntity.getQuantity() - (collectionEntity.getStock() - 1);
        String issuedCollectionId = issuedCollectionRepository.getIssuedIdByCollectionIdAndSerialNumber(collectionId, collectionSerialNumber);
        if (CharSequenceUtil.isBlank(issuedCollectionId)) {
//            throw new IssuedCollectionNotFoundException(CharSequenceUtil.format("DataError: 不存在ID为[{}]发行藏品", issuedCollectionId));
            // TODO: 2024/4/8 这里简单处理为, 用户购买时, 方才进行发行藏品上链, 但是这样会导致用户购买时, 会有一定的延迟
            //Transaction注解默认本方法中进行事务管理, 而Async注解会通过新建线程来实现异步操作, 会导致事务失效
            castIssuedCollection(collectionId, memberId, collectionSerialNumber, collectionEntity.getQuantity());
        }
        if (issuedCollectionActLogRepository.checkCollectionLock(issuedCollectionId)) {
            throw new IssuedCollectionLockException(CharSequenceUtil.format("多线程异常: [{}] 发行藏品已被锁定", issuedCollectionId));
        }
        Double amount = collectionEntity.getPrice();
        PayOrderEntity entity = quickBuildPayOrder(NftConstants.支付订单业务模式_平台自营, amount, collectionId, issuedCollectionId, memberId);
        if (Boolean.FALSE.equals(issuedCollectionActLogRepository.lockCollection("平台自营", issuedCollectionId, memberId))) {
            throw new IssuedCollectionActLogUpdateOrAddException("更新或添加发行藏品操作日志失败");
        }
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
//        if (Boolean.FALSE.equals(collectionRepository.reduceStock(entity.getCollectionId()))) {
//            throw new InsufficientStockException(CharSequenceUtil.format("[{}]库存不足", entity.getCollectionId()));
//        }
        if (Boolean.FALSE.equals(memberRepository.reduceBalance(memberId, entity.getAmount()))) {
            throw new InsufficientBalanceException(CharSequenceUtil.format("[{}]余额不足", memberId));
        }
        if (Boolean.FALSE.equals(payOrderRepository.updateStateById(entity.getId(), NftConstants.支付订单状态_已付款, now))) {
            throw new OrderPaymentFailedException(CharSequenceUtil.format("[{}]订单支付失败", orderId));
        }
        if (Boolean.FALSE.equals(holdCollectionRepository.increaseByPurchase(
                IdUtils.snowFlakeId(),
                entity.getCollectionId(),
                entity.getIssuedCollectionId(),
                memberId,
                entity.getAmount(),
                // TODO: 2024/4/5 这里需要获取区块链交易的hash地址 或 唯一hash值 
                null
                , now))) {
            throw new HoldCollectionCreationFailedException(CharSequenceUtil.format("[{}]持有藏品记录创建失败", memberId));
        }
        if (Boolean.FALSE.equals(memberBCLogRepository.createLog(memberId,
                -entity.getAmount(),
                NftConstants.会员余额变动日志类型_购买藏品,
                entity.getOrderNo(),
                now))) {
            throw new LogCreationFailedException(CharSequenceUtil.format("[{}]流水日志记录失败", memberId));
        }

        return Map.of("result", true);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean collectionResale(String memberId, String holdCollectionId, double resalePrice) {
        if (CharSequenceUtil.isBlank(memberId) || CharSequenceUtil.isBlank(holdCollectionId)) {
            return false;
        }
        if (resalePrice < 0 || resalePrice > NftConstants.单笔销售最大价格) {
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

    public PayOrderEntity quickBuildPayOrder(String bizMode, Double amount, String collectionId, String issuedCollectionId, String memberId) {
        LocalDateTime now = LocalDateTime.now();
        return PayOrderEntity.builder()
                .id(IdUtils.snowFlakeId())
                .issuedCollectionId(issuedCollectionId)
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

    public PayOrderRespDTO getMyPayOrderDetail(String orderId, String memberId) {
        PayOrderEntity entity = payOrderRepository.getById(orderId);
        if (entity == null || !entity.getMemberId().equals(memberId)) {
            throw new OrderNotFoundException(CharSequenceUtil.format("[{}]订单不存在", orderId));
        }
        CollectionEntity collection = collectionRepository.getById(entity.getCollectionId());
        CreatorEntity creator = creatorRepository.getById(collection.getCreatorId());
        PayOrderRespDTO respDTO = PayOrderRespDTO.builder()
                .id(entity.getId())
                .orderNo(entity.getOrderNo())
                .amount(entity.getAmount())
                .state(entity.getState())
                .collectionCover(collection.getCover())
                .collectionName(collection.getName())
                .creatorName(creator.getName())
                .commodityType(collection.getCommodityType())
                .createTime(entity.getCreateTime().toLocalDateTime())
                .build();
        if (!Objects.isNull(entity.getPaidTime())) {
            respDTO.setPaidTime(entity.getPaidTime().toLocalDateTime());
        }
        if (respDTO.getState().equals(NftConstants.支付订单状态_待付款)) {
            respDTO.setStateName("待付款");
        } else if (respDTO.getState().equals(NftConstants.支付订单状态_已付款)) {
            respDTO.setStateName("已付款");
        } else if (respDTO.getState().equals(NftConstants.支付订单状态_已取消)) {
            respDTO.setStateName("已取消");
        } else {
            respDTO.setStateName("未知");
        }
        return respDTO;
    }


    public ReceiverInfoRespDTO getCollectionReceiverInfo(String giveToAccount) {
        try {
            MemberEntity member = memberRepository.getOne(new QueryWrapper<MemberEntity>()
                    .or(item -> item.eq("mobile", giveToAccount)
                            .or().eq("block_chain_addr", giveToAccount)));
            if (member == null) {
                throw new MemberNotFoundException("收款方不存在");
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
            throw new MemberNotFoundException("收款方不存在");
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
        try {
            payOrderRepository.updateStateById(orderId, NftConstants.支付订单状态_已取消, Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)));
        } catch (Exception e) {
            throw new OrderCancellationFailedException(CharSequenceUtil.format("{}: 订单取消失败", orderId));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean cancelOrder(String orderId, String memberId) {
        if (CharSequenceUtil.isBlank(orderId) || CharSequenceUtil.isBlank(memberId)) {
            return false;
        }
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
        if (Boolean.FALSE.equals(payOrderRepository.updateStateById(entity.getId(), NftConstants.支付订单状态_已取消, Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT))))) {
            throw new OrderCancellationFailedException(CharSequenceUtil.format("[{}]订单取消失败", orderId));
        }
        if (Boolean.FALSE.equals(collectionRepository.increaseStock(entity.getCollectionId()))) {
            throw new OrderCancellationFailedException(CharSequenceUtil.format("[{}]订单取消失败", orderId));
        }
        return true;
    }

    // TODO: 2024/4/6 用户购买转售藏品之后, 出售者余额增加, 购买者余额减少, 出售者藏品状态变为已售出, 购买者藏品状态变为持有中 - 待实现
    // TODO: 2024/4/6 出售成功需要将持有藏品表中软删除用户持有状态为售出的藏品 - 待实现
    @Transactional(rollbackFor = Exception.class)
    public Boolean cancelResale(String memberId, String resaleCollectionId) {
        String format = LocalDateTime.now().format(NftConstants.DATE_FORMAT);
        Timestamp now = cn.hutool.core.date.DateUtil.parse(format, NftConstants.DATE_FORMAT).toTimestamp();
        if (CharSequenceUtil.isBlank(memberId) || Boolean.FALSE.equals(memberRepository.checkExist(memberId))) {
            throw new MemberNotFoundException("用户不存在");
        }
        if (CharSequenceUtil.isBlank(resaleCollectionId) || Boolean.FALSE.equals(resaleCollectionRepository.checkExist(resaleCollectionId))) {
            throw new MemberNotMatchException("藏品不存在");
        }
        if (Boolean.FALSE.equals(resaleCollectionRepository.checkExist(resaleCollectionId, memberId))) {
            throw new MemberNotMatchException("藏品不属于当前用户");
        }
        if (Boolean.FALSE.equals(resaleCollectionRepository.updateStateById(resaleCollectionId, NftConstants.转售的藏品状态_已取消, now))) {
            throw new ResaleCancellationFailedException("藏品取消转售失败");
        }
        return true;
    }

    @Async
    public String castIssuedCollection(String collectionId, String memberId, Integer serialNumber, Integer quantity) {
        try {
            CastIssuedCollectionReqDTO reqDTO = CastIssuedCollectionReqDTO.builder()
                    .id(IdUtils.snowFlakeId())
                    .collectionId(collectionId)
                    .collectionSerialNumber(serialNumber)
                    .issueTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)))
                    .build();
            issuedCollectionRepository.save(IssuedCollectionConvert.INSTANCE.convertToEntity(reqDTO));
            CollectionEntity collection = collectionRepository.getById(collectionId);
            BlockChainNFT nftEntity = chainService.issuedCollection(collection.getName(), collection.getCreatorId(), reqDTO.getId(), serialNumber, quantity);
            // TODO: 2024/4/8  待实现
            if (nftEntity != null || !CharSequenceUtil.isBlank(nftEntity.getUniqueId())) {
                issuedCollectionRepository.update(new UpdateWrapper<IssuedCollectionEntity>()
                        .eq("id", reqDTO.getId())
                        .set("sync_chain_time", Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)))
                        .set("unique_id",nftEntity.getUniqueId()));
                return nftEntity.getUniqueId();
            }
        } catch (Exception e) {
            throw new IssuedCollectionCastFailedException(CharSequenceUtil.format("BlockError: {}发行藏品上链失败{}", collectionId, e.getMessage()));
        }
        return null;
    }
}
