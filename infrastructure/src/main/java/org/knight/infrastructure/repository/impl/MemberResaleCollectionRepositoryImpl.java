package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.dao.domain.MemberResaleCollectionEntity;
import org.knight.infrastructure.dao.mapper.MemberResaleCollectionMapper;
import org.knight.infrastructure.repository.MemberResaleCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
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
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderDescPageList(long current, long pageSize) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(
                    new QueryWrapper<MemberResaleCollectionEntity>().orderByDesc("resale_price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderAscPageList(long current, long pageSize) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(
                    new QueryWrapper<MemberResaleCollectionEntity>().orderByAsc("resale_price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderDescPageListByCreatorId(long current, long pageSize, String creatorId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(
                    new QueryWrapper<MemberResaleCollectionEntity>()
                            .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                            .orderByDesc("resale_price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderDescPageListByCollectionId(long current, long pageSize, String collectionId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(
                    new QueryWrapper<MemberResaleCollectionEntity>()
                            .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                            .orderByDesc("resale_price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param creatorId    创建者id
     * @param collectionId 收藏品id
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderDescPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(
                    new QueryWrapper<MemberResaleCollectionEntity>()
                            .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                            .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                            .orderByDesc("resale_price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderAscPageListByCreatorId(long current, long pageSize, String creatorId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(
                    new QueryWrapper<MemberResaleCollectionEntity>()
                            .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                            .orderByAsc("resale_price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderAscPageListByCollectionId(long current, long pageSize, String collectionId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(
                    new QueryWrapper<MemberResaleCollectionEntity>()
                            .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                            .orderByAsc("resale_price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @param creatorId    创建者id
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderAscPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(
                    new QueryWrapper<MemberResaleCollectionEntity>()
                            .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                            .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                            .orderByAsc("resale_price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取分页列表 - 按照会员id
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 会员id
     * @return PageInfo<MemberHoldCollectionEntity> 分页列表
     */
    @Override
    public PageInfo<MemberResaleCollectionEntity> getPageListByMemberId(Long current, Long pageSize, String memberId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage(current.intValue(), pageSize.intValue());
            List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(
                    new QueryWrapper<MemberResaleCollectionEntity>()
                            .eq(!CharSequenceUtil.isBlank(memberId), "member_id", memberId));
            pageInfo = new PageInfo(list, pageSize.intValue());
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取分页列表 - 按照会员id和状态
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 会员id
     * @param state    持有藏品状态
     * @return PageInfo<MemberHoldCollectionEntity> 分页列表
     */
    @Override
    public PageInfo<MemberResaleCollectionEntity> getPageListByMemberIdAndStatus(Long current, Long pageSize, String memberId, String state) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage(current.intValue(), pageSize.intValue());
            List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(new QueryWrapper<MemberResaleCollectionEntity>()
                    .eq(!CharSequenceUtil.isBlank(memberId), "member_id", memberId)
                    .eq(!CharSequenceUtil.isBlank(state), "state", state));
            pageInfo = new PageInfo(list, pageSize.intValue());
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
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
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderDescPageListByParam(Long current, Long pageSize, String creatorId, String collectionId, String state, List<String> collectionIds) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage(current.intValue(), pageSize.intValue());
            List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(new QueryWrapper<MemberResaleCollectionEntity>()
                    .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                    .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                    .eq(!CharSequenceUtil.isBlank(state), "state", state)
                    .in(Optional.ofNullable(collectionIds).isPresent(), "collection_id", collectionIds)
                    .orderByDesc("resale_price"));
            pageInfo = new PageInfo(list, pageSize.intValue());
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
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
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    @Override
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderAscPageListByParam(Long current, Long pageSize, String creatorId, String collectionId, String state, List<String> collectionIds) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage(current.intValue(), pageSize.intValue());
            List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(new QueryWrapper<MemberResaleCollectionEntity>()
                    .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                    .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                    .eq(!CharSequenceUtil.isBlank(state), "state", state)
                    .in(Optional.ofNullable(collectionIds).isPresent(), "collection_id", collectionIds)
                    .orderByAsc("resale_price"));
            pageInfo = new PageInfo(list, pageSize.intValue());
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
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
        if (state.equals(NftConstants.转售的藏品状态_已卖出)) {
            return memberResaleCollectionMapper.update(new UpdateWrapper<MemberResaleCollectionEntity>()
                    .eq("id", id)
                    .set("state", state)
                    .set("sold_time", updateTime)) > 0;
        }
        if (state.equals(NftConstants.转售的藏品状态_已取消)) {
            return memberResaleCollectionMapper.update(new UpdateWrapper<MemberResaleCollectionEntity>()
                    .eq("id", id)
                    .set("state", state)
                    .set("cancel_time", updateTime)) > 0;
        }
        if (state.equals(NftConstants.转售的藏品状态_已发布)) {
            return memberResaleCollectionMapper.update(new UpdateWrapper<MemberResaleCollectionEntity>()
                    .eq("id", id)
                    .set("state", state)
                    .set("resale_time", updateTime)) > 0;
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
        if (CharSequenceUtil.isBlank(resaleCollectionId)) {
            return false;
        }
        return memberResaleCollectionMapper.exists(new QueryWrapper<MemberResaleCollectionEntity>()
                .eq("id", resaleCollectionId));
    }

    /**
     * 检查是否存在
     *
     * @param resaleCollectionId 出售藏品ID
     * @param memberId           会员ID
     * @return boolean 是否存在
     */
    @Override
    public boolean checkExist(String resaleCollectionId, String memberId) {
        if (CharSequenceUtil.isBlank(resaleCollectionId)) {
            return false;
        }
        if (CharSequenceUtil.isBlank(memberId)) {
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
     * @param state              状态
     * @return boolean 是否存在
     */
    @Override
    public Boolean checkState(String resaleCollectionId, String state) {
        return memberResaleCollectionMapper.exists(new QueryWrapper<MemberResaleCollectionEntity>()
                .eq("id", resaleCollectionId)
                .eq("state", state));
    }

    /**
     * 检查转赠藏品是否存在
     *
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户ID
     * @return boolean 是否存在
     */
    @Override
    public boolean checkResaleExistByIssuedCollection(String issuedCollectionId, String memberId) {
        if (CharSequenceUtil.isBlank(issuedCollectionId) || CharSequenceUtil.isBlank(memberId)) {
            return false;
        }
        List<MemberResaleCollectionEntity> list = memberResaleCollectionMapper.selectList(new QueryWrapper<MemberResaleCollectionEntity>()
                .eq("issued_collection_id", issuedCollectionId)
                .eq("member_id", memberId)
                .eq("state", NftConstants.转售的藏品状态_已发布));
        return !list.isEmpty();
    }

    /**
     * 锁定转售藏品
     *
     * @param resaleCollectionId id
     * @param memberId           用户iD
     * @return boolean
     */
    @Override
    public Boolean lockResaleCollection(String resaleCollectionId, String memberId) {
        if (CharSequenceUtil.isBlank(resaleCollectionId) || CharSequenceUtil.isBlank(memberId)) {
            return false;
        }
        return memberResaleCollectionMapper.update(new UpdateWrapper<MemberResaleCollectionEntity>()
                .eq("id", resaleCollectionId)
                .set("lock_pay_member_id", memberId)) > 0;
    }

    /**
     * 获取最新的锁定用户ID - 根据发行藏品ID
     *
     * @param issuedCollectionId 发行藏品ID
     * @return string 用户ID
     */
    @Override
    public String getLockMemberByIssuedCollectionId(String issuedCollectionId) {
        MemberResaleCollectionEntity entity = memberResaleCollectionMapper.selectOne(new QueryWrapper<MemberResaleCollectionEntity>()
                .eq("issued_collection_id", issuedCollectionId)
                .orderByDesc("resale_time")
                .last("limit 1"));
        if (Objects.isNull(entity)) {
            return null;
        }
        return entity.getLockPayMemberId();
    }

    /**
     * 获取转售藏品持有用户ID - 根据发行藏品ID
     *
     * @param issuedCollectionId 发行藏品ID
     * @return string 持有者ID
     */
    @Override
    public MemberResaleCollectionEntity getLastEntityByIssuedCollectionId(String issuedCollectionId) {
        MemberResaleCollectionEntity entity = memberResaleCollectionMapper.selectOne(new QueryWrapper<MemberResaleCollectionEntity>()
                .eq("issued_collection_id", issuedCollectionId)
                .orderByDesc("resale_time")
                .last("limit 1"));
        if (Objects.isNull(entity)) {
            return null;
        }
        return entity;
    }
}




