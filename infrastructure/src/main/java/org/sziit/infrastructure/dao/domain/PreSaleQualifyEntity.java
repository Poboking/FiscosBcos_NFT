package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 预售资格实体类，用于记录用户参与预售活动的资格信息。
 *
 * @TableName pre_sale_qualify
 */
@Data
@TableName(value = "pre_sale_qualify")
public class PreSaleQualifyEntity extends Model<PreSaleQualifyEntity> implements Serializable {
    /**
     * 预售资格记录的唯一标识ID
     */
    @TableId
    private String id;

    /**
     * 业务类型，如预售、抢购等
     */
    private String bizType;

    /**
     * 收藏品ID
     */
    private String collectionId;

    /**
     * 交易时间
     */
    private Timestamp dealTime;

    /**
     * 资格授予时间
     */
    private Timestamp grantTime;

    /**
     * 发行的收藏品ID
     */
    private String issuedCollectionId;

    /**
     * 用户ID
     */
    private String memberId;

    /**
     * 预售前的分钟数，用于倒计时
     */
    private Integer preMinute;

    /**
     * 预售任务ID
     */
    private String preSaleTaskId;

    /**
     * 状态，如待支付、已支付等
     */
    private String state;

    /**
     * 版本号，用于乐观锁控制
     */
    private Long version;

    /**
     * 序列化版本UID，用于类版本控制
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
        PreSaleQualifyEntity other = (PreSaleQualifyEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getBizType() == null ? other.getBizType() == null : this.getBizType().equals(other.getBizType()))
                && (this.getCollectionId() == null ? other.getCollectionId() == null : this.getCollectionId().equals(other.getCollectionId()))
                && (this.getDealTime() == null ? other.getDealTime() == null : this.getDealTime().equals(other.getDealTime()))
                && (this.getGrantTime() == null ? other.getGrantTime() == null : this.getGrantTime().equals(other.getGrantTime()))
                && (this.getIssuedCollectionId() == null ? other.getIssuedCollectionId() == null : this.getIssuedCollectionId().equals(other.getIssuedCollectionId()))
                && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getPreMinute() == null ? other.getPreMinute() == null : this.getPreMinute().equals(other.getPreMinute()))
                && (this.getPreSaleTaskId() == null ? other.getPreSaleTaskId() == null : this.getPreSaleTaskId().equals(other.getPreSaleTaskId()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBizType() == null) ? 0 : getBizType().hashCode());
        result = prime * result + ((getCollectionId() == null) ? 0 : getCollectionId().hashCode());
        result = prime * result + ((getDealTime() == null) ? 0 : getDealTime().hashCode());
        result = prime * result + ((getGrantTime() == null) ? 0 : getGrantTime().hashCode());
        result = prime * result + ((getIssuedCollectionId() == null) ? 0 : getIssuedCollectionId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getPreMinute() == null) ? 0 : getPreMinute().hashCode());
        result = prime * result + ((getPreSaleTaskId() == null) ? 0 : getPreSaleTaskId().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bizType=").append(bizType);
        sb.append(", collectionId=").append(collectionId);
        sb.append(", dealTime=").append(dealTime);
        sb.append(", grantTime=").append(grantTime);
        sb.append(", issuedCollectionId=").append(issuedCollectionId);
        sb.append(", memberId=").append(memberId);
        sb.append(", preMinute=").append(preMinute);
        sb.append(", preSaleTaskId=").append(preSaleTaskId);
        sb.append(", state=").append(state);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}