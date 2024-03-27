package org.knight.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @TableName chain_setting
 */
@TableName(value = "chain_setting")
@Data
public class ChainSettingEntity extends Model<ChainSettingEntity> implements Serializable {
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
    private String currentInUseChain;
    /**
     *
     */
    private Timestamp latelyUpdateTime;

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
        ChainSettingEntity other = (ChainSettingEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCurrentInUseChain() == null ? other.getCurrentInUseChain() == null : this.getCurrentInUseChain().equals(other.getCurrentInUseChain()))
                && (this.getLatelyUpdateTime() == null ? other.getLatelyUpdateTime() == null : this.getLatelyUpdateTime().equals(other.getLatelyUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCurrentInUseChain() == null) ? 0 : getCurrentInUseChain().hashCode());
        result = prime * result + ((getLatelyUpdateTime() == null) ? 0 : getLatelyUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", currentInUseChain=").append(currentInUseChain);
        sb.append(", latelyUpdateTime=").append(latelyUpdateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}