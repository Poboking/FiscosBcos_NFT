package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.dao.domain.CollectionEntity;
import org.knight.infrastructure.dao.mapper.CollectionMapper;
import org.knight.infrastructure.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
     * 获取藏品id
     *
     * @param collectionName 藏品名称
     * @return 藏品id
     */
    @Override
    public String getIdByName(String collectionName) {
        CollectionEntity collection = collectionMapper.selectList(new QueryWrapper<CollectionEntity>()
                        .like(!CharSequenceUtil.isBlank(collectionName), "name", collectionName))
                .stream().findFirst().orElse(null);
        if (Objects.isNull(collection)) {
            return null;
        }
        return collection.getId();
    }

    /**
     * 获取分页列表
     *
     * @param current  当前页
     * @param pageSize 页面大小
     * @return PageInfo<CollectionEntity> pageResult
     */
    @Override
    public PageInfo<CollectionEntity> getPageList(long current, long pageSize) {
        PageInfo result = null;
        try {
            com.github.pagehelper.Page<CollectionEntity> page = PageHelper.startPage((int) current, (int) pageSize);
            List<CollectionEntity> list = collectionMapper.selectList(null);
            result = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return result;
    }


    /**
     * 获取分页列表
     *
     * @param current  当前页
     * @param pageSize 页面大小
     * @param name     名称
     * @return PageInfo<CollectionEntity> pageResult
     */
    @Override
    public PageInfo<CollectionEntity> getPageListByName(long current, long pageSize, String name) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<CollectionEntity> list = collectionMapper.selectList(new QueryWrapper<CollectionEntity>()
                    .like(!CharSequenceUtil.isBlank(name), "name", name));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }

        return pageInfo;
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
     * @return PageInfo<CollectionEntity> pageResult
     */
    @Override
    public PageInfo<CollectionEntity> getPageListByCreatorId(long current, long pageSize, String creatorId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<CollectionEntity> list = collectionMapper.selectList(new QueryWrapper<CollectionEntity>()
                    .eq("creator_id", creatorId));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 根据藏品类型 - 获取分页列表
     *
     * @param current       当前页
     * @param pageSize      页面大小
     * @param commodityType 商品类型
     * @return PageInfo<CollectionEntity> pageResult
     */
    @Override
    public PageInfo<CollectionEntity> getPageListByCommodityType(long current, long pageSize, String commodityType) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<CollectionEntity> list = collectionMapper.selectList(new QueryWrapper<CollectionEntity>()
                    .eq(!CharSequenceUtil.isBlank(commodityType), "commodity_type", commodityType));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
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
     * @return PageInfo<CollectionEntity> pageResult
     */
    @Override
    public PageInfo<CollectionEntity> getPageListByCreatorIdAndCommodityType(long current, long pageSize, String creatorId, String commodityType) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<CollectionEntity> list = collectionMapper.selectList(new QueryWrapper<CollectionEntity>()
                    .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                    .eq(!CharSequenceUtil.isBlank(commodityType), "commodity_type", commodityType));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }

        return pageInfo;
    }

    /**
     * 根据藏品类型和藏品名称 - 获取分页列表
     *
     * @param current       当前页
     * @param pageSize      页面大小
     * @param name          藏品名称
     * @param commodityType 商品类型
     * @return PageInfo<CollectionEntity> pageResult
     */
    @Override
    public PageInfo<CollectionEntity> getPageListByNameAndCommodityType(long current, long pageSize, String name, String commodityType) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<CollectionEntity> list = collectionMapper.selectList(new QueryWrapper<CollectionEntity>()
                    .like(!CharSequenceUtil.isBlank(name), "name", name)
                    .eq(!CharSequenceUtil.isBlank(commodityType), "commodity_type", commodityType));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
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

    /**
     * 减少库存
     *
     * @param collectionId 藏品ID
     * @param count        减少数量
     * @return boolean 是否减少成功
     */
    @Override
    public Boolean reduceStock(String collectionId, Integer count) {
        return collectionMapper.update(null,
                new UpdateWrapper<CollectionEntity>()
                        .ge("stock", count)
                        .eq("id", collectionId)
                        .setSql("stock = stock - " + count)) > 0;
    }

    /**
     * 获取库存
     *
     * @param collectionId 藏品ID
     * @return Long 库存
     */
    @Override
    public Integer getStock(String collectionId) {
        if (CharSequenceUtil.isBlank(collectionId)) {
            return null;
        }
        return collectionMapper.selectById(collectionId).getStock();
    }

    /**
     * 获取指定ID藏品的数量 - 根据藏品ID
     *
     * @param collectionId 藏品ID
     * @return Integer 藏品发行数量
     */
    @Override
    public Integer getQuantityById(String collectionId) {
        CollectionEntity entity = collectionMapper.selectById(collectionId);
        if (Objects.isNull(entity)) {
            return null;
        }
        return entity.getQuantity();
    }

    /**
     * 检查藏品是否存在
     *
     * @param collectionId 藏品ID
     * @return boolean 是否存在
     */
    @Override
    public Boolean checkExist(String collectionId) {
        if (collectionId.isEmpty()) {
            return false;
        }
        return collectionMapper.exists(new QueryWrapper<CollectionEntity>()
                .eq("id", collectionId));
    }

    /**
     * 增加库存
     *
     * @param collectionId 藏品ID
     * @return boolean 是否增加成功
     */
    @Override
    public Boolean increaseStock(String collectionId) {
        return collectionMapper.update(null,
                new UpdateWrapper<CollectionEntity>()
                        .eq("id", collectionId)
                        .setSql("stock = stock + 1")) > 0;
    }

    /**
     * 增加库存
     *
     * @param collectionId 藏品ID
     * @param count        增加数量
     * @return boolean 是否增加成功
     */
    @Override
    public Boolean increaseStock(String collectionId, Integer count) {
        return collectionMapper.update(null,
                new UpdateWrapper<CollectionEntity>()
                        .eq("id", collectionId)
                        .setSql("stock = stock + " + count)) > 0;
    }


}




