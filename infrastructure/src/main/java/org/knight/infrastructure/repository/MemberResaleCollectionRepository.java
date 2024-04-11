package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
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
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderDescPageList(long current, long pageSize);

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderAscPageList(long current, long pageSize);

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderDescPageListByCreatorId(long current, long pageSize, String creatorId);

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderDescPageListByCollectionId(long current, long pageSize, String collectionId);

    /**
     * 获取分页列表 - 按照价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param creatorId    创建者id
     * @param collectionId 收藏品id
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderDescPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId);

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderAscPageListByCreatorId(long current, long pageSize, String creatorId);

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderAscPageListByCollectionId(long current, long pageSize, String collectionId);

    /**
     * 获取分页列表 - 按照价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @param creatorId    创建者id
     * @return PageInfo<MemberResaleCollectionEntity> 分页列表
     */
    public PageInfo<MemberResaleCollectionEntity> getPriceOrderAscPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId);

    /**
     * 获取分页列表 - 按照会员id
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 会员id
     * @return PageInfo<MemberHoldCollectionEntity> 分页列表
     */
    PageInfo<MemberResaleCollectionEntity> getPageListByMemberId(Long current, Long pageSize, String memberId);

    /**
     * 获取分页列表 - 按照会员id和状态
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param memberId  会员id
     * @param state 持有藏品状态
     * @return PageInfo<MemberHoldCollectionEntity> 分页列表
     */
    PageInfo<MemberResaleCollectionEntity> getPageListByMemberIdAndStatus(Long current, Long pageSize, String memberId, String state);
    



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
    PageInfo<MemberResaleCollectionEntity> getPriceOrderDescPageListByParam(Long current, Long pageSize, String creatorId, String collectionId, String state, List<String> collectionIds);

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
    PageInfo<MemberResaleCollectionEntity> getPriceOrderAscPageListByParam(Long current, Long pageSize, String creatorId, String collectionId, String state, List<String> collectionIds);

    /**
     * 更新订单状态 - 根据ID
     *
     * @param id         订单ID
     * @param state      订单状态
     * @param updateTime 更新时间
     * @return Boolean 是否更新成功
     */
    Boolean updateStateById(String id, String state, Timestamp updateTime);

    /**
     * 检查是否存在
     *
     * @param resaleCollectionId 出售藏品ID
     * @return boolean 是否存在
     */
    boolean checkExist(String resaleCollectionId);

    /**
     * 检查是否存在
     *
     * @param resaleCollectionId 出售藏品ID
     * @param memberId           会员ID
     * @return boolean 是否存在
     */
    boolean checkExist(String resaleCollectionId, String memberId);

    /**
     * 检查状态
     *
     * @param resaleCollectionId 出售藏品ID
     * @param 转售的藏品状态已发布         状态
     * @return boolean 是否存在
     */
    Boolean checkState(String resaleCollectionId, String 转售的藏品状态已发布);
}
