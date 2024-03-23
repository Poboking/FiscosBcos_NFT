package org.sziit.infrastructure.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.sziit.infrastructure.dao.domain.PayOrderEntity;

import java.sql.Timestamp;

/**
 * @author poboking
 * @description 针对表【pay_order】的数据库操作Service
 * @createDate 2024-03-07 17:31:43
 */
public interface PayOrderRepository extends IService<PayOrderEntity> {

    /**
     * 获取我的支付订单分页列表 - 全部
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return 分页列表
     */
    IPage<PayOrderEntity> getPayOrderPageList(long current, long pageSize);

    /**
     * 获取我的支付订单分页列表 - 根据订单状态
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param status   订单状态
     * @return 分页列表
     */
    IPage<PayOrderEntity> getPayOrderPageList(long current, long pageSize, String status);

    /**
     * 获取我的支付订单分页列表 - 全部
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return 分页列表
     */
    IPage<PayOrderEntity> getPayOrderPageListByMemberId(long current, long pageSize, String memberId);

    /**
     * 获取我的支付订单分页列表 - 根据订单状态
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param status   订单状态
     * @return 分页列表
     */
    IPage<PayOrderEntity> getPayOrderPageListByMemberIdAndStatus(long current, long pageSize, String memberId, String status);


    /**
     * 更新订单状态
     *
     * @param id         订单ID
     * @param state      订单状态
     * @param updateTime 更新时间
     * @return Boolean 是否更新成功
     */
    Boolean updateStateById(String id, String state, Timestamp updateTime);
}
