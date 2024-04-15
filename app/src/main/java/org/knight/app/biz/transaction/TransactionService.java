package org.knight.app.biz.transaction;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.knight.app.biz.convert.artwork.IssuedCollectionConvert;
import org.knight.app.biz.convert.transaction.CollectionGiveRecordConvert;
import org.knight.app.biz.convert.transaction.PayOrderConvert;
import org.knight.app.biz.exception.block.BlockChainTransactionException;
import org.knight.app.biz.exception.block.ChainTransactionFailedException;
import org.knight.app.biz.exception.block.GainFromAddressException;
import org.knight.app.biz.exception.collection.*;
import org.knight.app.biz.exception.log.MemberBCLogCreationFailedException;
import org.knight.app.biz.exception.member.MemberNotFoundException;
import org.knight.app.biz.exception.member.RealNameNotVerifiedException;
import org.knight.app.biz.exception.member.ReceiverNotFoundException;
import org.knight.app.biz.exception.transaction.*;
import org.knight.app.biz.transaction.bo.OrderBO;
import org.knight.app.biz.transaction.dto.giverecord.CollectionGiveRecordRespDTO;
import org.knight.app.biz.transaction.dto.issued.CastIssuedCollectionReqDTO;
import org.knight.app.biz.transaction.dto.member.ReceiverInfoRespDTO;
import org.knight.app.biz.transaction.dto.order.MyPayOrderRespDTO;
import org.knight.app.biz.transaction.dto.order.PayOrderRespDTO;
import org.knight.app.biz.transaction.dto.trade.TradeStatisticDayRespDTO;
import org.knight.app.biz.transaction.dto.trade.TradeStatisticRespDTO;
import org.knight.infrastructure.common.*;
import org.knight.infrastructure.dao.domain.*;
import org.knight.infrastructure.fisco.config.BcosProperties;
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

    private final BcosProperties bcosProperties;
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
    private final IssuedCollectionActionLogRepositoryImpl issuedCollectionActLogRepo;
    private final MemberHoldCollectionRepositoryImpl memberHoldCollectionRepo;
    private final ChainService chainService;

    @Autowired
    public TransactionService(BcosProperties bcosProperties, PayOrderRepositoryImpl payOrderRepository,
                              CollectionGiveRecordRepositoryImpl collectionGiveRecordRepository,
                              CollectionRepositoryImpl collectionRepository, MemberRepositoryImpl memberRepository,
                              CreatorRepositoryImpl creatorRepository, MemberBalanceChangeLogRepositoryImpl memberBalanceChangeLogRepository,
                              MemberHoldCollectionRepositoryImpl holdCollectionRepository, MemberResaleCollectionRepositoryImpl resaleCollectionRepository,
                              IssuedCollectionRepositoryImpl issuedCollectionRepository, IssuedCollectionActionLogRepositoryImpl issuedCollectionActLogRepository,
                              IssuedCollectionActionLogRepositoryImpl issuedCollectionActLogRepo, MemberHoldCollectionRepositoryImpl memberHoldCollectionRepo, ChainService chainService) {
        this.bcosProperties = bcosProperties;
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
        this.issuedCollectionActLogRepo = issuedCollectionActLogRepo;
        this.memberHoldCollectionRepo = memberHoldCollectionRepo;
        this.chainService = chainService;
    }

    public PayOrderEntity quickBuildPayOrder(String bizMode, Double amount, String collectionId, String issuedCollectionId, String memberId) {
        LocalDateTime now = LocalDateTime.now();
        PayOrderEntity payOrderEntity = new PayOrderEntity();
        payOrderEntity.setId(IdUtils.snowFlakeId());
        payOrderEntity.setAmount(amount);
        payOrderEntity.setIssuedCollectionId(issuedCollectionId);
        payOrderEntity.setCollectionId(collectionId);
        payOrderEntity.setMemberId(memberId);
        payOrderEntity.setOrderNo(OrderNoUtil.generateOrderNo());
        payOrderEntity.setState(NftConstants.支付订单状态_待付款);
        payOrderEntity.setBizMode(bizMode);
        payOrderEntity.setBizType(NftConstants.会员余额变动日志类型_购买藏品);
        payOrderEntity.setCreateTime(Timestamp.valueOf(now.format(NftConstants.DATE_FORMAT)));
        payOrderEntity.setOrderDeadline(Timestamp.valueOf(now.plusMinutes(NftConstants.支付订单有效期).format(NftConstants.DATE_FORMAT)));
        return payOrderEntity;
    }

    /**
     * 快速构建持有藏品实体类 - 购买藏品 - 平台自营/二级市场
     *
     * @param memberId           持有者ID
     * @param collectionId       藏品ID
     * @param issuedCollectionId 发行藏品ID
     * @param payOrderId         支付订单ID
     * @param gainWay            获取方式
     * @param transactionHash    交易哈希
     * @return MemberHoldCollectionEntity
     */
    public MemberHoldCollectionEntity quickBuildHoldCollectionByPurchase(String memberId, String collectionId, String issuedCollectionId, String payOrderId, String gainWay, String transactionHash) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT));
        MemberHoldCollectionEntity memberHoldCollectionEntity = new MemberHoldCollectionEntity();
        CollectionEntity collectionEntity = collectionRepository.getById(collectionId);
        PayOrderEntity payOrderEntity = payOrderRepository.getById(payOrderId);
        memberHoldCollectionEntity.setId(IdUtils.snowFlakeId());
        memberHoldCollectionEntity.setName(collectionEntity.getName());
        memberHoldCollectionEntity.setCover(collectionEntity.getCover());
        memberHoldCollectionEntity.setCollectionId(collectionId);
        memberHoldCollectionEntity.setGainWay(gainWay);
        memberHoldCollectionEntity.setHoldTime(now);
        memberHoldCollectionEntity.setIssuedCollectionId(issuedCollectionId);
        memberHoldCollectionEntity.setMemberId(memberId);
        memberHoldCollectionEntity.setPrice(payOrderEntity.getAmount());
        memberHoldCollectionEntity.setState(NftConstants.持有藏品状态_持有中);
        memberHoldCollectionEntity.setSyncChainTime(collectionEntity.getSyncChainTime());
        memberHoldCollectionEntity.setTransactionHash(transactionHash);
        return memberHoldCollectionEntity;
    }


    /**
     * 快速构建持有藏品实体类 - 赠送藏品
     *
     * @param memberId           持有者ID
     * @param collectionId       藏品ID
     * @param issuedCollectionId 发行藏品ID
     * @param transactionHash    交易哈希
     * @return MemberHoldCollectionEntity
     */
    public MemberHoldCollectionEntity quickBuildHoldCollectionByGive(String memberId, String collectionId, String issuedCollectionId, String transactionHash) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT));
        MemberHoldCollectionEntity memberHoldCollectionEntity = new MemberHoldCollectionEntity();
        CollectionEntity collectionEntity = collectionRepository.getById(collectionId);
        memberHoldCollectionEntity.setId(IdUtils.snowFlakeId());
        memberHoldCollectionEntity.setName(collectionEntity.getName());
        memberHoldCollectionEntity.setCover(collectionEntity.getCover());
        memberHoldCollectionEntity.setCollectionId(collectionId);
        memberHoldCollectionEntity.setPrice(0.0);
        memberHoldCollectionEntity.setGainWay(NftConstants.藏品获取方式_赠送);
        memberHoldCollectionEntity.setHoldTime(now);
        memberHoldCollectionEntity.setIssuedCollectionId(issuedCollectionId);
        memberHoldCollectionEntity.setMemberId(memberId);
        memberHoldCollectionEntity.setState(NftConstants.持有藏品状态_持有中);
        memberHoldCollectionEntity.setSyncChainTime(collectionEntity.getSyncChainTime());
        memberHoldCollectionEntity.setTransactionHash(transactionHash);
        return memberHoldCollectionEntity;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> resaleCollectionCreateOrder(String resaleCollectionId, String memberId, Timestamp now) {
        if (!memberRepository.checkRealName(memberId)) {
            throw new RealNameNotVerifiedException("未实名认证");
        }
        MemberResaleCollectionEntity resaleCollection = resaleCollectionRepository.getById(resaleCollectionId);
        if (Objects.isNull(resaleCollection)) {
            throw new ResaleCollectionNotFoundException("不存在ID为" + resaleCollectionId + "转售藏品");
        }
        if (resaleCollection.getState().equals(NftConstants.转售的藏品状态_已卖出)) {
            throw new ResaleCollectionSoldOutException("转售藏品已售出");
        }
        if (resaleCollection.getState().equals(NftConstants.转售的藏品状态_已下架)) {
            throw new ResaleCollectionSoldOutException("转售藏品已下架");
        }
        if (resaleCollection.getState().equals(NftConstants.转售的藏品状态_已取消)) {
            throw new ResaleCollectionAlreadyCancelException("转售藏品已取消");
        }
        if (resaleCollection.getMemberId().equals(memberId)) {
            throw new ResaleCollectionOwnerException("不能购买自己的转售藏品");
        }
        CollectionEntity collectionEntity = collectionRepository.getById(resaleCollection.getCollectionId());
        if (Objects.isNull(collectionEntity) || CharSequenceUtil.isBlank(collectionEntity.getId())) {
            throw new CollectionNotFoundException("不存在ResaleCollectionID为" + resaleCollectionId + "转售藏品");
        }
        IssuedCollectionEntity issuedCollection = issuedCollectionRepository.getById(resaleCollection.getIssuedCollectionId());
        if (Objects.isNull(issuedCollection) || CharSequenceUtil.isBlank(issuedCollection.getId())) {
            throw new IssuedCollectionNotFoundException("[collectionId: " + collectionEntity.getId() + "] 不存在iD为" + resaleCollection.getIssuedCollectionId() + "发行藏品");
        }
        if (issuedCollectionActLogRepository.checkMultithreadingCollectionLock(resaleCollection.getIssuedCollectionId(), now)) {
            throw new IssuedCollectionMultithreadingLockException(CharSequenceUtil.format("多线程异常: [{}] 发行藏品已被锁定", resaleCollection.getIssuedCollectionId()));
        }
        Double amount = collectionEntity.getPrice();
        if (Boolean.FALSE.equals(issuedCollectionActLogRepository.lockCollection(NftConstants.发行藏品流转类型_二级市场_锁定藏品, issuedCollection.getId(), memberId))) {
            throw new IssuedCollectionActLogUpdateOrAddException("更新或添加发行藏品操作日志失败");
        }
        // TODO: 2024/4/13 resale订单确定之后, 需要将出售者的hold-collection状态更改
        if (Boolean.FALSE.equals(resaleCollectionRepository.lockResaleCollection(resaleCollectionId, memberId))) {
            throw new ResaleCollectionLockException("锁定转售藏品失败");
        }
        PayOrderEntity entity = quickBuildPayOrder(NftConstants.支付订单业务类型_二级市场, amount, collectionEntity.getId(), issuedCollection.getId(), memberId);
        if (Boolean.FALSE.equals(payOrderRepository.save(entity))) {
            throw new OrderCreationFailedException("生成订单失败");
        }
        return Map.of("orderId", entity.getId());
    }


    @Transactional(rollbackFor = Exception.class)
    public Map<String, String> latestCollectionCreateOrder(String collectionId, String memberId, Timestamp now) {
        if (!memberRepository.checkRealName(memberId)) {
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
        Integer collectionSerialNumber = Math.abs(collectionEntity.getQuantity() - collectionEntity.getStock() + 1);
        if (collectionSerialNumber < 1 || collectionSerialNumber > collectionEntity.getQuantity()) {
            throw new InsufficientStockException(CharSequenceUtil.format("[{}]库存异常", collectionId));
        }
        String issuedCollectionId = issuedCollectionRepository.getIssuedIdByCollectionIdAndSerialNumber(collectionId, collectionSerialNumber);
        if (issuedCollectionActLogRepository.checkMultithreadingCollectionLock(issuedCollectionId, now)) {
            throw new IssuedCollectionMultithreadingLockException(CharSequenceUtil.format("多线程异常: [{}] 发行藏品已被锁定", issuedCollectionId));
        }
        if (collectionSerialNumber > 0 && collectionSerialNumber <= collectionEntity.getQuantity() && CharSequenceUtil.isBlank(issuedCollectionId)) {
            log.error("DataError: 不存在序列号为{}发行藏品", collectionSerialNumber);
////            throw new IssuedCollectionNotFoundException(CharSequenceUtil.format("DataError: 不存在ID为[{}]发行藏品", issuedCollectionId));
//            // TODO: 2024/4/8 这里简单处理为, 用户购买时, 方才进行发行藏品上链, 但是这样会导致用户购买时, 会有一定的延迟
//            //Transaction注解默认本方法中进行事务管理, 而Async注解会通过新建线程来实现异步操作, 会导致事务失效
//            castIssuedCollection(collectionId, memberId, collectionSerialNumber, collectionEntity.getQuantity());
        }
        Double amount = collectionEntity.getPrice();
        PayOrderEntity entity = quickBuildPayOrder(NftConstants.支付订单业务类型_平台自营, amount, collectionId, issuedCollectionId, memberId);
        if (Boolean.FALSE.equals(issuedCollectionActLogRepository.lockCollection(NftConstants.发行藏品流转类型_平台自营_锁定藏品, issuedCollectionId, memberId))) {
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
        if (Boolean.FALSE.equals(memberRepository.reduceBalance(memberId, entity.getAmount()))) {
            throw new InsufficientBalanceException(CharSequenceUtil.format("[{}]余额不足", memberId));
        }
        if (Boolean.FALSE.equals(payOrderRepository.updateStateById(entity.getId(), NftConstants.支付订单状态_已付款, now))) {
            throw new OrderPaymentFailedException(CharSequenceUtil.format("[{}]订单支付失败", orderId));
        }

        //买家余额变动日志
        try {
            if (entity.getBizMode().equals(NftConstants.支付订单业务类型_二级市场)) {
                memberBCLogRepository.createLog(memberId, -Math.abs(entity.getAmount()), NftConstants.会员余额变动日志类型_购买转售的藏品, entity.getOrderNo(), now);
            } else if (entity.getBizMode().equals(NftConstants.支付订单业务类型_平台自营)) {
                memberBCLogRepository.createLog(memberId, -Math.abs(entity.getAmount()), NftConstants.会员余额变动日志类型_购买藏品, entity.getOrderNo(), now);
            } else {
                memberBCLogRepository.createLog(memberId, -Math.abs(entity.getAmount()), NftConstants.会员余额变动日志类型_系统, entity.getOrderNo(), now);
            }
        } catch (Exception e) {
            throw new MemberBCLogCreationFailedException("[生成会员余额变动日志失败]: " + e);
        }

        //卖家余额变动日志 & 卖家藏品状态更新
        String fromAddress = null;
        if (entity.getBizMode().equals(NftConstants.支付订单业务类型_二级市场)) {
            MemberResaleCollectionEntity resaleCollection = resaleCollectionRepository.getLastEntityByIssuedCollectionId(entity.getIssuedCollectionId());
            String fromMemberId = resaleCollection.getMemberId();
            fromAddress = memberRepository.getAddressById(fromMemberId);
            try {
                resaleCollectionRepository.updateStateById(resaleCollection.getId(), NftConstants.转售的藏品状态_已卖出, now);
            } catch (Exception e) {
                throw new ResaleCollectionUpdateFailedException(CharSequenceUtil.format("[{}]更新转售藏品状态失败: {}", resaleCollection.getId(), e));
            }
            try {
                memberHoldCollectionRepo.updateState(resaleCollection.getMemberHoldCollectionId(), NftConstants.持有藏品状态_已卖出, now);
            } catch (Exception e) {
                throw new MemberHoldCollectionUpdateFailedException(CharSequenceUtil.format("[{}]更新持有藏品状态失败: {}", resaleCollection.getMemberHoldCollectionId(), e));
            }
            try {
                memberBCLogRepository.createLog(fromMemberId, resaleCollection.getResalePrice(), NftConstants.会员余额变动日志类型_出售藏品, entity.getOrderNo(), now);
            } catch (Exception e) {
                throw new MemberBCLogCreationFailedException(CharSequenceUtil.format("[{}][生成转售藏品持有者会员余额变动日志失败]: {} ", fromMemberId, e));
            }
            try {
                memberRepository.increaseBalance(fromMemberId, resaleCollection.getResalePrice());
            } catch (Exception e) {
                throw new MemberBalanceUpdateFailedException(CharSequenceUtil.format("[{}][更新转售藏品持有者余额失败]: {} ", fromMemberId, e));
            }
        }


        if (entity.getBizMode().equals(NftConstants.支付订单业务类型_平台自营)) {
            fromAddress = bcosProperties.getDeployAddress();
        }
        if (CharSequenceUtil.isBlank(fromAddress)) {
            throw new GainFromAddressException("获取转出地址失败");
        }


        //区块链链上交易
        String toAddress = memberRepository.getAddressById(memberId);
        String transactionHash = chainService.transaction(fromAddress, toAddress, memberId);
        if (CharSequenceUtil.isBlank(transactionHash)) {
            throw new BlockChainTransactionException("区块链交易异常");
        }

        //购买者藏品持有
        if (Boolean.FALSE.equals(holdCollectionRepository.increaseByPurchase(
                IdUtils.snowFlakeId(),
                entity.getCollectionId(),
                entity.getIssuedCollectionId(),
                memberId,
                entity.getAmount(),
                // 这里需要获取区块链交易的hash地址 或 唯一hash值
                transactionHash
                , now))) {
            throw new HoldCollectionCreationFailedException(CharSequenceUtil.format("[{}]持有藏品创建失败", memberId));
        }
        String actionType = issuedCollectionActLogRepository.checkLockType(entity.getIssuedCollectionId(), memberId);
        try {
            if (NftConstants.发行藏品流转类型_平台自营_锁定藏品.equals(actionType)) {
                issuedCollectionActLogRepository.updateActionLog(NftConstants.发行藏品流转类型_二级市场, entity.getIssuedCollectionId(), memberId);
            }
            if (NftConstants.发行藏品流转类型_二级市场_锁定藏品.equals(actionType)) {
                issuedCollectionActLogRepository.updateActionLog(NftConstants.发行藏品流转类型_平台自营, entity.getIssuedCollectionId(), memberId);
            }
            issuedCollectionActLogRepository.updateActionLog(NftConstants.发行藏品流转类型_系统, entity.getIssuedCollectionId(), memberId);
        } catch (Exception e) {
            throw new IssuedCollectionActLogUpdateOrAddException("更新或添加发行藏品操作日志失败");
        }
        return Map.of("result", true);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean collectionResale(String memberId, String holdCollectionId, double resalePrice) throws Exception {
        if (CharSequenceUtil.isBlank(memberId) || CharSequenceUtil.isBlank(holdCollectionId)) {
            System.err.println("Test");
            return false;
        }
        if (resalePrice < 0 || resalePrice > NftConstants.单笔销售最大价格) {
            throw new InvalidCollectionPriceException("藏品价格不合法");
        }
        MemberHoldCollectionEntity holdCollection = holdCollectionRepository.checkExist(holdCollectionId, memberId);
        if (Objects.isNull(holdCollection)) {
            throw new CollectionNotFoundException("持有藏品不存在");
        }
        if (resaleCollectionRepository.checkResaleExistByIssuedCollection(holdCollection.getIssuedCollectionId(), memberId)) {
            throw new CollectionAlreadyResaleException("藏品已转售");
        }
        try {
            MemberResaleCollectionEntity resaleCollection = new MemberResaleCollectionEntity();
            resaleCollection.setId(IdUtils.snowFlakeId());
            resaleCollection.setMemberHoldCollectionId(holdCollectionId);
            resaleCollection.setMemberId(memberId);
            resaleCollection.setCollectionId(holdCollection.getCollectionId());
            resaleCollection.setCover(holdCollection.getCover());
            resaleCollection.setIssuedCollectionId(holdCollection.getIssuedCollectionId());
            resaleCollection.setVersion(1L);
            resaleCollection.setResalePrice(resalePrice);
            resaleCollection.setState(NftConstants.转售的藏品状态_已发布);
            resaleCollection.setResaleTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)));
            holdCollection.setState(NftConstants.持有藏品状态_转售中);
            return resaleCollectionRepository.saveOrUpdate(resaleCollection) && holdCollectionRepository.saveOrUpdate(holdCollection);
        } catch (Exception e) {
            throw new CollectionResaleException("藏品转售失败");
        }
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

    public MyPayOrderRespDTO getMyPayOrderDetail(String orderId, String memberId) {
        PayOrderEntity entity = payOrderRepository.getById(orderId);
        if (entity == null || !entity.getMemberId().equals(memberId)) {
            throw new OrderNotFoundException(CharSequenceUtil.format("[{}]订单不存在", orderId));
        }
        CollectionEntity collection = collectionRepository.getById(entity.getCollectionId());
        CreatorEntity creator = creatorRepository.getById(collection.getCreatorId());
        MyPayOrderRespDTO respDTO = MyPayOrderRespDTO.builder()
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
                    .and(item -> item.eq("mobile", giveToAccount)
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
    public Boolean collectionGive(String memberId, String issuedCollectionId, String giveToAccount) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT));
        MemberEntity receiver = memberRepository.getOne(new QueryWrapper<MemberEntity>()
                .and(item -> item.eq("mobile", giveToAccount)
                        .or().eq("block_chain_addr", giveToAccount)));
        if (receiver == null) {
            throw new MemberNotFoundException("收款方不存在");
        }
        if (memberId.equals(receiver.getId())) {
            throw new CantGiveSelfException("无法转赠自己");
        }
        MemberHoldCollectionEntity holdCollection = holdCollectionRepository.getOne(new QueryWrapper<MemberHoldCollectionEntity>()
                .eq("member_id", memberId)
                .eq("issued_collection_id", issuedCollectionId));
        if (holdCollection == null || Boolean.FALSE.equals(collectionRepository.checkExist(holdCollection.getCollectionId()))) {
            throw new CollectionNotFoundException("藏品不存在");
        }
        if (holdCollection.getState().equals(NftConstants.持有藏品状态_转售中)) {
            throw new CollectionAlreadySoldException("藏品已转售");
        }
        if (holdCollection.getState().equals(NftConstants.持有藏品状态_已转赠)) {
            throw new CollectionAlreadyGivenException("藏品已转赠");
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
        String transaction = "未知";
        try {
            String fromAddress = memberRepository.getAddressById(memberId);
            String toAddress = memberRepository.getAddressById(receiver.getId());
            transaction = chainService.transaction(fromAddress, toAddress, holdCollection.getIssuedCollectionId());
        } catch (Exception e) {
            throw new ChainTransactionFailedException("链上交易失败");
        }
        //修改持有者藏品状态
        if (Boolean.FALSE.equals(holdCollectionRepository.update(new UpdateWrapper<MemberHoldCollectionEntity>()
                .eq("member_id", memberId)
                .eq("collection_id", holdCollection.getCollectionId())
                .eq("issued_collection_id", issuedCollectionId)
                .set("state", NftConstants.持有藏品状态_已转赠)
                .set("lose_time", now)))) {
            throw new HoldCollectionUpdateFailedException("藏品转赠失败 - 更新持有者藏品状态失败");
        }
        //修改被转赠者藏品状态
        if (Boolean.FALSE.equals(holdCollectionRepository.save(quickBuildHoldCollectionByGive(
                receiver.getId(),
                holdCollection.getCollectionId(),
                holdCollection.getIssuedCollectionId(),
                transaction
        )))) {
            throw new HoldCollectionSaveFailedException("藏品转赠失败 - 更新被转赠者藏品状态失败");
        }
        try {
            issuedCollectionActLogRepo.lockCollection(NftConstants.发行藏品流转类型_转赠, issuedCollectionId, receiver.getId());
        } catch (Exception e) {
            throw new IssuedCollectionActLogUpdateOrAddException("发行藏品记录添加或更新失败");
        }
        try {
            CollectionGiveRecordEntity entity = new CollectionGiveRecordEntity();
            entity.setId(IdUtils.snowFlakeId());
            entity.setGiveFromId(memberId);
            entity.setGiveToId(receiver.getId());
            entity.setHoldCollectionId(holdCollection.getId());
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

    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(String orderId) {
        try {
            if (CharSequenceUtil.isBlank(orderId)) {
                return;
            }
            PayOrderEntity entity = payOrderRepository.getById(orderId);
            if (Objects.isNull(entity)) {
                return;
            }
            payOrderRepository.updateStateById(orderId, NftConstants.支付订单状态_已取消, Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)));
            issuedCollectionActLogRepository.unlockCollection(entity.getIssuedCollectionId(), entity.getMemberId());
            collectionRepository.increaseStock(entity.getCollectionId());
        } catch (Exception e) {
            throw new OrderCancellationFailedException(CharSequenceUtil.format("{}: 订单取消失败 || ", orderId) + e.getMessage());
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
        if (Boolean.FALSE.equals(issuedCollectionActLogRepository.unlockCollection(entity.getIssuedCollectionId(), memberId))) {
            throw new OrderCancellationFailedException(CharSequenceUtil.format("[{}]订单取消失败", orderId));
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
        MemberResaleCollectionEntity resaleCollection = resaleCollectionRepository.getById(resaleCollectionId);
        if (Boolean.FALSE.equals(resaleCollectionRepository.checkExist(resaleCollectionId, memberId))) {
            throw new MemberNotMatchException("藏品不属于当前用户");
        }
        if (Boolean.TRUE.equals(resaleCollectionRepository.checkState(resaleCollectionId, NftConstants.转售的藏品状态_已取消))) {
            throw new ResaleNotMatchException("藏品不是转售中状态: 转售的藏品状态_已取消");
        }
        if (Boolean.TRUE.equals(resaleCollectionRepository.checkState(resaleCollectionId, NftConstants.转售的藏品状态_已卖出))) {
            throw new ResaleNotMatchException("藏品不是转售中状态: 转售的藏品状态_已卖出");
        }
        if (Boolean.FALSE.equals(resaleCollectionRepository.updateStateById(resaleCollectionId, NftConstants.转售的藏品状态_已取消, now))) {
            throw new ResaleCancellationFailedException("藏品取消转售失败");
        }
        if (Boolean.FALSE.equals(memberHoldCollectionRepo.updateState(resaleCollection.getMemberHoldCollectionId(), NftConstants.持有藏品状态_持有中, now))) {
            throw new MemberHoldCollectionUpdateStateFailedException("藏品取消转售失败: 更新持有藏品状态失败");
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
                        .set("unique_id", nftEntity.getUniqueId()));
                return nftEntity.getUniqueId();
            }
        } catch (Exception e) {
            throw new IssuedCollectionCastFailedException(CharSequenceUtil.format("BlockError: {}发行藏品上链失败{}", collectionId, e.getMessage()));
        }
        return null;
    }

    public PageResult<MyPayOrderRespDTO> getMyPayOrder(long current, long pageSize, String status, String memberId) {
        PageInfo<PayOrderEntity> pageList = null;
        if (CharSequenceUtil.isBlank(status)) {
            pageList = payOrderRepository.getPayOrderPageListByMemberId(current, pageSize, memberId);
        } else {
            pageList = payOrderRepository.getPayOrderPageListByMemberIdAndStatus(current, pageSize, memberId, status);
        }
        List<MyPayOrderRespDTO> recordList = new ArrayList<>();
        pageList.getList().stream().filter(entity -> {
            return collectionRepository.checkExist(entity.getCollectionId());
        }).forEach(entity -> {
            MyPayOrderRespDTO respDTO = PayOrderConvert.INSTANCE.convertToMyRespDTO(entity);
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
            recordList.add(respDTO);
        });
        return PageResult.convertFor(pageList, pageSize, recordList);
    }


    public PageResult<CollectionGiveRecordRespDTO> getMyGiveRecord(long current, long pageSize, String memberId, String giveDirection) {
        PageInfo<CollectionGiveRecordEntity> pageList = null;
        String resultDirection;
        if (giveDirection != null && NftConstants.DIRECTION_FROM.equals(giveDirection)) {
            resultDirection = giveDirection;
            pageList = collectionGiveRecordRepository.getPageListByGiveFromId(current, pageSize, memberId);
        } else if (giveDirection != null && NftConstants.DIRECTION_TO.equals(giveDirection)) {
            resultDirection = giveDirection;
            pageList = collectionGiveRecordRepository.getPageListByGiveToId(current, pageSize, memberId);
        } else {
            resultDirection = "other";
            pageList = collectionGiveRecordRepository.getPageListByGiveToIdOrGiveFormId(current, pageSize, memberId, memberId);
        }
        List<CollectionGiveRecordRespDTO> recordList = new ArrayList<>();
        pageList.getList().forEach(collectionGiveRecordEntity -> {
            CollectionGiveRecordRespDTO respDTO = CollectionGiveRecordConvert.INSTANCE.convertToDTO(collectionGiveRecordEntity);
            MemberHoldCollectionEntity holdCollection = holdCollectionRepository.getById(collectionGiveRecordEntity.getHoldCollectionId());
            CollectionEntity collection = collectionRepository.getById(holdCollection.getCollectionId());

            // TODO: 2024/4/12 这里暂且用转赠记录iD作为 orderId
            respDTO.setOrderNo(collectionGiveRecordEntity.getId());
            if ("other".equals(resultDirection)) {
                if (memberId.equals(collectionGiveRecordEntity.getGiveFromId())) {
                    respDTO.setGiveDirection(NftConstants.DIRECTION_FROM);
                } else if (memberId.equals(collectionGiveRecordEntity.getGiveToId())) {
                    respDTO.setGiveDirection(NftConstants.DIRECTION_TO);
                }
            } else {
                respDTO.setGiveDirection(resultDirection);
            }
            respDTO.setGiveToMobile(memberRepository.getMobileById(collectionGiveRecordEntity.getGiveToId()));
            respDTO.setGiveFromMobile(memberRepository.getMobileById(collectionGiveRecordEntity.getGiveFromId()));
            if (Objects.isNull(collection)) {
                respDTO.setCollectionName("DataError: No Found Collection Name");
                respDTO.setCollectionCover("DataError: No Found Collection Cover");
            } else {
                respDTO.setCollectionName(collection.getName());
                respDTO.setCollectionCover(collection.getCover());
            }
            recordList.add(respDTO);
        });

        return PageResult.convertFor(pageList, pageSize, recordList);
    }


    public PageResult<PayOrderRespDTO> findPayOrderByPage(long current, long pageSize, String memberMobile,
                                                          String collectionName, String bizMode, String state,
                                                          LocalDateTime createTimeStart, LocalDateTime createTimeEnd) {
        PageInfo<PayOrderEntity> pageList = payOrderRepository.getPayOrderPageListByParam(current, pageSize, memberMobile, collectionName, bizMode, state, createTimeStart, createTimeEnd);
        List<PayOrderRespDTO> respDTOS = new ArrayList<>();
        pageList.getList().forEach(entity -> {
            PayOrderRespDTO respDTO = PayOrderConvert.INSTANCE.convertToRespDTO(entity);
            CollectionEntity collection = collectionRepository.getById(entity.getCollectionId());
            MemberEntity member = memberRepository.getById(entity.getMemberId());
            if(!Objects.isNull(entity.getCreateTime())){
                respDTO.setCreateTime(entity.getCreateTime().toLocalDateTime().format(NftConstants.DATE_FORMAT));
            }
            if (!Objects.isNull(entity.getCancelTime())){
                respDTO.setCancelTime(entity.getCancelTime().toLocalDateTime().format(NftConstants.DATE_FORMAT));
            }
            if (!Objects.isNull(entity.getPaidTime())){
                respDTO.setPaidTime(entity.getPaidTime().toLocalDateTime().format(NftConstants.DATE_FORMAT));
            }
            if (!Objects.isNull(member)) {
                respDTO.setMemberMobile(member.getMobile());
                respDTO.setMemberBlockChainAddr(member.getBlockChainAddr());
            } else {
                respDTO.setMemberMobile("DataError: No Found Member Mobile");
                respDTO.setMemberBlockChainAddr("DataError: No Found Member BlockChain Addr");
            }
            if (!Objects.isNull(collection)) {
                respDTO.setCollectionName(collection.getName());
                respDTO.setCollectionCover(collection.getCover());
                respDTO.setCommodityType(collection.getCommodityType());
            }else {
                respDTO.setCollectionName("DataError: No Found Collection Name");
                respDTO.setCollectionCover("DataError: No Found Collection Cover");
                respDTO.setCommodityType("DataError: No Found Commodity Type");
            }
            CreatorEntity creator = creatorRepository.getById(collection.getCreatorId());
            if (!Objects.isNull(creator)){
                respDTO.setCreatorName(creator.getName());
            }else {
                respDTO.setCreatorName("DataError: No Found Creator Name");
            }

            respDTOS.add(respDTO);
        });
        return PageResult.convertFor(pageList, pageSize, respDTOS);
    }
}
