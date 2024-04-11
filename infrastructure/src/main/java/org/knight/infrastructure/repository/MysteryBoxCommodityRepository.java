package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.dao.domain.MysteryBoxCommodityEntity;

/**
 * @author poboking
 * @description 针对表【mystery_box_commodity】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface MysteryBoxCommodityRepository extends IService<MysteryBoxCommodityEntity> {

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 用户id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    public PageInfo<MysteryBoxCommodityEntity> getPageListByMemberId(long current, long pageSize, String memberId);

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param name     盲盒名称
     * @param memberId 用户id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    public PageInfo<MysteryBoxCommodityEntity> getPageListByMemberIdAndName(long current, long pageSize, String name, String memberId);

    /**
     * 分页查询 - 按价格降序
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderDescPageList(long current, long pageSize);

    /**
     * 分页查询 - 按价格降序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderDescPageListByCreatorId(long current, long pageSize, String creatorId);

    /**
     * 分页查询 - 按价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderDescPageListByCollectionId(long current, long pageSize, String collectionId);

    /**
     * 分页查询 - 按价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param creatorId    创建者id
     * @param collectionId 收藏品id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderDescPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId);

    /**
     * 分页查询 - 按价格升序
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderAscPageList(long current, long pageSize);

    /**
     * 分页查询 - 按价格升序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderAscPageListByCreatorId(long current, long pageSize, String creatorId);

    /**
     * 分页查询 - 按价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderAscPageListByCollectionId(long current, long pageSize, String collectionId);

    /**
     * 分页查询 - 按价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param creatorId    创建者id
     * @param collectionId 收藏品id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderAscPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId);

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    PageInfo<MysteryBoxCommodityEntity> getPageList(long current, long pageSize);
}
