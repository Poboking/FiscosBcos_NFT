package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.MysteryBoxCommodityEntity;
import org.knight.infrastructure.dao.mapper.MysteryBoxCommodityMapper;
import org.knight.infrastructure.repository.MysteryBoxCommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author poboking
 * @description 针对表【mystery_box_commodity】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class MysteryBoxCommodityRepositoryImpl extends ServiceImpl<MysteryBoxCommodityMapper, MysteryBoxCommodityEntity>
        implements MysteryBoxCommodityRepository {

    @Autowired
    private MysteryBoxCommodityMapper mysteryBoxCommodityMapper;


    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 用户id
     * @return IPage<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public IPage<MysteryBoxCommodityEntity> getPageListByMemberId(long current, long pageSize, String memberId) {
        return mysteryBoxCommodityMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MysteryBoxCommodityEntity>().eq("member_id", memberId));
    }

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param name     盲盒名称
     * @param memberId 用户id
     * @return IPage<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public IPage<MysteryBoxCommodityEntity> getPageListByMemberIdAndName(long current, long pageSize, String name, String memberId) {
        return mysteryBoxCommodityMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MysteryBoxCommodityEntity>().like("name", name)
                        .eq("member_id", memberId));
    }

    /**
     * 分页查询 - 按价格降序
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public IPage<MysteryBoxCommodityEntity> getPriceOrderDescPageList(long current, long pageSize) {
        return mysteryBoxCommodityMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MysteryBoxCommodityEntity>().orderByDesc("price"));
    }

    /**
     * 分页查询 - 按价格降序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return IPage<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public IPage<MysteryBoxCommodityEntity> getPriceOrderDescPageListByCreatorId(long current, long pageSize, String creatorId) {
        return mysteryBoxCommodityMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MysteryBoxCommodityEntity>().eq("creator_id", creatorId)
                        .orderByDesc("price"));
    }

    /**
     * 分页查询 - 按价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return IPage<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public IPage<MysteryBoxCommodityEntity> getPriceOrderDescPageListByCollectionId(long current, long pageSize, String collectionId) {
        return mysteryBoxCommodityMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MysteryBoxCommodityEntity>()
                        .eq(Optional.ofNullable(collectionId).isPresent(), "collection_id", collectionId)
                        .orderByDesc("price"));
    }

    /**
     * 分页查询 - 按价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param creatorId    创建者id
     * @param collectionId 收藏品id
     * @return IPage<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public IPage<MysteryBoxCommodityEntity> getPriceOrderDescPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId) {
        return mysteryBoxCommodityMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MysteryBoxCommodityEntity>()
                        .eq(Optional.ofNullable(creatorId).isPresent(), "creator_id", creatorId)
                        .eq(Optional.ofNullable(collectionId).isPresent(), "collection_id", collectionId)
                        .orderByDesc("price"));
    }

    /**
     * 分页查询 - 按价格升序
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public IPage<MysteryBoxCommodityEntity> getPriceOrderAscPageList(long current, long pageSize) {
        return mysteryBoxCommodityMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MysteryBoxCommodityEntity>().orderByAsc("price"));
    }

    /**
     * 分页查询 - 按价格升序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return IPage<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public IPage<MysteryBoxCommodityEntity> getPriceOrderAscPageListByCreatorId(long current, long pageSize, String creatorId) {
        return mysteryBoxCommodityMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MysteryBoxCommodityEntity>()
                        .eq(Optional.ofNullable(creatorId).isPresent(), "creator_id", creatorId)
                        .orderByAsc("price"));
    }

    /**
     * 分页查询 - 按价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return IPage<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public IPage<MysteryBoxCommodityEntity> getPriceOrderAscPageListByCollectionId(long current, long pageSize, String collectionId) {
        return mysteryBoxCommodityMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MysteryBoxCommodityEntity>()
                        .eq(Optional.ofNullable(collectionId).isPresent(), "collection_id", collectionId)
                        .orderByAsc("price"));
    }

    /**
     * 分页查询 - 按价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param creatorId    创建者id
     * @param collectionId 收藏品id
     * @return IPage<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public IPage<MysteryBoxCommodityEntity> getPriceOrderAscPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId) {
        return mysteryBoxCommodityMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MysteryBoxCommodityEntity>()
                        .eq(Optional.ofNullable(creatorId).isPresent(), "creator_id", creatorId)
                        .eq(Optional.ofNullable(collectionId).isPresent(), "collection_id", collectionId)
                        .orderByAsc("price"));
    }

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public IPage<MysteryBoxCommodityEntity> getPageList(long current, long pageSize) {
        return mysteryBoxCommodityMapper.selectPage(new Page<>(current, pageSize), null);
    }
}




