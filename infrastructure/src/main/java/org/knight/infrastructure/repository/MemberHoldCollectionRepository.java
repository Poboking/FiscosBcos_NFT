package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.knight.infrastructure.dao.domain.MemberHoldCollectionEntity;

/**
 * @author poboking
 * @description 针对表【member_hold_collection】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface MemberHoldCollectionRepository extends IService<MemberHoldCollectionEntity> {
    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return IPage<MemberHoldCollectionEntity> 分页结果
     */
    IPage<MemberHoldCollectionEntity> getPageList(long current, long pageSize);

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param memberId 用户id
     * @return IPage<MemberHoldCollectionEntity> 分页结果
     */
    IPage<MemberHoldCollectionEntity> getPageListByMemberId(long current, long pageSize, String memberId);

    /**
     * 分页查询
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param name     作品名称
     * @param memberId 用户id
     * @return IPage<MemberHoldCollectionEntity> 分页结果
     */
    IPage<MemberHoldCollectionEntity> getPageListByMemberIdAndName(long current, long pageSize, String name, String memberId);

    /**
     * @param current  当前页
     * @param pageSize 每页大小
     * @param status   藏品转售状态
     * @param memberId 用户id
     * @return IPage<MemberHoldCollectionEntity> 分页结果
     */
    IPage<MemberHoldCollectionEntity> getPageListByMemberIdAndStatus(long current, long pageSize, String status, String memberId);


}
