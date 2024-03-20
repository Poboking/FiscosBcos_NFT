package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @TableName collection_give_record
 * 收藏赠送记录实体类，用于记录用户之间收藏品的赠送行为
 */
@TableName(value = "collection_give_record")
@Data
public class CollectionGiveRecordEntity extends Model<CollectionGiveRecordEntity> implements Serializable {
    /**
     * 赠送记录的唯一标识符ID
     */
    @TableId
    private String id;

    /**
     * 赠送方的用户ID
     */
    private String giveFromId;

    /**
     * 赠送行为发生的时间
     */
    private Timestamp giveTime;

    /**
     * 接收方的用户ID
     */
    private String giveToId;

    /**
     * 被赠送的收藏品ID
     */
    private String holdCollectionId;

    /**
     * 关联的订单编号，用于追踪赠送行为的具体交易
     */
    private String orderNo;

    /**
     * 版本号，用于乐观锁机制
     */
    private Long version;

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
        CollectionGiveRecordEntity other = (CollectionGiveRecordEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getGiveFromId() == null ? other.getGiveFromId() == null : this.getGiveFromId().equals(other.getGiveFromId()))
                && (this.getGiveTime() == null ? other.getGiveTime() == null : this.getGiveTime().equals(other.getGiveTime()))
                && (this.getGiveToId() == null ? other.getGiveToId() == null : this.getGiveToId().equals(other.getGiveToId()))
                && (this.getHoldCollectionId() == null ? other.getHoldCollectionId() == null : this.getHoldCollectionId().equals(other.getHoldCollectionId()))
                && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGiveFromId() == null) ? 0 : getGiveFromId().hashCode());
        result = prime * result + ((getGiveTime() == null) ? 0 : getGiveTime().hashCode());
        result = prime * result + ((getGiveToId() == null) ? 0 : getGiveToId().hashCode());
        result = prime * result + ((getHoldCollectionId() == null) ? 0 : getHoldCollectionId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
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
        sb.append(", giveFromId=").append(giveFromId);
        sb.append(", giveTime=").append(giveTime);
        sb.append(", giveToId=").append(giveToId);
        sb.append(", holdCollectionId=").append(holdCollectionId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}