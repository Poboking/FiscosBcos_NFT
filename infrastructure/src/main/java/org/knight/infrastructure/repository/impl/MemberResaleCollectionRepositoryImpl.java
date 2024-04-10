package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.dao.domain.MemberResaleCollectionEntity;
import org.knight.infrastructure.dao.mapper.MemberResaleCollectionMapper;
import org.knight.infrastructure.repository.MemberResaleCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * @author poboking
 * @description 针对表【member_resale_collection】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class MemberResaleCollectionRepositoryImpl extends ServiceImpl<MemberResaleCollectionMapper, MemberResaleCollectionEntity>
        implements MemberResaleCollectionRepository {
    @Autowired
    private MemberResaleCollectionMapper memberResaleCollectionMapper;

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public IPage<MemberResaleCollectionEntity> getPriceOrderDescPageList(long current, long pageSize) {
        return memberResaleCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberResaleCollectionEntity>().orderByDesc("resale_price"));
    }

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public IPage<MemberResaleCollectionEntity> getPriceOrderAscPageList(long current, long pageSize) {
        return memberResaleCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberResaleCollectionEntity>().orderByAsc("resale_price"));
    }

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public IPage<MemberResaleCollectionEntity> getPriceOrderDescPageListByCreatorId(long current, long pageSize, String creatorId) {
        return memberResaleCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberResaleCollectionEntity>()
                        .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                        .orderByDesc("resale_price"));
    }

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public IPage<MemberResaleCollectionEntity> getPriceOrderDescPageListByCollectionId(long current, long pageSize, String collectionId) {
        return memberResaleCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberResaleCollectionEntity>()
                        .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                        .orderByDesc("resale_price"));
    }

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param creatorId    创建者id
     * @param collectionId 收藏品id
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public IPage<MemberResaleCollectionEntity> getPriceOrderDescPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId) {
        return memberResaleCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberResaleCollectionEntity>()
                        .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                        .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                        .orderByDesc("resale_price"));
    }

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public IPage<MemberResaleCollectionEntity> getPriceOrderAscPageListByCreatorId(long current, long pageSize, String creatorId) {
        return memberResaleCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberResaleCollectionEntity>()
                        .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                        .orderByDesc("resale_price"));
    }

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public IPage<MemberResaleCollectionEntity> getPriceOrderAscPageListByCollectionId(long current, long pageSize, String collectionId) {
        return memberResaleCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberResaleCollectionEntity>()
                        .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                        .orderByAsc("resale_price"));
    }

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @param creatorId    创建者id
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public IPage<MemberResaleCollectionEntity> getPriceOrderAscPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId) {
        return memberResaleCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberResaleCollectionEntity>()
                        .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                        .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                        .orderByAsc("resale_price"));
    }

    /**
     * 获取分页列表 - 按照会员id
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 会员id
     * @return IPage<MemberHoldCollectionEntity> 分页列表
     */
    @Override
    public IPage<MemberResaleCollectionEntity> getPageListByMemberId(long current, long pageSize, String memberId) {
        return memberResaleCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberResaleCollectionEntity>()
                        .eq(!CharSequenceUtil.isBlank(memberId), "member_id", memberId));
    }

    /**
     * 获取分页列表 - 按照会员id和状态
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 会员id
     * @param state   持有藏品状态
     * @return IPage<MemberHoldCollectionEntity> 分页列表
     */
    @Override
    public IPage<MemberResaleCollectionEntity> getPageListByMemberIdAndStatus(long current, long pageSize, String memberId, String state) {
        return memberResaleCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberResaleCollectionEntity>()
                        .eq(!CharSequenceUtil.isBlank(memberId), "member_id", memberId)
                        .eq(!CharSequenceUtil.isBlank(state), "state", state));
    }

    /**
     * 获取分页列表 - 按照Param
     *
     * @param current       当前页
     * @param pageSize      每页大小
     * @param creatorId     创建者id
     * @param collectionId  收藏品id
     * @param state         持有藏品状态
     * @param collectionIds 特定类别的收藏品ids
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public IPage<MemberResaleCollectionEntity> getPriceOrderDescPageListByParam(Long current, Long pageSize, String creatorId, String collectionId, String state, List<String> collectionIds) {
        return memberResaleCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberResaleCollectionEntity>()
                        .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                        .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                        .eq(!CharSequenceUtil.isBlank(state), "state", state)
                        .in(Optional.ofNullable(collectionIds).isPresent(), "collection_id", collectionIds)
                        .orderByDesc("resale_price"));
    }

    /**
     * 获取分页列表 - 按照Param
     *
     * @param current       当前页
     * @param pageSize      每页大小
     * @param creatorId     创建者id
     * @param collectionId  收藏品id
     * @param state         持有藏品状态
     * @param collectionIds 特定类别的收藏品ids
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public IPage<MemberResaleCollectionEntity> getPriceOrderAscPageListByParam(Long current, Long pageSize, String creatorId, String collectionId, String state, List<String> collectionIds) {
        return memberResaleCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberResaleCollectionEntity>()
                        .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                        .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                        .eq(!CharSequenceUtil.isBlank(state), "state", state)
                        .in(Optional.ofNullable(collectionIds).isPresent(), "collection_id", collectionIds)
                        .orderByAsc("resale_price"));
    }

    /**
     * 更新用户出售藏品状态 - 根据ID
     *
     * @param id         订单ID
     * @param state      订单状态
     * @param updateTime 更新时间
     * @return Boolean 是否更新成功
     */
    @Override
    public Boolean updateStateById(String id, String state, Timestamp updateTime) {
        if (CharSequenceUtil.isBlank(id) || CharSequenceUtil.isBlank(state) || updateTime == null) {
            return false;
        }
        if (state.equals(NftConstants.转售的藏品状态_已卖出)){
            return memberResaleCollectionMapper.update(new UpdateWrapper<MemberResaleCollectionEntity>()
                    .eq("id", id)
                    .set("state", state)
                    .set("update_time", updateTime)
                    .set("sold_time",updateTime)) > 0;
        }
        if (state.equals(NftConstants.转售的藏品状态_已取消)){
            return memberResaleCollectionMapper.update(new UpdateWrapper<MemberResaleCollectionEntity>()
                    .eq("id", id)
                    .set("state", state)
                    .set("update_time", updateTime)
                    .set("cancel_time",updateTime)) > 0;
        }
        if (state.equals(NftConstants.转售的藏品状态_已发布)){
            return memberResaleCollectionMapper.update(new UpdateWrapper<MemberResaleCollectionEntity>()
                    .eq("id", id)
                    .set("state", state)
                    .set("update_time", updateTime)
                    .set("resale",updateTime)) > 0;

        }
//        if (state.equals(NftConstants.转售的藏品状态_已下架))
        return false;
    }

    /**
     * 检查是否存在
     *
     * @param resaleCollectionId 出售藏品ID
     * @return boolean 是否存在
     */
    @Override
    public boolean checkExist(String resaleCollectionId) {
        if (CharSequenceUtil.isBlank(resaleCollectionId)){
            return false;
        }
        return memberResaleCollectionMapper.exists(new QueryWrapper<MemberResaleCollectionEntity>()
                .eq("id", resaleCollectionId));
    }

    /**
     * 检查是否存在
     *
     * @param resaleCollectionId 出售藏品ID
     * @param memberId 会员ID
     * @return boolean 是否存在
     */
    @Override
    public boolean checkExist(String resaleCollectionId, String memberId){
        if (CharSequenceUtil.isBlank(resaleCollectionId)){
            return false;
        }
        if (CharSequenceUtil.isBlank(memberId)){
            return false;
        }
        return memberResaleCollectionMapper.exists(new QueryWrapper<MemberResaleCollectionEntity>()
                .eq("id", resaleCollectionId)
                .eq("member_id", memberId));
    }

    /**
     * 检查状态
     *
     * @param resaleCollectionId 出售藏品ID
     * @param state         状态
     * @return boolean 是否存在
     */
    @Override
    public Boolean checkState(String resaleCollectionId, String state) {
        return memberResaleCollectionMapper.exists(new QueryWrapper<MemberResaleCollectionEntity>()
                .eq("id", resaleCollectionId)
                .eq("state", state));
    }
}




