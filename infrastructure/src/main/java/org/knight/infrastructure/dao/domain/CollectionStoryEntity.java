package org.knight.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 收藏故事实体类，用于记录和管理收藏品相关的故事或描述信息
 *
 * @TableName collection_story 指定实体类对应的数据库表名为 "collection_story"
 */
@TableName(value = "collection_story")
@Data
@Builder
public class CollectionStoryEntity extends Model<CollectionStoryEntity> implements Serializable {
    /**
     * 收藏故事记录的唯一标识符ID，用于唯一标识每条收藏故事记录
     */
    @TableId
    private String id;

    /**
     * 收藏品ID，关联的收藏品的唯一标识符
     */
    private String collectionId;

    /**
     * 顺序编号，用于定义故事在一系列故事中的顺序
     */
    private Double orderNo;

    /**
     * 图片链接，收藏故事相关的图片资源的网络地址
     */
    private String picLink;

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