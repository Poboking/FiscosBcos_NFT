package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import org.knight.infrastructure.dao.domain.PayOrderEntity;

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
    PageInfo<PayOrderEntity> getPayOrderPageList(long current, long pageSize);

    /**
     * 获取我的支付订单分页列表 - 根据订单状态
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param status   订单状态
     * @return 分页列表
     */
    PageInfo<PayOrderEntity> getPayOrderPageList(long current, long pageSize, String status);

    /**
     * 获取我的支付订单分页列表 - 全部
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @return 分页列表
     */
    PageInfo<PayOrderEntity> getPayOrderPageListByMemberId(long current, long pageSize, String memberId);

    /**
     * 获取我的支付订单分页列表 - 根据订单状态
     *
     * @param current  当前页
     * @param pageSize 每页大小
     * @param status   订单状态
     * @return 分页列表
     */
    PageInfo<PayOrderEntity> getPayOrderPageListByMemberIdAndStatus(long current, long pageSize, String memberId, String status);


    /**
     * 更新订单状态
     *
     * @param id         订单ID
     * @param state      订单状态
     * @param updateTime 更新时间
     * @return Boolean 是否更新成功
     */
    Boolean updateStateById(String id, String state, Timestamp updateTime);

    /**
     * 获取成功交易总额 - 根据日期
     *
     * @param bizMode 业务模式
     * @param date    日期
     * @return double 订单金额
     */
    Double getSuccessAmountByDate(String bizMode, String date);

    /**
     * 获取成功交易总数 - 根据日期
     *
     * @param bizMode 业务模式
     * @param date    日期
     * @return Integer 订单数量
     */
    Long getSuccessCountByDate(String bizMode, String date);

    /**
     * 获取订单 - 根据藏品ID 和 用户ID
     *
     * @param collectionId 藏品ID
     * @param memberId     用户ID
     * @return PayOrderEntity 订单
     */
    PayOrderEntity checkExistByCollectionIdAndMemberId(String collectionId, String memberId);

    /**
     * 获取订单状态 - 根据ID
     *
     * @param id 订单ID
     * @return String 订单状态
     */
    String getSateById(String id);
}
