package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 收藏品实体类，用于存储和管理收藏品的详细信息。
 *
 * @TableName collection
 */
@Data
@TableName(value = "collection")
public class CollectionEntity extends Model<CollectionEntity> implements Serializable {

    /**
     * 收藏品的唯一标识ID
     */
    @TableId
    @Schema(name = "id", type = "String", minimum = "undefined", maximum = "undefined")
    private String id;

    /**
     * 创建者的姓名
     */
    @TableField(exist = false)
    private String creatorName;

    /**
     * 创建者的头像
     */
    @TableField(exist = false)
    private String creatorAvatar;

    /**
     * 收藏品的哈希值，用于唯一确定一个收藏品
     */
    private String collectionHash;

    /**
     * 收藏品的类型
     * 商品类型_藏品 = "1";
     * 商品类型_盲盒 = "2"
     */
    private String commodityType;

    /**
     * 收藏品的封面图片链接或路径
     */
    private String cover;

    /**
     * 收藏品的创建时间
     */
    private Timestamp createTime;

    /**
     * 创建收藏品的创作者ID
     */
    private String creatorId;

    /**
     * 删除标志，表示该记录是否已被逻辑删除
     */
    private Boolean deletedFlag;

    /**
     * 逻辑删除的时间戳
     */
    private Timestamp deletedTime;

    /**
     * 是否允许在外部平台销售的标志
     */
    private Boolean externalSaleFlag;

    /**
     * 收藏品的名称
     */
    private String name;

    /**
     * 收藏品的价格
     */
    private Double price;

    /**
     * 收藏品的数量
     */
    private Integer quantity;

    /**
     * 收藏品的销售时间
     */
    private Timestamp saleTime;

    /**
     * 收藏品的库存数量
     */
    private Integer stock;

    /**
     * 与区块链同步的时间
     */
    private Timestamp syncChainTime;

    /**
     * 版本号，用于数据库的乐观锁机制
     */
    private Long version;

    /**
     * 序列化版本UID，用于版本控制
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
        CollectionEntity other = (CollectionEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCollectionHash() == null ? other.getCollectionHash() == null : this.getCollectionHash().equals(other.getCollectionHash()))
                && (this.getCommodityType() == null ? other.getCommodityType() == null : this.getCommodityType().equals(other.getCommodityType()))
                && (this.getCover() == null ? other.getCover() == null : this.getCover().equals(other.getCover()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getCreatorId() == null ? other.getCreatorId() == null : this.getCreatorId().equals(other.getCreatorId()))
                && (this.getDeletedFlag() == null ? other.getDeletedFlag() == null : this.getDeletedFlag().equals(other.getDeletedFlag()))
                && (this.getDeletedTime() == null ? other.getDeletedTime() == null : this.getDeletedTime().equals(other.getDeletedTime()))
                && (this.getExternalSaleFlag() == null ? other.getExternalSaleFlag() == null : this.getExternalSaleFlag().equals(other.getExternalSaleFlag()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
                && (this.getSaleTime() == null ? other.getSaleTime() == null : this.getSaleTime().equals(other.getSaleTime()))
                && (this.getStock() == null ? other.getStock() == null : this.getStock().equals(other.getStock()))
                && (this.getSyncChainTime() == null ? other.getSyncChainTime() == null : this.getSyncChainTime().equals(other.getSyncChainTime()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCollectionHash() == null) ? 0 : getCollectionHash().hashCode());
        result = prime * result + ((getCommodityType() == null) ? 0 : getCommodityType().hashCode());
        result = prime * result + ((getCover() == null) ? 0 : getCover().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreatorId() == null) ? 0 : getCreatorId().hashCode());
        result = prime * result + ((getDeletedFlag() == null) ? 0 : getDeletedFlag().hashCode());
        result = prime * result + ((getDeletedTime() == null) ? 0 : getDeletedTime().hashCode());
        result = prime * result + ((getExternalSaleFlag() == null) ? 0 : getExternalSaleFlag().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getSaleTime() == null) ? 0 : getSaleTime().hashCode());
        result = prime * result + ((getStock() == null) ? 0 : getStock().hashCode());
        result = prime * result + ((getSyncChainTime() == null) ? 0 : getSyncChainTime().hashCode());
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
        sb.append(", collectionHash=").append(collectionHash);
        sb.append(", commodityType=").append(commodityType);
        sb.append(", cover=").append(cover);
        sb.append(", createTime=").append(createTime);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", deletedFlag=").append(deletedFlag);
        sb.append(", deletedTime=").append(deletedTime);
        sb.append(", externalSaleFlag=").append(externalSaleFlag);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", quantity=").append(quantity);
        sb.append(", saleTime=").append(saleTime);
        sb.append(", stock=").append(stock);
        sb.append(", syncChainTime=").append(syncChainTime);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}