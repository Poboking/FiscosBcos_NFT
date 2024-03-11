package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName collection_give_record
 */
@TableName(value = "collection_give_record")
@Data
public class CollectionGiveRecordEntity extends Model<CollectionGiveRecordEntity> implements Serializable {
    /**
     *
     */
    @TableId
    private String id;

    /**
     *
     */
    private String giveFromId;

    /**
     *
     */
    private LocalDateTime giveTime;

    /**
     *
     */
    private String giveToId;

    /**
     *
     */
    private String holdCollectionId;

    /**
     *
     */
    private String orderNo;

    /**
     *
     */
    private Long version;

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