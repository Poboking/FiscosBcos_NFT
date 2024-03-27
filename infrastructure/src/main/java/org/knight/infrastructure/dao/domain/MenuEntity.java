package org.knight.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 菜单实体类，用于定义系统中的菜单项及其属性
 *
 * @TableName menu 指定实体类对应的数据库表名为 "menu"
 */
@TableName(value = "menu")
@Data
public class MenuEntity extends Model<MenuEntity> implements Serializable {
    /**
     * 菜单项的唯一标识符ID
     */
    @TableId
    private String id;

    /**
     * 删除标志，用于软删除功能，如果菜单项被标记为删除，则此字段为true
     */
    private Boolean deletedFlag;

    /**
     * 菜单项删除的时间
     */
    private Timestamp deletedTime;

    /**
     * 菜单项的名称
     */
    private String name;

    /**
     * 菜单项的排序编号，用于在菜单中确定显示顺序
     */
    private Double orderNo;

    /**
     * 父菜单项的ID，如果此菜单项是子菜单，则此字段指向其父菜单项
     */
    private String parentId;

    /**
     * 菜单项类型，如目录、菜单、按钮等
     */
    private String type;

    /**
     * 菜单项关联的URL地址，如果是链接类型的菜单项，则此字段有效
     */
    private String url;

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
        MenuEntity other = (MenuEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getDeletedFlag() == null ? other.getDeletedFlag() == null : this.getDeletedFlag().equals(other.getDeletedFlag()))
                && (this.getDeletedTime() == null ? other.getDeletedTime() == null : this.getDeletedTime().equals(other.getDeletedTime()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
                && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDeletedFlag() == null) ? 0 : getDeletedFlag().hashCode());
        result = prime * result + ((getDeletedTime() == null) ? 0 : getDeletedTime().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", deletedFlag=").append(deletedFlag);
        sb.append(", deletedTime=").append(deletedTime);
        sb.append(", name=").append(name);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", parentId=").append(parentId);
        sb.append(", type=").append(type);
        sb.append(", url=").append(url);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}