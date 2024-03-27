package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.knight.infrastructure.dao.domain.MemberBalanceChangeLogEntity;

import java.sql.Timestamp;

/**
 * @author poboking
 * @description 针对表【member_balance_change_log】的数据库操作Service
 * @createDate 2024-03-07 17:31:42
 */
public interface MemberBalanceChangeLogRepository extends IService<MemberBalanceChangeLogEntity> {

    /**
     * 获取会员余额变动日志的分页列表 - 根据变动类型
     *
     * @param current    当前页码
     * @param size       每页显示的记录数
     * @param changeType 变动类型
     * @return 会员余额变动日志的分页列表
     */
    IPage<MemberBalanceChangeLogEntity> getMemberBCLogPageList(long current, long size, String changeType);

    /**
     * 获取会员余额变动日志的分页列表 - 全部
     *
     * @param current 当前页码
     * @param size    每页显示的记录数
     * @return 会员余额变动日志的分页列表
     */
    public IPage<MemberBalanceChangeLogEntity> getMemberBCLogPageList(long current, long size);

    /**
     * 获取会员余额变动日志的分页列表 - 根据变动类型和会员ID
     *
     * @param current  当前页码
     * @param size     每页显示的记录数
     * @param memberId 会员ID
     * @return 会员余额变动日志的分页列表
     */
    IPage<MemberBalanceChangeLogEntity> getMemberBCLogPageListByMemberId(long current, long size, String memberId);

    /**
     * 获取会员余额变动日志的分页列表 - 根据变动类型和会员ID
     *
     * @param current    当前页码
     * @param size       每页显示的记录数
     * @param changeType 变动类型
     * @param memberId   会员ID
     * @return 会员余额变动日志的分页列表
     */
    IPage<MemberBalanceChangeLogEntity> getMemberBCLogPageListByMemberId(long current, long size, String changeType, String memberId);

/**
     * 创建余额变动日志
     *
     * @param memberId      会员ID
     * @param balanceChange 余额变动
     * @param changeType    变动类型
     * @param orderNo       订单号
     * @param changeTime    变动时间
     * @return 是否创建成功
     */
    Boolean createLog(String memberId, Double balanceChange, String changeType, String orderNo, Timestamp changeTime);
}
