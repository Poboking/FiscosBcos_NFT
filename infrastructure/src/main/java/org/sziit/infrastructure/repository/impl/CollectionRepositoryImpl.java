package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.CollectionEntity;
import org.sziit.infrastructure.dao.mapper.CollectionMapper;
import org.sziit.infrastructure.repository.CollectionRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author poboking
 * @description 针对表【collection】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
@AllArgsConstructor
public class CollectionRepositoryImpl extends ServiceImpl<CollectionMapper, CollectionEntity>
        implements CollectionRepository {

    @Autowired
    private CollectionMapper collectionMapper;


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
        return  collectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<CollectionEntity>()
                        .eq(Optional.ofNullable(creatorId).isPresent(), "creator_id", creatorId)
                        .eq(Optional.ofNullable(commodityType).isPresent(), "commodity_type", commodityType));
    }
}




