package org.knight.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 神秘盒子商品实体类，用于记录神秘盒子中的商品信息及其出现概率。
 *
 * @TableName mystery_box_commodity
 */
@Data
@TableName(value = "mystery_box_commodity")
public class MysteryBoxCommodityEntity extends Model<MysteryBoxCommodityEntity> implements Serializable {
    /**
     * 序列化版本UID，用于类版本控制
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 商品的唯一标识ID
     */
    @TableId
    private String id;
    /**
     * 所属收藏品的ID
     */
    private String collectionId;
    /**
     * 商品的ID
     */
    private String commodityId;
    /**
     * 商品在神秘盒子中出现的概率
     */
    private Double probability;
    /**
     * 版本号，用于乐观锁控制
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
        MysteryBoxCommodityEntity other = (MysteryBoxCommodityEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCollectionId() == null ? other.getCollectionId() == null : this.getCollectionId().equals(other.getCollectionId()))
                && (this.getCommodityId() == null ? other.getCommodityId() == null : this.getCommodityId().equals(other.getCommodityId()))
                && (this.getProbability() == null ? other.getProbability() == null : this.getProbability().equals(other.getProbability()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCollectionId() == null) ? 0 : getCollectionId().hashCode());
        result = prime * result + ((getCommodityId() == null) ? 0 : getCommodityId().hashCode());
        result = prime * result + ((getProbability() == null) ? 0 : getProbability().hashCode());
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
        sb.append(", commodityId=").append(commodityId);
        sb.append(", probability=").append(probability);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}