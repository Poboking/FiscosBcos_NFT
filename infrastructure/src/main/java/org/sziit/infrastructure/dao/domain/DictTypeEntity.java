package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 字典类型实体类，用于定义系统中的字典类型及其相关信息。
 *
 * @TableName dict_type
 */
@Data
@TableName(value = "dict_type")
public class DictTypeEntity extends Model<DictTypeEntity> implements Serializable {
    /**
     * 序列化版本UID，用于类版本控制
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 字典类型的唯一标识ID
     */
    @TableId
    private String id;
    /**
     * 字典类型的编码
     */
    private String dictTypeCode;
    /**
     * 字典类型的名称
     */
    private String dictTypeName;
    /**
     * 最后修改时间
     */
    private Timestamp lastModifyTime;
    /**
     * 字典类型的备注信息
     */
    private String note;
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
        DictTypeEntity other = (DictTypeEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getDictTypeCode() == null ? other.getDictTypeCode() == null : this.getDictTypeCode().equals(other.getDictTypeCode()))
                && (this.getDictTypeName() == null ? other.getDictTypeName() == null : this.getDictTypeName().equals(other.getDictTypeName()))
                && (this.getLastModifyTime() == null ? other.getLastModifyTime() == null : this.getLastModifyTime().equals(other.getLastModifyTime()))
                && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDictTypeCode() == null) ? 0 : getDictTypeCode().hashCode());
        result = prime * result + ((getDictTypeName() == null) ? 0 : getDictTypeName().hashCode());
        result = prime * result + ((getLastModifyTime() == null) ? 0 : getLastModifyTime().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
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
        sb.append(", dictTypeCode=").append(dictTypeCode);
        sb.append(", dictTypeName=").append(dictTypeName);
        sb.append(", lastModifyTime=").append(lastModifyTime);
        sb.append(", note=").append(note);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}