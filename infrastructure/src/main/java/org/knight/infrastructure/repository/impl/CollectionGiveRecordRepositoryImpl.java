package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.CollectionGiveRecordEntity;
import org.knight.infrastructure.dao.mapper.CollectionGiveRecordMapper;
import org.knight.infrastructure.repository.CollectionGiveRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author poboking
 * @description 针对表【collection_give_record】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class CollectionGiveRecordRepositoryImpl extends ServiceImpl<CollectionGiveRecordMapper, CollectionGiveRecordEntity>
        implements CollectionGiveRecordRepository {
    private final CollectionGiveRecordMapper collectionGiveRecordMapper;

    @Autowired
    public CollectionGiveRecordRepositoryImpl(CollectionGiveRecordMapper collectionGiveRecordMapper) {
        this.collectionGiveRecordMapper = collectionGiveRecordMapper;
    }

    /**
     * 根据赠送方ID获取赠送记录
     *
     * @param current    当前页
     * @param pageSize   每页大小
     * @param giveFromId 赠送方ID
     * @return 赠送记录分页列表
     */
    @Override
    public IPage<CollectionGiveRecordEntity> getPageListByGiveFromId(long current, long pageSize, String giveFromId) {
        return collectionGiveRecordMapper.selectPage(new Page<>(current, pageSize), new QueryWrapper<CollectionGiveRecordEntity>()
                .eq(!CharSequenceUtil.isBlank(giveFromId), "give_from_id", giveFromId));
    }

    /**
     * 根据接收方ID获取赠送记录
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param giveToId 接收方ID
     * @return 赠送记录分页列表
     */
    @Override
    public IPage<CollectionGiveRecordEntity> getPageListByGiveToId(long current, long pageSize, String giveToId) {
        return collectionGiveRecordMapper.selectPage(new Page<>(current, pageSize), new QueryWrapper<CollectionGiveRecordEntity>()
                .eq(!CharSequenceUtil.isBlank(giveToId), "give_to_id", giveToId));
    }

    /**
     * 根据赠送方ID或接收方ID获取赠送记录
     *
     * @param current    当前页
     * @param pageSize   每页大小
     * @param giveFromId 赠送方ID
     * @param giveToId   接收方ID
     * @return 赠送记录分页列表
     */
    @Override
    public IPage<CollectionGiveRecordEntity> getPageListByGiveToIdOrGiveFormId(long current, long pageSize, String giveFromId, String giveToId) {
        return collectionGiveRecordMapper.selectPage(new Page<>(current, pageSize), new QueryWrapper<CollectionGiveRecordEntity>()
                .or(i -> i.eq( "give_from_id", giveFromId).or().eq("give_to_id", giveToId)));
    }
}




