package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @TableName compose_record
 */
@TableName(value = "compose_record")
@Data
public class ComposeRecordEntity extends Model<ComposeRecordEntity> implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId
    private String id;
    /**
     *
     */
    private String composeActivityId;
    /**
     *
     */
    private Timestamp composeTime;
    /**
     *
     */
    private String issuedCollectionId;
    /**
     *
     */
    private String memberId;
    /**
     *
     */
    private String orderNo;
    /**
     *
     */
    private Long version;

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
        ComposeRecordEntity other = (ComposeRecordEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getComposeActivityId() == null ? other.getComposeActivityId() == null : this.getComposeActivityId().equals(other.getComposeActivityId()))
                && (this.getComposeTime() == null ? other.getComposeTime() == null : this.getComposeTime().equals(other.getComposeTime()))
                && (this.getIssuedCollectionId() == null ? other.getIssuedCollectionId() == null : this.getIssuedCollectionId().equals(other.getIssuedCollectionId()))
                && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getComposeActivityId() == null) ? 0 : getComposeActivityId().hashCode());
        result = prime * result + ((getComposeTime() == null) ? 0 : getComposeTime().hashCode());
        result = prime * result + ((getIssuedCollectionId() == null) ? 0 : getIssuedCollectionId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
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
        sb.append(", composeActivityId=").append(composeActivityId);
        sb.append(", composeTime=").append(composeTime);
        sb.append(", issuedCollectionId=").append(issuedCollectionId);
        sb.append(", memberId=").append(memberId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}