package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.MemberHoldCollectionEntity;
import org.knight.infrastructure.dao.mapper.MemberHoldCollectionMapper;
import org.knight.infrastructure.repository.MemberHoldCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author poboking
 * @description 针对表【member_hold_collection】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class MemberHoldCollectionRepositoryImpl extends ServiceImpl<MemberHoldCollectionMapper, MemberHoldCollectionEntity>
        implements MemberHoldCollectionRepository {

    @Autowired
    private MemberHoldCollectionMapper memberHoldCollectionMapper;

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<MemberHoldCollectionEntity> 分页结果
     */
    @Override
    public IPage<MemberHoldCollectionEntity> getPageList(long current, long pageSize) {
        return memberHoldCollectionMapper.selectPage(new Page<>(current, pageSize), null);
    }

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 用户id
     * @return IPage<MemberHoldCollectionEntity> 分页结果
     */
    @Override
    public IPage<MemberHoldCollectionEntity> getPageListByMemberId(long current, long pageSize, String memberId) {
        return memberHoldCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberHoldCollectionEntity>().eq("member_id", memberId));
    }

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param name     作品名称
     * @param memberId 用户id
     * @return IPage<MemberHoldCollectionEntity> 分页结果
     */
    @Override
    public IPage<MemberHoldCollectionEntity> getPageListByMemberIdAndName(long current, long pageSize, String name, String memberId) {
        return memberHoldCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberHoldCollectionEntity>().like("name", name).eq("member_id", memberId));
    }

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param status   藏品转售状态
     * @param memberId 用户id
     * @return IPage<MemberHoldCollectionEntity> 分页结果
     */
    @Override
    public IPage<MemberHoldCollectionEntity> getPageListByMemberIdAndStatus(long current, long pageSize, String status, String memberId) {
        return memberHoldCollectionMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<MemberHoldCollectionEntity>().eq("status", status).eq("member_id", memberId));
    }

    /**
     * 检查藏品是否存在
     *
     * @param holdCollectionId 持有收藏品id
     * @return MemberHoldCollectionEntity
     */
    @Override
    public MemberHoldCollectionEntity checkExist(String holdCollectionId, String memberId) {
        return memberHoldCollectionMapper.selectOne(new QueryWrapper<MemberHoldCollectionEntity>()
                .eq("id", holdCollectionId)
                .eq("member_id", memberId));
    }
}




