package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.dao.domain.CollectionEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author poboking
 * @description 针对表【collection】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface CollectionRepository extends IService<CollectionEntity> {

    /**
     * 获取藏品id
     *
     * @param collectionName 藏品名称
     * @return 藏品id
     */
    String getIdByName(String collectionName);

    /**
     * 获取分页列表
     *
     * @param current  当前页
     * @param pageSize 页面大小
     * @return PageInfo<CollectionEntity> pageResult
     */
    PageInfo<CollectionEntity> getPageList(long current, long pageSize);

    /**
     * 获取分页列表
     *
     * @param current  当前页
     * @param pageSize 页面大小
     * @param name     名称
     * @return PageInfo<CollectionEntity> pageResult
     */
    PageInfo<CollectionEntity> getPageListByName(long current, long pageSize, String name);

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
     * @return PageInfo<CollectionEntity> pageResult
     */
    PageInfo<CollectionEntity> getPageListByCreatorId(long current, long pageSize, String creatorId);

    /**
     * 根据藏品类型 - 获取分页列表
     *
     * @param current       当前页
     * @param pageSize      页面大小
     * @param commodityType 商品类型
     * @return PageInfo<CollectionEntity> pageResult
     */
    PageInfo<CollectionEntity> getPageListByCommodityType(long current, long pageSize, String commodityType);

    /**
     * 根据藏品类型 - 获取ID列表
     *
     * @param commodityType 商品类型(藏品或盲盒)
     * @return List<String> id列表
     */
    List<String> getIdsByCommodityType(String commodityType);

    /**
     * 根据藏品类型和创建者ID - 获取分页列表
     *
     * @param current       当前页
     * @param pageSize      页面大小
     * @param commodityType 商品类型
     * @param creatorId     创建者ID
     * @return PageInfo<CollectionEntity> pageResult
     */
    PageInfo<CollectionEntity> getPageListByCreatorIdAndCommodityType(long current, long pageSize, String creatorId, String commodityType);

    /**
     * 根据藏品类型和藏品名称 - 获取分页列表
     *
     * @param current       当前页
     * @param pageSize      页面大小
     * @param commodityType 商品类型
     * @param name          藏品名称
     * @return PageInfo<CollectionEntity> pageResult
     */
    PageInfo<CollectionEntity> getPageListByNameAndCommodityType(long current, long pageSize, String name, String commodityType);

    /**
     * 根据时间范围 - 获取升序列表
     *
     * @param saleTimeStart 销售时间开始
     * @param saleTimeEnd   销售时间结束
     * @return List<CollectionEntity> 指定销售时间范围的CollectionEntity升序列表
     */
    List<CollectionEntity> getAscListByDateParam(LocalDateTime saleTimeStart, LocalDateTime saleTimeEnd);

    /**
     * 根据时间范围 - 获取降序列表
     *
     * @param saleTimeStart 销售时间开始
     * @param saleTimeEnd   销售时间结束
     * @return List<CollectionEntity> 指定销售时间范围的CollectionEntity降序列表
     */
    List<CollectionEntity> getDescListByDateParam(LocalDateTime saleTimeStart, LocalDateTime saleTimeEnd);

    /**
     * 检查藏品是否存在
     *
     * @param collectionId 藏品ID
     * @return boolean 是否存在
     */
    Boolean checkExist(String collectionId);

    /**
     * 增加库存
     *
     * @param collectionId 藏品ID
     * @return boolean 是否增加成功
     */
    Boolean increaseStock(String collectionId);

    /**
     * 增加库存
     *
     * @param collectionId 藏品ID
     * @param count        增加数量
     * @return boolean 是否增加成功
     */
    Boolean increaseStock(String collectionId, Integer count);

    /**
     * 减少库存
     *
     * @param collectionId 藏品ID
     * @return boolean 是否减少成功
     */
    Boolean reduceStock(String collectionId);

    /**
     * 减少库存
     *
     * @param collectionId 藏品ID
     * @param count        减少数量
     * @return boolean 是否减少成功
     */
    Boolean reduceStock(String collectionId, Integer count);

    /**
     * 获取库存
     *
     * @param collectionId 藏品ID
     * @return Long 库存
     */
    Integer getStock(String collectionId);

    /**
     * 获取指定ID藏品的数量 - 根据藏品ID
     *
     * @param collectionId 藏品ID
     * @return Integer 藏品发行数量
     */
    Integer getQuantityById(String collectionId);
}
