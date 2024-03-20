package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sziit.infrastructure.dao.domain.MemberBalanceChangeLogEntity;
import org.sziit.infrastructure.dao.mapper.MemberBalanceChangeLogMapper;
import org.sziit.infrastructure.repository.MemberBalanceChangeLogRepository;

import java.util.Optional;

/**
 * @author poboking
 * @description 针对表【member_balance_change_log】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:42
 */
@Service
public class MemberBalanceChangeLogRepositoryImpl extends ServiceImpl<MemberBalanceChangeLogMapper, MemberBalanceChangeLogEntity>
        implements MemberBalanceChangeLogRepository {
    @Autowired
    private MemberBalanceChangeLogMapper memberBalanceChangeLogMapper;

    /**
     * 获取会员余额变动日志的分页列表 - 根据变动类型
     *
     * @param current    当前页码
     * @param size       每页显示的记录数
     * @param changeType 变动类型
     * @return 会员余额变动日志的分页列表
     */
    @Override
    public IPage<MemberBalanceChangeLogEntity> getMemberBCLogPageList(long current, long size, String changeType) {
        return  memberBalanceChangeLogMapper.selectPage(new Page<>(current, size), new QueryWrapper<MemberBalanceChangeLogEntity>()
                .eq(Optional.ofNullable(changeType).isPresent(), "change_type", changeType));
    }

    /**
     * 获取会员余额变动日志的分页列表 - 全部
     *
     * @param current 当前页码
     * @param size    每页显示的记录数
     * @return 会员余额变动日志的分页列表
     */
    @Override
    public IPage<MemberBalanceChangeLogEntity> getMemberBCLogPageList(long current, long size) {
        return memberBalanceChangeLogMapper.selectPage(new Page<>(current, size), null);
    }

    /**
     * 获取会员余额变动日志的分页列表 - 根据变动类型和会员ID
     *
     * @param current  当前页码
     * @param size     每页显示的记录数
     * @param memberId 会员ID
     * @return 会员余额变动日志的分页列表
     */
    @Override
    public IPage<MemberBalanceChangeLogEntity> getMemberBCLogPageListByMemberId(long current, long size, String memberId) {
        return memberBalanceChangeLogMapper.selectPage(new Page<>(current, size), new QueryWrapper<MemberBalanceChangeLogEntity>()
                .eq(Optional.ofNullable(memberId).isPresent(), "member_id", memberId));
    }

    /**
     * 获取会员余额变动日志的分页列表 - 根据变动类型和会员ID
     *
     * @param current    当前页码
     * @param size       每页显示的记录数
     * @param changeType 变动类型
     * @param memberId   会员ID
     * @return 会员余额变动日志的分页列表
     */
    @Override
    public IPage<MemberBalanceChangeLogEntity> getMemberBCLogPageListByMemberId(long current, long size, String changeType, String memberId) {
        return memberBalanceChangeLogMapper.selectPage(new Page<>(current, size), new QueryWrapper<MemberBalanceChangeLogEntity>()
                .eq(Optional.ofNullable(changeType).isPresent(), "change_type", changeType)
                .eq(Optional.ofNullable(memberId).isPresent(), "member_id", memberId));
    }
}




