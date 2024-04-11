package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.dao.domain.MysteryBoxCommodityEntity;
import org.knight.infrastructure.dao.mapper.MysteryBoxCommodityMapper;
import org.knight.infrastructure.repository.MysteryBoxCommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author poboking
 * @description 针对表【mystery_box_commodity】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
@SuppressWarnings("all")
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
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public PageInfo<MysteryBoxCommodityEntity> getPageListByMemberId(long current, long pageSize, String memberId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MysteryBoxCommodityEntity> list = mysteryBoxCommodityMapper.selectList(
                    new QueryWrapper<MysteryBoxCommodityEntity>().eq("member_id", memberId));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param name     盲盒名称
     * @param memberId 用户id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public PageInfo<MysteryBoxCommodityEntity> getPageListByMemberIdAndName(long current, long pageSize, String name, String memberId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MysteryBoxCommodityEntity> list = mysteryBoxCommodityMapper.selectList(
                    new QueryWrapper<MysteryBoxCommodityEntity>().like("name", name)
                            .eq("member_id", memberId));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;

    }

    /**
     * 分页查询 - 按价格降序
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderDescPageList(long current, long pageSize) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MysteryBoxCommodityEntity> list = mysteryBoxCommodityMapper.selectList(
                    new QueryWrapper<MysteryBoxCommodityEntity>().orderByDesc("price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询 - 按价格降序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderDescPageListByCreatorId(long current, long pageSize, String creatorId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MysteryBoxCommodityEntity> list = mysteryBoxCommodityMapper.selectList(
                    new QueryWrapper<MysteryBoxCommodityEntity>().eq("creator_id", creatorId)
                            .orderByDesc("price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询 - 按价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderDescPageListByCollectionId(long current, long pageSize, String collectionId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MysteryBoxCommodityEntity> list = mysteryBoxCommodityMapper.selectList(
                    new QueryWrapper<MysteryBoxCommodityEntity>()
                            .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                            .orderByDesc("price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询 - 按价格降序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param creatorId    创建者id
     * @param collectionId 收藏品id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderDescPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MysteryBoxCommodityEntity> list = mysteryBoxCommodityMapper.selectList(
                    new QueryWrapper<MysteryBoxCommodityEntity>()
                            .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                            .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                            .orderByDesc("price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询 - 按价格升序
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderAscPageList(long current, long pageSize) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MysteryBoxCommodityEntity> list = mysteryBoxCommodityMapper.selectList(
                    new QueryWrapper<MysteryBoxCommodityEntity>().orderByAsc("price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询 - 按价格升序
     *
     * @param current   当前页
     * @param pageSize  每页大小
     * @param creatorId 创建者id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderAscPageListByCreatorId(long current, long pageSize, String creatorId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MysteryBoxCommodityEntity> list = mysteryBoxCommodityMapper.selectList(
                    new QueryWrapper<MysteryBoxCommodityEntity>()
                            .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                            .orderByAsc("price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询 - 按价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param collectionId 收藏品id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderAscPageListByCollectionId(long current, long pageSize, String collectionId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MysteryBoxCommodityEntity> list = mysteryBoxCommodityMapper.selectList(new QueryWrapper<MysteryBoxCommodityEntity>()
                    .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                    .orderByAsc("price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询 - 按价格升序
     *
     * @param current      当前页
     * @param pageSize     每页大小
     * @param creatorId    创建者id
     * @param collectionId 收藏品id
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public PageInfo<MysteryBoxCommodityEntity> getPriceOrderAscPageListByCreatorIdAndCollectionId(long current, long pageSize, String creatorId, String collectionId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MysteryBoxCommodityEntity> list = mysteryBoxCommodityMapper.selectList(new QueryWrapper<MysteryBoxCommodityEntity>()
                    .eq(!CharSequenceUtil.isBlank(creatorId), "creator_id", creatorId)
                    .eq(!CharSequenceUtil.isBlank(collectionId), "collection_id", collectionId)
                    .orderByAsc("price"));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return PageInfo<MysteryBoxCommodityEntity> 分页结果
     */
    @Override
    public PageInfo<MysteryBoxCommodityEntity> getPageList(long current, long pageSize) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MysteryBoxCommodityEntity> list = mysteryBoxCommodityMapper.selectList(null);
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }
}




