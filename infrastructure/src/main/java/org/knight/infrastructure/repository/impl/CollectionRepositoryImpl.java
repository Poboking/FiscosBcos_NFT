package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.CollectionEntity;
import org.knight.infrastructure.dao.mapper.CollectionMapper;
import org.knight.infrastructure.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author poboking
 * @description 针对表【collection】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class CollectionRepositoryImpl extends ServiceImpl<CollectionMapper, CollectionEntity>
        implements CollectionRepository {

    private final CollectionMapper collectionMapper;

    @Autowired
    public CollectionRepositoryImpl(CollectionMapper collectionMapper) {
        this.collectionMapper = collectionMapper;
    }


    /**
     * 获取分页列表
     *
     * @param current  当前页
     * @param pageSize 页面大小
     * @return IPage<CollectionEntity> pageResult
     */
    @Override
    public IPage<CollectionEntity> getPageList(long current, long pageSize) {
        return collectionMapper.selectPage(new Page<>(current, pageSize), null);
    }


    /**
     * 获取分页列表
     *
     * @param current  当前页
     * @param pageSize 页面大小
     * @param name     名称
     * @return IPage<CollectionEntity> pageResult
     */
    @Override
    public IPage<CollectionEntity> getPageListByName(long current, long pageSize, String name) {
        return collectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<CollectionEntity>()
                        .like(Optional.ofNullable(name).isPresent(), "name", name));
    }

    /**
     * 获取详情
     *
     * @param collectionId 主键
     * @return CollectionEntity
     */
    @Override
    public CollectionEntity getCollectionDetail(String collectionId) {
        return collectionMapper.selectById(collectionId);
    }

    /**
     * 获取分页列表
     *
     * @param current   当前页
     * @param pageSize  页面大小
     * @param creatorId 创建者id
     * @return IPage<CollectionEntity> pageResult
     */
    @Override
    public IPage<CollectionEntity> getPageListByCreatorId(long current, long pageSize, String creatorId) {
        return collectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<CollectionEntity>()
                        .eq(Optional.ofNullable(creatorId).isPresent(), "creator_id", creatorId));
    }

    /**
     * 根据藏品类型 - 获取分页列表
     *
     * @param current       当前页
     * @param pageSize      页面大小
     * @param commodityType 商品类型
     * @return IPage<CollectionEntity> pageResult
     */
    @Override
    public IPage<CollectionEntity> getPageListByCommodityType(long current, long pageSize, String commodityType) {
        return collectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<CollectionEntity>()
                        .eq(Optional.ofNullable(commodityType).isPresent(), "commodity_type", commodityType));
    }

    /**
     * 根据藏品类型 - 获取ID列表
     *
     * @param commodityType 商品类型(藏品或盲盒)
     * @return List<String> id列表
     */
    @Override
    public List<String> getIdsByCommodityType(String commodityType) {
        return collectionMapper.selectObjs(new QueryWrapper<CollectionEntity>()
                .select("id")
                .eq("commodity_type", Optional.ofNullable(commodityType).orElse("1")));
    }

    /**
     * 根据藏品类型和创建者ID - 获取分页列表
     *
     * @param current       当前页
     * @param pageSize      页面大小
     * @param creatorId     创建者ID
     * @param commodityType 商品类型
     * @return IPage<CollectionEntity> pageResult
     */
    @Override
    public IPage<CollectionEntity> getPageListByCreatorIdAndCommodityType(long current, long pageSize, String creatorId, String commodityType) {
        return collectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<CollectionEntity>()
                        .eq(Optional.ofNullable(creatorId).isPresent(), "creator_id", creatorId)
                        .eq(Optional.ofNullable(commodityType).isPresent(), "commodity_type", commodityType));
    }

    /**
     * 根据藏品类型和藏品名称 - 获取分页列表
     *
     * @param current       当前页
     * @param pageSize      页面大小
     * @param name          藏品名称
     * @param commodityType 商品类型
     * @return IPage<CollectionEntity> pageResult
     */
    @Override
    public IPage<CollectionEntity> getPageListByNameAndCommodityType(long current, long pageSize, String name, String commodityType) {
        return collectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<CollectionEntity>()
                        .like(Optional.ofNullable(name).isPresent(), "name", name)
                        .eq(Optional.ofNullable(commodityType).isPresent(), "commodity_type", commodityType));
    }

    /**
     * 根据时间范围 - 获取升序列表
     *
     * @param saleTimeStart 销售时间开始
     * @param saleTimeEnd   销售时间结束
     * @return List<CollectionEntity> 指定销售时间范围的CollectionEntity升序列表
     */
    @Override
    public List<CollectionEntity> getAscListByDateParam(LocalDateTime saleTimeStart, LocalDateTime saleTimeEnd) {
        return collectionMapper.selectList(new QueryWrapper<CollectionEntity>()
                .ge(Optional.ofNullable(saleTimeStart).isPresent(), "sale_time", saleTimeStart)
                .le(Optional.ofNullable(saleTimeEnd).isPresent(), "sale_time", saleTimeEnd)
                .orderByAsc("sale_time"));
    }

    /**
     * 根据时间范围 - 获取降序列表
     *
     * @param saleTimeStart 销售时间开始
     * @param saleTimeEnd   销售时间结束
     * @return List<CollectionEntity> 指定销售时间范围的CollectionEntity降序列表
     */
    @Override
    public List<CollectionEntity> getDescListByDateParam(LocalDateTime saleTimeStart, LocalDateTime saleTimeEnd) {
        return collectionMapper.selectList(new QueryWrapper<CollectionEntity>()
                .ge(Optional.ofNullable(saleTimeStart).isPresent(), "sale_time", saleTimeStart)
                .le(Optional.ofNullable(saleTimeEnd).isPresent(), "sale_time", saleTimeEnd)
                .orderByDesc("sale_time"));
    }

    /**
     * 减少库存
     *
     * @param collectionId 藏品ID
     * @return boolean 是否减少成功
     */
    @Override
    public Boolean reduceStock(String collectionId) {
        if (collectionMapper.selectById(collectionId).getStock() <= 0) {
            return false;
        }
        return collectionMapper.update(null,
                new UpdateWrapper<CollectionEntity>()
                        .gt("stock", 0)
                        .eq("id", collectionId)
                        .setSql("stock = stock - 1")) > 0;
    }
}




