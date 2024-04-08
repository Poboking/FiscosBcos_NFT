package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.dao.domain.PayOrderEntity;
import org.knight.infrastructure.dao.mapper.PayOrderMapper;
import org.knight.infrastructure.repository.PayOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
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


    // 获取我的支付订单查询结果 - 默认按照创建时间进行降序处理

    /**
     * 获取我的支付订单分页列表 - 全部
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return 分页列表
     */
    @Override
    public IPage<PayOrderEntity> getPayOrderPageList(long current, long pageSize) {
        return payOrderMapper.selectPage(new Page<>(current, pageSize),
                new QueryWrapper<PayOrderEntity>().orderByDesc("create_time"));
    }

    /**
     * 获取我的支付订单分页列表 - 根据订单状态
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param state   订单状态
     * @return 分页列表
     */
    @Override
    public IPage<PayOrderEntity> getPayOrderPageList(long current, long pageSize, String state) {
        return payOrderMapper.selectPage(new Page<>(current, pageSize), new QueryWrapper<PayOrderEntity>()
                .eq(!CharSequenceUtil.isBlank(state), "state", state)
                .orderByDesc("create_time"));
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
                .eq(!CharSequenceUtil.isBlank(memberId), "member_id", memberId)
                .orderByDesc("create_time"));
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
                .eq(!CharSequenceUtil.isBlank(memberId), "member_id", memberId)
                .eq(!CharSequenceUtil.isBlank(memberId), "state", status)
                .orderByDesc("create_time"));
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
        if (CharSequenceUtil.isBlank(id) || CharSequenceUtil.isBlank(state) || updateTime == null) {
            return false;
        }
        if (state.equals(NftConstants.支付订单状态_已付款) && getSateById(id).equals(NftConstants.支付订单状态_已付款)) {
            return true;
        }
        if (state.equals(NftConstants.支付订单状态_已取消) && getSateById(id).equals(NftConstants.支付订单状态_已取消)) {
            return true;
        }
        if (state.equals(NftConstants.支付订单超时取消) && getSateById(id).equals(NftConstants.支付订单超时取消)) {
            return true;
        }
        if (state.equals(NftConstants.支付订单状态_已取消) && getSateById(id).equals(NftConstants.支付订单状态_已付款)) {
            // TODO: 2024/4/3 21:11 退货逻辑待实现
            return false;
        }
        if (state.equals(NftConstants.支付订单状态_已付款) && getSateById(id).equals(NftConstants.支付订单状态_已取消)) {
            return false;
        }
        if (state.equals(NftConstants.支付订单状态_已付款) && updateTime.after(payOrderMapper.selectById(id).getOrderDeadline())) {
            payOrderMapper.update(new UpdateWrapper<PayOrderEntity>()
                    .eq("id", id)
                    .set("state", NftConstants.支付订单超时取消));
            return false;
        }
        if (state.equals(NftConstants.支付订单超时取消) && updateTime.after(payOrderMapper.selectById(id).getOrderDeadline())) {
            return payOrderMapper.update(new UpdateWrapper<PayOrderEntity>()
                    .eq("id", id)
                    .set("state", NftConstants.支付订单超时取消)) > 0;
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
                    .set("paid_time", updateTime)
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
//        return payOrderMapper.selectList(new QueryWrapper<PayOrderEntity>()
//                        .eq("biz_mode", bizMode)
//                        .eq("state", NftConstants.支付订单状态_已付款)
//                        .like(Optional.ofNullable(date).isPresent(), "paid_time", date))
//                .stream()
//                .map(PayOrderEntity::getAmount)
//                .reduce(Double::sum)
//                .orElse(0.0);
        QueryWrapper<PayOrderEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("biz_mode", bizMode)
                .eq("state", NftConstants.支付订单状态_已付款);
        // 如果日期参数不为空，则添加日期条件
        if (date != null && !date.isEmpty()) {
            queryWrapper.apply("DATE_FORMAT(paid_time, '%Y-%m-%d') LIKE {0}", date + "%");
            //queryWrapper.apply("DATE_FORMAT(paid_time, '%Y-%m-%d') = DATE_FORMAT({0}, '%Y-%m-%d')", date);
        }
        List<PayOrderEntity> orderList = payOrderMapper.selectList(queryWrapper);
        // 计算成功交易总额
        return orderList.stream()
                .mapToDouble(PayOrderEntity::getAmount)
                .sum();
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
                .like(Optional.ofNullable(date).isPresent(), "pay_time", date));
    }

    /**
     * 获取订单 - 根据藏品ID 和 用户ID
     *
     * @param collectionId 藏品ID
     * @param memberId     用户ID
     * @return PayOrderEntity 订单
     */
    @Override
    public PayOrderEntity checkExistByCollectionIdAndMemberId(String collectionId, String memberId) {
        return payOrderMapper.selectList(new QueryWrapper<PayOrderEntity>()
                .eq("collection_id", collectionId)
                .eq("member_id", memberId)
                .eq("state", NftConstants.支付订单状态_待付款)).stream().findFirst().orElse(null);
    }

    /**
     * 获取订单状态 - 根据ID
     *
     * @param id 订单ID
     * @return String 订单状态
     */
    @Override
    public String getSateById(String id) {
        if (CharSequenceUtil.isBlank(id)) {
            return null;
        }
        PayOrderEntity order = payOrderMapper.selectById(id);
        if (order == null) {
            return null;
        }
        return order.getState();
    }


}




