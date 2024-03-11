package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 预售条件实体类，用于定义预售任务的条件。
 *
 * @TableName pre_sale_condition
 */
@Data
@TableName(value = "pre_sale_condition")
public class PreSaleConditionEntity extends Model<PreSaleConditionEntity> implements Serializable {
    /**
     * 预售条件记录的唯一标识ID
     */
    @TableId
    private String id;

    /**
     * 条件表达式
     */
    private String cond;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段值
     */
    private String fieldValue;

    /**
     * 逻辑操作符，如AND、OR等
     */
    private String logicalOperation;

    /**
     * 排序号，用于条件的排序
     */
    private Double orderNo;

    /**
     * 预售任务ID，关联的预售任务
     */
    private String preSaleTaskId;

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
        PreSaleConditionEntity other = (PreSaleConditionEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCond() == null ? other.getCond() == null : this.getCond().equals(other.getCond()))
                && (this.getFieldName() == null ? other.getFieldName() == null : this.getFieldName().equals(other.getFieldName()))
                && (this.getFieldValue() == null ? other.getFieldValue() == null : this.getFieldValue().equals(other.getFieldValue()))
                && (this.getLogicalOperation() == null ? other.getLogicalOperation() == null : this.getLogicalOperation().equals(other.getLogicalOperation()))
                && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
                && (this.getPreSaleTaskId() == null ? other.getPreSaleTaskId() == null : this.getPreSaleTaskId().equals(other.getPreSaleTaskId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCond() == null) ? 0 : getCond().hashCode());
        result = prime * result + ((getFieldName() == null) ? 0 : getFieldName().hashCode());
        result = prime * result + ((getFieldValue() == null) ? 0 : getFieldValue().hashCode());
        result = prime * result + ((getLogicalOperation() == null) ? 0 : getLogicalOperation().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getPreSaleTaskId() == null) ? 0 : getPreSaleTaskId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cond=").append(cond);
        sb.append(", fieldName=").append(fieldName);
        sb.append(", fieldValue=").append(fieldValue);
        sb.append(", logicalOperation=").append(logicalOperation);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", preSaleTaskId=").append(preSaleTaskId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}