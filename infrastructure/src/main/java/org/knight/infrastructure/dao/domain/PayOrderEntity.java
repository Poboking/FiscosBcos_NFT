package org.knight.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 支付订单实体类，用于记录支付相关的订单信息
 *
 * @TableName pay_order
 */
@TableName(value = "pay_order")
@Data
public class PayOrderEntity extends Model<PayOrderEntity> implements Serializable {

    /**
     * 支付订单的唯一标识符ID
     */
    @TableId
    private String id;
    /**
     * 订单的支付金额
     */
    private Double amount;
    /**
     * 业务订单号，用于关联具体的业务操作
     */
    private String bizOrderNo;
    /**
     * 业务类型，如购买商品、服务等
     */
    private String bizType;
    /**
     * 发行收藏品ID，如果支付订单与某个发行收藏品相关联
     */
    private String issuedCollectionId;
    /**
     * 收藏ID，如果支付订单与某个收藏相关联
     */
    private String collectionId;
    /**
     * 订单创建的时间
     */
    private Timestamp createTime;
    /**
     * 会员的唯一标识符ID
     */
    private String memberId;
    /**
     * 订单的截止时间，即订单的有效期限
     */
    private Timestamp orderDeadline;
    /**
     * 订单编号，用于唯一标识一个支付订单
     */
    private String orderNo;
    /**
     * 订单的状态，如待支付、已支付、已取消等
     */
    private String state;
    /**
     * 版本号，用于乐观锁机制
     */
    private Long version;
    /**
     * 订单的支付时间
     */
    private Timestamp paidTime;
    /**
     * 订单的取消时间
     */
    private Timestamp cancelTime;
    /**
     * 业务模式，如线上支付、线下支付等
     */
    private String bizMode;

    /**
     * 序列化版本UID，用于类在序列化和反序列化过程中保持版本兼容性
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PayOrderEntity other = (PayOrderEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
                && (this.getBizOrderNo() == null ? other.getBizOrderNo() == null : this.getBizOrderNo().equals(other.getBizOrderNo()))
                && (this.getBizType() == null ? other.getBizType() == null : this.getBizType().equals(other.getBizType()))
                && (this.getIssuedCollectionId() == null ? other.getIssuedCollectionId() == null : this.getIssuedCollectionId().equals(other.getIssuedCollectionId()))
                && (this.getCollectionId() == null ? other.getCollectionId() == null : this.getCollectionId().equals(other.getCollectionId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getOrderDeadline() == null ? other.getOrderDeadline() == null : this.getOrderDeadline().equals(other.getOrderDeadline()))
                && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
                && (this.getPaidTime() == null ? other.getPaidTime() == null : this.getPaidTime().equals(other.getPaidTime()))
                && (this.getCancelTime() == null ? other.getCancelTime() == null : this.getCancelTime().equals(other.getCancelTime()))
                && (this.getBizMode() == null ? other.getBizMode() == null : this.getBizMode().equals(other.getBizMode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getBizOrderNo() == null) ? 0 : getBizOrderNo().hashCode());
        result = prime * result + ((getIssuedCollectionId() == null) ? 0 : getIssuedCollectionId().hashCode());
        result = prime * result + ((getBizType() == null) ? 0 : getBizType().hashCode());
        result = prime * result + ((getCollectionId() == null) ? 0 : getCollectionId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getOrderDeadline() == null) ? 0 : getOrderDeadline().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getPaidTime() == null) ? 0 : getPaidTime().hashCode());
        result = prime * result + ((getCancelTime() == null) ? 0 : getCancelTime().hashCode());
        result = prime * result + ((getBizMode() == null) ? 0 : getBizMode().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", amount=").append(amount);
        sb.append(", bizOrderNo=").append(bizOrderNo);
        sb.append(", bizType=").append(bizType);
        sb.append(", issuedCollectionId=").append(issuedCollectionId);
        sb.append(", collectionId=").append(collectionId);
        sb.append(", createTime=").append(createTime);
        sb.append(", memberId=").append(memberId);
        sb.append(", orderDeadline=").append(orderDeadline);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", state=").append(state);
        sb.append(", version=").append(version);
        sb.append(", paidTime=").append(paidTime);
        sb.append(", cancelTime=").append(cancelTime);
        sb.append(", bizMode=").append(bizMode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}