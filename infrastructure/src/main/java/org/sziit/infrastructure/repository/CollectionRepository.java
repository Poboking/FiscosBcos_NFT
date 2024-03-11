package org.sziit.infrastructure.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.sziit.infrastructure.dao.domain.CollectionEntity;

/**
 * @author poboking
 * @description 针对表【collection】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface CollectionRepository extends IService<CollectionEntity> {
    /**
     * 获取分页列表
     *
     * @param current  当前页
     * @param pageSize 页面大小
     * @return IPage<CollectionEntity> pageResult
     */
    IPage<CollectionEntity> getPageList(long current, long pageSize);

    /**
     * 获取分页列表
     *
     * @param current  当前页
     * @param pageSize 页面大小
     * @param name     名称
     * @return IPage<CollectionEntity> pageResult
     */
    IPage<CollectionEntity> getPageListByName(long current, long pageSize, String name);

    /**
     * 获取详情
     *
     * @param collectionId 主键
     * @return CollectionEntity
     */
    CollectionEntity getCollectionDetail(String collectionId);

    /**
     * 获取分页列表
     *
     * @param current   当前页
     * @param pageSize  页面大小
     * @param creatorId 创建者id
     * @return IPage<CollectionEntity> pageResult
     */
    IPage<CollectionEntity> getPageListByCreatorId(long current, long pageSize, String creatorId);

    /**
     * 根据藏品类型 - 获取分页列表
     *
     * @param current       当前页
     * @param pageSize      页面大小
     * @param commodityType 商品类型
     * @return IPage<CollectionEntity> pageResult
     */
    IPage<CollectionEntity> getPageListByCommodityType(long current, long pageSize, String commodityType);
}
