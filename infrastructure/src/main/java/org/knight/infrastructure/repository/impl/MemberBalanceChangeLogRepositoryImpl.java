package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.dao.domain.MemberBalanceChangeLogEntity;
import org.knight.infrastructure.dao.mapper.MemberBalanceChangeLogMapper;
import org.knight.infrastructure.dao.mapper.MemberMapper;
import org.knight.infrastructure.repository.MemberBalanceChangeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author poboking
 * @description 针对表【member_balance_change_log】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:42
 */
@Service
public class MemberBalanceChangeLogRepositoryImpl extends ServiceImpl<MemberBalanceChangeLogMapper, MemberBalanceChangeLogEntity>
        implements MemberBalanceChangeLogRepository {
    private final MemberBalanceChangeLogMapper memberBalanceChangeLogMapper;

    private final MemberMapper memberMapper;

    @Autowired
    public MemberBalanceChangeLogRepositoryImpl(MemberBalanceChangeLogMapper memberBalanceChangeLogMapper, MemberMapper memberMapper) {
        this.memberBalanceChangeLogMapper = memberBalanceChangeLogMapper;
        this.memberMapper = memberMapper;
    }

    /**
     * 获取会员余额变动日志的分页列表 - 根据变动类型
     *
     * @param current    当前页码
     * @param pageSize   每页显示的记录数
     * @param changeType 变动类型
     * @return 会员余额变动日志的分页列表
     */
    @Override
    public PageInfo<MemberBalanceChangeLogEntity> getMemberBCLogPageList(long current, long pageSize, String changeType) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberBalanceChangeLogEntity> list = memberBalanceChangeLogMapper.
                    selectList(new QueryWrapper<MemberBalanceChangeLogEntity>()
                            .eq(!CharSequenceUtil.isBlank(changeType), "change_type", changeType));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取会员余额变动日志的分页列表 - 全部
     *
     * @param current  当前页码
     * @param pageSize 每页显示的记录数
     * @return 会员余额变动日志的分页列表
     */
    @Override
    public PageInfo<MemberBalanceChangeLogEntity> getMemberBCLogPageList(long current, long pageSize) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberBalanceChangeLogEntity> list = memberBalanceChangeLogMapper
                    .selectList(new Page<>(current, pageSize), null);
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;

    }

    /**
     * 获取会员余额变动日志的分页列表 - 根据变动类型和会员ID
     *
     * @param current  当前页码
     * @param pageSize 每页显示的记录数
     * @param memberId 会员ID
     * @return 会员余额变动日志的分页列表
     */
    @Override
    public PageInfo<MemberBalanceChangeLogEntity> getMemberBCLogPageListByMemberId(long current, long pageSize, String memberId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberBalanceChangeLogEntity> list = memberBalanceChangeLogMapper.selectList(new QueryWrapper<MemberBalanceChangeLogEntity>()
                    .eq(!CharSequenceUtil.isBlank(memberId), "member_id", memberId));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;
    }

    /**
     * 获取会员余额变动日志的分页列表 - 根据变动类型和会员ID
     *
     * @param current    当前页码
     * @param pageSize   每页显示的记录数
     * @param changeType 变动类型
     * @param memberId   会员ID
     * @return 会员余额变动日志的分页列表
     */
    @Override
    public PageInfo<MemberBalanceChangeLogEntity> getMemberBCLogPageListByMemberId(long current, long pageSize, String changeType, String memberId) {
        PageInfo pageInfo = null;
        try {
            PageHelper.startPage((int) current, (int) pageSize);
            List<MemberBalanceChangeLogEntity> list = memberBalanceChangeLogMapper.selectList(new QueryWrapper<MemberBalanceChangeLogEntity>()
                    .eq(!CharSequenceUtil.isBlank(changeType), "change_type", changeType)
                    .eq(!CharSequenceUtil.isBlank(memberId), "member_id", memberId));
            pageInfo = new PageInfo(list, (int) pageSize);
        } finally {
            PageHelper.clearPage();
        }
        return pageInfo;

    }

    /**
     * 创建余额变动日志 - 需在Balance改变之后方可调用
     *
     * @param memberId      会员ID
     * @param balanceChange 余额变动
     * @param changeType    变动类型
     * @param orderNo       订单号
     * @param changeTime    变动时间
     * @return 是否创建成功
     */
    @Override
    public Boolean createLog(String memberId, Double balanceChange, String changeType, String orderNo, Timestamp changeTime) {
        Double balance = memberMapper.selectById(memberId).getBalance();
        MemberBalanceChangeLogEntity entity = MemberBalanceChangeLogEntity.builder()
                .memberId(memberId)
                .balanceChange(balanceChange)
                .balanceAfter(balance + balanceChange)
                .balanceBefore(balance)
                .changeType(changeType)
                .bizOrderNo(orderNo)
                .changeTime(changeTime)
                .note("余额变动")
                .build();
        return memberBalanceChangeLogMapper.insert(entity) > 0;
    }
}




