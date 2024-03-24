package org.sziit.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sziit.infrastructure.common.NftConstants;
import org.sziit.infrastructure.dao.domain.PayOrderEntity;
import org.sziit.infrastructure.dao.mapper.PayOrderMapper;
import org.sziit.infrastructure.repository.PayOrderRepository;

import java.sql.Timestamp;
import java.util.Optional;

/**
 * @author poboking
 * @description 针对表【pay_order】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:43
 */
@Service
public class PayOrderRepositoryImpl extends ServiceImpl<PayOrderMapper, PayOrderEntity>
        implements PayOrderRepository {
    private final PayOrderMapper payOrderMapper;

    @Autowired
    public PayOrderRepositoryImpl(PayOrderMapper payOrderMapper) {
        this.payOrderMapper = payOrderMapper;
    }

    /**
     * 获取我的支付订单分页列表 - 全部
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return 分页列表
     */
    @Override
    public IPage<PayOrderEntity> getPayOrderPageList(long current, long pageSize) {
        return payOrderMapper.selectPage(new Page<>(current, pageSize), null);
    }

    /**
     * 获取我的支付订单分页列表 - 根据订单状态
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param status   订单状态
     * @return 分页列表
     */
    @Override
    public IPage<PayOrderEntity> getPayOrderPageList(long current, long pageSize, String status) {
        return payOrderMapper.selectPage(new Page<>(current, pageSize), new QueryWrapper<PayOrderEntity>()
                .eq(Optional.ofNullable(status).isPresent(), "status", status));
    }

    /**
     * 获取我的支付订单分页列表 - 根据会员ID获取全部
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return 分页列表
     */
    @Override
    public IPage<PayOrderEntity> getPayOrderPageListByMemberId(long current, long pageSize, String memberId) {
        return payOrderMapper.selectPage(new Page<>(current, pageSize), new QueryWrapper<PayOrderEntity>()
                .eq(Optional.ofNullable(memberId).isPresent(), "member_id", memberId));
    }

    /**
     * 获取我的支付订单分页列表 - 根据订单状态 & 会员ID
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param status   订单状态
     * @return 分页列表
     */
    @Override
    public IPage<PayOrderEntity> getPayOrderPageListByMemberIdAndStatus(long current, long pageSize, String memberId, String status) {
        return payOrderMapper.selectPage(new Page<>(current, pageSize), new QueryWrapper<PayOrderEntity>()
                .eq(Optional.ofNullable(memberId).isPresent(), "member_id", memberId)
                .eq(Optional.ofNullable(status).isPresent(), "state", status));
    }

    /**
     * 更新订单状态
     *
     * @param id         订单ID
     * @param state      订单状态
     * @param updateTime 更新时间
     * @return 是否更新成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStateById(String id, String state, Timestamp updateTime) {
        if (id == null || state == null) {
            return false;
        }
        if (updateTime.after(payOrderMapper.selectById(id).getOrderDeadline())) {
            return false;
        }
        if (state.equals(NftConstants.支付订单状态_已取消)) {
            return payOrderMapper.update(new UpdateWrapper<PayOrderEntity>()
                    .eq("id", id)
                    .set("cancel_time", updateTime)
                    .set("state", state)) > 0;
        }
        if (state.equals(NftConstants.支付订单状态_已付款)) {
            return payOrderMapper.update(new UpdateWrapper<PayOrderEntity>()
                    .eq("id", id)
                    .set("pay_time", updateTime)
                    .set("state", state)) > 0;
        }
        return false;
    }

    /**
     * 获取成功交易总额 - 根据日期
     *
     * @param bizMode 业务模式
     * @param date    日期
     * @return 订单数量
     */
    @Override
    public Double getSuccessAmountByDate(String bizMode, String date) {
        return payOrderMapper.selectList(new QueryWrapper<PayOrderEntity>()
                .eq("biz_mode", bizMode)
                .eq("state", NftConstants.支付订单状态_已付款)
                .like(Optional.ofNullable(date).isPresent(),"paid_time", date))
                .stream()
                .map(PayOrderEntity::getAmount)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    /**
     * 获取成功交易总数 - 根据日期
     *
     * @param bizMode 业务模式
     * @param date    日期
     * @return Integer 订单数量
     */
    @Override
    public Long getSuccessCountByDate(String bizMode, String date) {
        return count(new QueryWrapper<PayOrderEntity>()
                .eq("biz_mode", bizMode)
                .eq("state", NftConstants.支付订单状态_已付款)
                .like(Optional.ofNullable(date).isPresent(),"pay_time", date));
    }


}




