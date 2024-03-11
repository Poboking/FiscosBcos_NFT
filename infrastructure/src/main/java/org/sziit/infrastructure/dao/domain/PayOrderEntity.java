package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName pay_order
 */
@TableName(value = "pay_order")
@Data
public class PayOrderEntity extends Model<PayOrderEntity> implements Serializable {
    /**
     *
     */
    @TableId
    private String id;

    /**
     *
     */
    private Double amount;

    /**
     *
     */
    private String bizOrderNo;

    /**
     *
     */
    private String bizType;

    /**
     *
     */
    private String collectionId;

    /**
     *
     */
    private LocalDateTime createTime;

    /**
     *
     */
    private String memberId;

    /**
     *
     */
    private LocalDateTime orderDeadline;

    /**
     *
     */
    private String orderNo;

    /**
     *
     */
    private String state;

    /**
     *
     */
    private Long version;

    /**
     *
     */
    private LocalDateTime paidTime;

    /**
     *
     */
    private LocalDateTime cancelTime;

    /**
     *
     */
    private String bizMode;

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