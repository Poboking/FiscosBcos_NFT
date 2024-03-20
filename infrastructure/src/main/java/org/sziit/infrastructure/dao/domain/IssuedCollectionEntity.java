package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 发行收藏品实体类，用于记录发行的收藏品信息。
 *
 * @TableName issued_collection
 */
@Data
@TableName(value = "issued_collection")
public class IssuedCollectionEntity extends Model<IssuedCollectionEntity> implements Serializable {
    /**
     * 发行收藏品的唯一标识ID
     */
    @TableId
    private String id;

    /**
     * 收藏品ID
     */
    private String collectionId;

    /**
     * 收藏品序列号
     */
    private Integer collectionSerialNumber;

    /**
     * 删除标志，用于软删除
     */
    private Boolean deletedFlag;

    /**
     * 删除时间
     */
    private Timestamp deletedTime;

    /**
     * 发行时间
     */
    private Timestamp issueTime;

    /**
     * 与区块链同步的时间
     */
    private Timestamp syncChainTime;

    /**
     * 唯一ID，用于在区块链上的唯一标识
     */
    private String uniqueId;

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
        IssuedCollectionEntity other = (IssuedCollectionEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCollectionId() == null ? other.getCollectionId() == null : this.getCollectionId().equals(other.getCollectionId()))
                && (this.getCollectionSerialNumber() == null ? other.getCollectionSerialNumber() == null : this.getCollectionSerialNumber().equals(other.getCollectionSerialNumber()))
                && (this.getDeletedFlag() == null ? other.getDeletedFlag() == null : this.getDeletedFlag().equals(other.getDeletedFlag()))
                && (this.getDeletedTime() == null ? other.getDeletedTime() == null : this.getDeletedTime().equals(other.getDeletedTime()))
                && (this.getIssueTime() == null ? other.getIssueTime() == null : this.getIssueTime().equals(other.getIssueTime()))
                && (this.getSyncChainTime() == null ? other.getSyncChainTime() == null : this.getSyncChainTime().equals(other.getSyncChainTime()))
                && (this.getUniqueId() == null ? other.getUniqueId() == null : this.getUniqueId().equals(other.getUniqueId()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCollectionId() == null) ? 0 : getCollectionId().hashCode());
        result = prime * result + ((getCollectionSerialNumber() == null) ? 0 : getCollectionSerialNumber().hashCode());
        result = prime * result + ((getDeletedFlag() == null) ? 0 : getDeletedFlag().hashCode());
        result = prime * result + ((getDeletedTime() == null) ? 0 : getDeletedTime().hashCode());
        result = prime * result + ((getIssueTime() == null) ? 0 : getIssueTime().hashCode());
        result = prime * result + ((getSyncChainTime() == null) ? 0 : getSyncChainTime().hashCode());
        result = prime * result + ((getUniqueId() == null) ? 0 : getUniqueId().hashCode());
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
        sb.append(", collectionId=").append(collectionId);
        sb.append(", collectionSerialNumber=").append(collectionSerialNumber);
        sb.append(", deletedFlag=").append(deletedFlag);
        sb.append(", deletedTime=").append(deletedTime);
        sb.append(", issueTime=").append(issueTime);
        sb.append(", syncChainTime=").append(syncChainTime);
        sb.append(", uniqueId=").append(uniqueId);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}