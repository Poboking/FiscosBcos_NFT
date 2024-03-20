package org.sziit.infrastructure.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.sziit.infrastructure.dao.domain.MemberBalanceChangeLogEntity;

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
}
