package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典项实体类，用于定义特定字典类型的具体项。
 *
 * @TableName dict_item
 */
@Data
@TableName(value = "dict_item")
public class DictItemEntity extends Model<DictItemEntity> implements Serializable {
    /**
     * 字典项的唯一标识ID
     */
    @TableId
    private String id;

    /**
     * 字典项的编码
     */
    private String dictItemCode;

    /**
     * 字典项的名称
     */
    private String dictItemName;

    /**
     * 所属字典类型的ID
     */
    private String dictTypeId;

    /**
     * 字典项的排序号
     */
    private Double orderNo;

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
        DictItemEntity other = (DictItemEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getDictItemCode() == null ? other.getDictItemCode() == null : this.getDictItemCode().equals(other.getDictItemCode()))
                && (this.getDictItemName() == null ? other.getDictItemName() == null : this.getDictItemName().equals(other.getDictItemName()))
                && (this.getDictTypeId() == null ? other.getDictTypeId() == null : this.getDictTypeId().equals(other.getDictTypeId()))
                && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDictItemCode() == null) ? 0 : getDictItemCode().hashCode());
        result = prime * result + ((getDictItemName() == null) ? 0 : getDictItemName().hashCode());
        result = prime * result + ((getDictTypeId() == null) ? 0 : getDictTypeId().hashCode());
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
        sb.append(", dictItemCode=").append(dictItemCode);
        sb.append(", dictItemName=").append(dictItemName);
        sb.append(", dictTypeId=").append(dictTypeId);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}