package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 空投记录实体类，用于记录空投活动的相关信息。
 *
 * @TableName air_drop_record
 */
@TableName(value = "air_drop_record")
@Data
public class AirDropRecordEntity extends Model<AirDropRecordEntity> implements Serializable {
    /**
     * 记录的唯一标识ID
     */
    @TableId
    private String id;

    /**
     * 空投时间
     */
    private Timestamp airDropTime;

    /**
     * 收藏品ID
     */
    private String collectionId;

    /**
     * 发行的收藏品ID
     */
    private String issuedCollectionId;

    /**
     * 成员ID
     */
    private String memberId;

    /**
     * 订单号
     */
    private String orderNo;

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
        AirDropRecordEntity other = (AirDropRecordEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAirDropTime() == null ? other.getAirDropTime() == null : this.getAirDropTime().equals(other.getAirDropTime()))
                && (this.getCollectionId() == null ? other.getCollectionId() == null : this.getCollectionId().equals(other.getCollectionId()))
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
        result = prime * result + ((getAirDropTime() == null) ? 0 : getAirDropTime().hashCode());
        result = prime * result + ((getCollectionId() == null) ? 0 : getCollectionId().hashCode());
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
        sb.append(", airDropTime=").append(airDropTime);
        sb.append(", collectionId=").append(collectionId);
        sb.append(", issuedCollectionId=").append(issuedCollectionId);
        sb.append(", memberId=").append(memberId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}