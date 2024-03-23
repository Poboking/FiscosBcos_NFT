package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName collection_story
 */
@TableName(value = "collection_story")
@Data
public class CollectionStoryEntity extends Model<CollectionStoryEntity> implements Serializable {
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
    private String collectionId;
    /**
     *
     */
    private Double orderNo;
    /**
     *
     */
    private String picLink;

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
        CollectionStoryEntity other = (CollectionStoryEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCollectionId() == null ? other.getCollectionId() == null : this.getCollectionId().equals(other.getCollectionId()))
                && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
                && (this.getPicLink() == null ? other.getPicLink() == null : this.getPicLink().equals(other.getPicLink()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCollectionId() == null) ? 0 : getCollectionId().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getPicLink() == null) ? 0 : getPicLink().hashCode());
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
        sb.append(", orderNo=").append(orderNo);
        sb.append(", picLink=").append(picLink);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}