package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.dao.domain.MemberHoldCollectionEntity;
import org.knight.infrastructure.dao.mapper.MemberHoldCollectionMapper;
import org.knight.infrastructure.repository.MemberHoldCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author poboking
 * @description 针对表【member_hold_collection】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class MemberHoldCollectionRepositoryImpl extends ServiceImpl<MemberHoldCollectionMapper, MemberHoldCollectionEntity>
        implements MemberHoldCollectionRepository {

    private final MemberHoldCollectionMapper memberHoldCollectionMapper;

    @Autowired
    public MemberHoldCollectionRepositoryImpl(MemberHoldCollectionMapper memberHoldCollectionMapper) {
        this.memberHoldCollectionMapper = memberHoldCollectionMapper;
    }

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    @Override
    public PageInfo<MemberHoldCollectionEntity> getPageList(long current, long pageSize) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberHoldCollectionEntity> list = memberHoldCollectionMapper.selectList(null);
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询 - 根据用户id
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 用户id
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    @Override
    public PageInfo<MemberHoldCollectionEntity> getPageListByMemberId(long current, long pageSize, String memberId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberHoldCollectionEntity> list = memberHoldCollectionMapper.selectList(new QueryWrapper<MemberHoldCollectionEntity>().eq("member_id", memberId));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询 - 根据用户id和作品id
     *
     * @param current       当前页
     * @param pageSize      每页大小
     * @param collectionIds 作品id
     * @param memberId      用户id
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    @Override
    public PageInfo<MemberHoldCollectionEntity> getPageListByIdsAndMemberId(long current, long pageSize, List<String> collectionIds, String memberId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberHoldCollectionEntity> list = memberHoldCollectionMapper.selectList(new QueryWrapper<MemberHoldCollectionEntity>()
                    .eq("member_id", memberId)
                    .in("collection_id", collectionIds)
                    .orderByDesc("hold_time"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param name     作品名称
     * @param memberId 用户id
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    @Override
    public PageInfo<MemberHoldCollectionEntity> getPageListByMemberIdAndName(long current, long pageSize, String name, String memberId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberHoldCollectionEntity> list = memberHoldCollectionMapper.selectList(new QueryWrapper<MemberHoldCollectionEntity>()
                    .like("name", name)
                    .eq("member_id", memberId));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param status   藏品转售状态
     * @param memberId 用户id
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    @Override
    public PageInfo<MemberHoldCollectionEntity> getPageListByMemberIdAndStatus(long current, long pageSize, String status, String memberId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberHoldCollectionEntity> list = memberHoldCollectionMapper.selectList(new QueryWrapper<MemberHoldCollectionEntity>()
                    .eq("state", status)
                    .eq("member_id", memberId));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 检查藏品是否存在
     *
     * @param holdCollectionId 持有收藏品id
     * @return MemberHoldCollectionEntity
     */
    @Override
    public MemberHoldCollectionEntity checkExist(String holdCollectionId, String memberId) {
        return memberHoldCollectionMapper.selectOne(new QueryWrapper<MemberHoldCollectionEntity>()
                .eq("id", holdCollectionId)
                .eq("member_id", memberId));
    }

    /**
     * 增加持有数量
     *
     * @param id                 操作类型
     * @param collectionId       藏品id
     * @param gainWay            获取方式
     * @param issuedCollectionId 发行藏品ID
     * @param state              状态码
     * @param memberId           用户id
     * @param transactionHash    交易hash
     * @param holdTime           当前时间
     * @return Boolean
     */
    @Override
    public Boolean increase(String id, String collectionId, String gainWay, String issuedCollectionId, String state, String memberId, Double price, String transactionHash, Timestamp holdTime) {
        MemberHoldCollectionEntity entity = new MemberHoldCollectionEntity();
        entity.setId(id);
        entity.setCollectionId(collectionId);
        entity.setGainWay(gainWay);
        entity.setIssuedCollectionId(issuedCollectionId);
        entity.setState(state);
        entity.setMemberId(memberId);
        entity.setPrice(price);
        entity.setTransactionHash(transactionHash);
        entity.setHoldTime(holdTime);
        return entity.insert();
    }

    /**
     * 增加持有数量 - 通过购买
     *
     * @param id                 操作类型
     * @param collectionId       藏品id
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户id
     * @param price              价格
     * @param transactionHash    交易hash
     * @param holdTime           当前时间
     * @return Boolean
     */
    @Override
    public Boolean increaseByPurchase(String id, String collectionId, String issuedCollectionId, String memberId, Double price, String transactionHash, Timestamp holdTime) {
        return increase(id, collectionId, NftConstants.藏品获取方式_购买, issuedCollectionId, NftConstants.持有藏品状态_持有中, memberId, price, transactionHash, holdTime);
    }

    /**
     * 增加持有数量 - 通过赠送
     *
     * @param id                 操作类型
     * @param collectionId       藏品id
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户id
     * @param transactionHash    交易hash
     * @param holdTime           当前时间
     * @return Boolean
     */
    @Override
    public Boolean increaseByGive(String id, String collectionId, String issuedCollectionId, String memberId, String transactionHash, Timestamp holdTime) {
        return increase(id, collectionId, NftConstants.藏品获取方式_赠送, issuedCollectionId, NftConstants.持有藏品状态_持有中, memberId, 0.0, transactionHash, holdTime);
    }

    /**
     * 增加持有数量 - 通过二级市场
     *
     * @param id                 操作类型
     * @param collectionId       藏品id
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户id
     * @param transactionHash    交易hash
     * @param holdTime           当前时间
     * @return Boolean
     */
    @Override
    public Boolean increaseBySecondaryMarket(String id, String collectionId, String issuedCollectionId, String memberId, Double price, String transactionHash, Timestamp holdTime) {
        return increase(id, collectionId, NftConstants.藏品获取方式_二级市场, issuedCollectionId, NftConstants.持有藏品状态_持有中, memberId, price, transactionHash, holdTime);
    }

    /**
     * 增加持有数量 - 通过盲盒
     *
     * @param id                 操作类型
     * @param collectionId       藏品id
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户id
     * @param transactionHash    交易hash
     * @param holdTime           当前时间
     * @return Boolean
     */
    @Override
    public Boolean increaseByMysteryBox(String id, String collectionId, String issuedCollectionId, String memberId, String transactionHash, Timestamp holdTime) {
        return increase(id, collectionId, NftConstants.藏品获取方式_盲盒, issuedCollectionId, NftConstants.持有藏品状态_持有中, memberId, 0.0, transactionHash, holdTime);
    }

    /**
     * 增加持有数量 - 通过合成
     *
     * @param id                 操作类型
     * @param collectionId       藏品id
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户id
     * @param transactionHash    交易hash
     * @param holdTime           当前时间
     * @return Boolean 是否成功
     */
    @Override
    public Boolean increaseByCompound(String id, String collectionId, String issuedCollectionId, String memberId, String transactionHash, Timestamp holdTime) {
        return increase(id, collectionId, NftConstants.藏品获取方式_合成, issuedCollectionId, NftConstants.持有藏品状态_持有中, memberId, 0.0, transactionHash, holdTime);
    }

    /**
     * 增加持有数量 - 通过空投
     *
     * @param id                 操作类型
     * @param collectionId       藏品id
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户id
     * @param transactionHash    交易hash
     * @param holdTime           当前时间
     * @return Boolean
     */
    @Override
    public Boolean increaseByAirDrop(String id, String collectionId, String issuedCollectionId, String memberId, String transactionHash, Timestamp holdTime) {
        return increase(id, collectionId, NftConstants.藏品获取方式_空投, issuedCollectionId, NftConstants.持有藏品状态_持有中, memberId, 0.0, transactionHash, holdTime);
    }

    /**
     * 增加持有数量 - 通过兑换码
     *
     * @param id                 操作类型
     * @param collectionId       藏品id
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户id
     * @param transactionHash    交易hash
     * @param holdTime           当前时间
     * @return Boolean
     */
    @Override
    public Boolean increaseByRedeemCode(String id, String collectionId, String issuedCollectionId, String memberId, String transactionHash, Timestamp holdTime) {
        return increase(id, collectionId, NftConstants.藏品获取方式_兑换码, issuedCollectionId, NftConstants.持有藏品状态_持有中, memberId, 0.0, transactionHash, holdTime);
    }

    /**
     * 获取分页列表 - 根据参数体查询
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param memberId     用户ID
     * @param collectionId 藏品ID
     * @param state        藏品状态
     * @param gainWay      获得方式
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    @Override
    public PageInfo<MemberHoldCollectionEntity> getPageListByParam(Long current, Long pageSize, String memberId, String collectionId, String state, String gainWay) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage(current.intValue(), pageSize.intValue());
            List<MemberHoldCollectionEntity> list = memberHoldCollectionMapper.selectList(new QueryWrapper<MemberHoldCollectionEntity>()
                    .eq(!CharSequenceUtil.isBlank(memberId), "member_id", memberId)
                    .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                    .eq(!CharSequenceUtil.isBlank(state), "state", state)
                    .eq(!CharSequenceUtil.isBlank(gainWay), "gain_way", gainWay));
            pageInfo = new PageInfo(list, pageSize.intValue());
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取分页列表 - 根据参数体查询
     *
     * @param current       当前页
     * @param pageSize      每页大小
     * @param collectionIds 藏品ID
     * @param memberId      用户ID
     * @return PageInfo<MemberHoldCollectionEntity> 分页结果
     */
    @Override
    public PageInfo<MemberHoldCollectionEntity> getHoldPageListByIdsAndMemberId(long current, long pageSize, List<String> collectionIds, String memberId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberHoldCollectionEntity> list = memberHoldCollectionMapper.selectList(new QueryWrapper<MemberHoldCollectionEntity>()
                    .eq("member_id", memberId)
                    .in("collection_id", collectionIds)
                    .eq("state", NftConstants.持有藏品状态_持有中)
                    .orderByDesc("hold_time"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 更新藏品状态 - 通过藏品ID
     *
     * @param memberHoldCollectionId id
     * @param state                  状态
     * @param updateTime             更新时间
     * @return boolean 是否成功
     */
    @Override
    public Boolean updateState(String memberHoldCollectionId, String state, Timestamp updateTime) {
        if (CharSequenceUtil.isBlank(memberHoldCollectionId) || CharSequenceUtil.isBlank(state)) {
            return false;
        }
        if (updateTime == null) {
            updateTime = Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT));
        }
        if (NftConstants.持有藏品状态_已卖出.equals(state)) {
            return memberHoldCollectionMapper.update(new UpdateWrapper<MemberHoldCollectionEntity>()
                    .eq("id", memberHoldCollectionId)
                    .set("state", state)
                    .set("lose_time", updateTime)) > 0;
        }
        if (NftConstants.持有藏品状态_已转赠.equals(state)) {
            return memberHoldCollectionMapper.update(new UpdateWrapper<MemberHoldCollectionEntity>()
                    .eq("id", memberHoldCollectionId)
                    .set("state", state)
                    .set("lose_time", updateTime)) > 0;
        }
        return memberHoldCollectionMapper.update(new UpdateWrapper<MemberHoldCollectionEntity>()
                .eq("id", memberHoldCollectionId)
                .set("state", state)) > 0;
    }

}




