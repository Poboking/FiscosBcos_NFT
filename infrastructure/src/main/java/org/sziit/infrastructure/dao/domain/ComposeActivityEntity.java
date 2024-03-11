package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName compose_activity
 */
@TableName(value = "compose_activity")
@Data
public class ComposeActivityEntity extends Model<ComposeActivityEntity> implements Serializable {
    /**
     *
     */
    @TableId
    private String id;

    /**
     *
     */
    private LocalDateTime activityTimeEnd;

    /**
     *
     */
    private LocalDateTime activityTimeStart;

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
    private Boolean deletedFlag;

    /**
     *
     */
    private LocalDateTime deletedTime;

    /**
     *
     */
    private Integer quantity;

    /**
     *
     */
    private Integer stock;

    /**
     *
     */
    private String title;

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
        ComposeActivityEntity other = (ComposeActivityEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getActivityTimeEnd() == null ? other.getActivityTimeEnd() == null : this.getActivityTimeEnd().equals(other.getActivityTimeEnd()))
                && (this.getActivityTimeStart() == null ? other.getActivityTimeStart() == null : this.getActivityTimeStart().equals(other.getActivityTimeStart()))
                && (this.getCollectionId() == null ? other.getCollectionId() == null : this.getCollectionId().equals(other.getCollectionId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getDeletedFlag() == null ? other.getDeletedFlag() == null : this.getDeletedFlag().equals(other.getDeletedFlag()))
                && (this.getDeletedTime() == null ? other.getDeletedTime() == null : this.getDeletedTime().equals(other.getDeletedTime()))
                && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
                && (this.getStock() == null ? other.getStock() == null : this.getStock().equals(other.getStock()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getActivityTimeEnd() == null) ? 0 : getActivityTimeEnd().hashCode());
        result = prime * result + ((getActivityTimeStart() == null) ? 0 : getActivityTimeStart().hashCode());
        result = prime * result + ((getCollectionId() == null) ? 0 : getCollectionId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getDeletedFlag() == null) ? 0 : getDeletedFlag().hashCode());
        result = prime * result + ((getDeletedTime() == null) ? 0 : getDeletedTime().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getStock() == null) ? 0 : getStock().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
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
        sb.append(", activityTimeEnd=").append(activityTimeEnd);
        sb.append(", activityTimeStart=").append(activityTimeStart);
        sb.append(", collectionId=").append(collectionId);
        sb.append(", createTime=").append(createTime);
        sb.append(", deletedFlag=").append(deletedFlag);
        sb.append(", deletedTime=").append(deletedTime);
        sb.append(", quantity=").append(quantity);
        sb.append(", stock=").append(stock);
        sb.append(", title=").append(title);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}