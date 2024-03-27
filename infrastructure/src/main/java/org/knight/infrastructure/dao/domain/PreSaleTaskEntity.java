package org.knight.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 预售任务实体类，用于定义和管理预售活动的任务信息。
 *
 * @TableName pre_sale_task
 */
@Data
@TableName(value = "pre_sale_task")
public class PreSaleTaskEntity extends Model<PreSaleTaskEntity> implements Serializable {
    /**
     * 序列化版本UID，用于类版本控制
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 预售任务的唯一标识ID
     */
    @TableId
    private String id;
    /**
     * 相关联的收藏品ID
     */
    private String collectionId;
    /**
     * 任务创建时间
     */
    private Timestamp createTime;
    /**
     * 任务执行时间
     */
    private Timestamp executeTime;
    /**
     * 预售前的分钟数，用于倒计时
     */
    private Integer preMinute;
    /**
     * 任务状态，如未开始、进行中、已结束等
     */
    private String state;
    /**
     * 任务名称
     */
    private String taskName;
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
        PreSaleTaskEntity other = (PreSaleTaskEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCollectionId() == null ? other.getCollectionId() == null : this.getCollectionId().equals(other.getCollectionId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getExecuteTime() == null ? other.getExecuteTime() == null : this.getExecuteTime().equals(other.getExecuteTime()))
                && (this.getPreMinute() == null ? other.getPreMinute() == null : this.getPreMinute().equals(other.getPreMinute()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getTaskName() == null ? other.getTaskName() == null : this.getTaskName().equals(other.getTaskName()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCollectionId() == null) ? 0 : getCollectionId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getExecuteTime() == null) ? 0 : getExecuteTime().hashCode());
        result = prime * result + ((getPreMinute() == null) ? 0 : getPreMinute().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
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
        sb.append(", createTime=").append(createTime);
        sb.append(", executeTime=").append(executeTime);
        sb.append(", preMinute=").append(preMinute);
        sb.append(", state=").append(state);
        sb.append(", taskName=").append(taskName);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}