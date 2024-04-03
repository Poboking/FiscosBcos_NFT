package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.knight.infrastructure.dao.domain.MemberResaleCollectionEntity;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author poboking
 * @description 针对表【member_resale_collection】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface MemberResaleCollectionRepository extends IService<MemberResaleCollectionEntity> {

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    public IPage<MemberResaleCollectionEntity> getPriceOrderDescPageList(long current, long pageSize);

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    public IPage<MemberResaleCollectionEntity> getPriceOrderAscPageList(long current, long pageSize);

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    public IPage<MemberResaleCollectionEntity> getPriceOrderDescPageListByCreatorId(long current, long pageSize, String creatorId);

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    public IPage<MemberResaleCollectionEntity> getPriceOrderDescPageListByCollectionId(long current, long pageSize, String collectionId);

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param creatorId    创建者id
     * @param collectionId 收藏品id
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    public IPage<MemberResaleCollectionEntity> getPriceOrderDescPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId);

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    public IPage<MemberResaleCollectionEntity> getPriceOrderAscPageListByCreatorId(long current, long pageSize, String creatorId);

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    public IPage<MemberResaleCollectionEntity> getPriceOrderAscPageListByCollectionId(long current, long pageSize, String collectionId);

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @param creatorId    创建者id
     * @return IPage<MemberResaleCollectionEntity> 分页列表
     */
    public IPage<MemberResaleCollectionEntity> getPriceOrderAscPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId);

    /**
     * 获取分页列表 - 按照会员id
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 会员id
     * @return IPage<MemberHoldCollectionEntity> 分页列表
     */
    public IPage<MemberResaleCollectionEntity> getPageListByMemberId(long current, long pageSize, String memberId);

    /**
     * 获取分页列表 - 按照会员id和状态
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param memberId  会员id
     * @param 持有藏品状态已卖出 持有藏品状态
     * @return IPage<MemberHoldCollectionEntity> 分页列表
     */
    public IPage<MemberResaleCollectionEntity> getPageListByMemberIdAndStatus(long current, long pageSize, String memberId, String 持有藏品状态已卖出);

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
    IPage<MemberResaleCollectionEntity> getPriceOrderDescPageListByParam(Long current, Long pageSize, String creatorId, String collectionId, String state, List<String> collectionIds);

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
    IPage<MemberResaleCollectionEntity> getPriceOrderAscPageListByParam(Long current, Long pageSize, String creatorId, String collectionId, String state, List<String> collectionIds);

    /**
     * 更新订单状态 - 根据ID
     *
     * @param id         订单ID
     * @param state      订单状态
     * @param updateTime 更新时间
     * @return Boolean 是否更新成功
     */
    Boolean updateStateById(String id, String state, Timestamp updateTime);
}
