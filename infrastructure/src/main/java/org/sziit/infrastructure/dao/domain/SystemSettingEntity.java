package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName system_setting
 */
@TableName(value = "system_setting")
@Data
public class SystemSettingEntity extends Model<SystemSettingEntity> implements Serializable {
    /**
     *
     */
    @TableId
    private String id;

    /**
     *
     */
    private String appSchema;

    /**
     *
     */
    private String appUrl;

    /**
     *
     */
    private Double appVersion;

    /**
     *
     */
    private String customerServiceUrl;

    /**
     *
     */
    private String h5gateway;

    /**
     *
     */
    private LocalDateTime latelyUpdateTime;

    /**
     *
     */
    private String localStoragePath;

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
        SystemSettingEntity other = (SystemSettingEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAppSchema() == null ? other.getAppSchema() == null : this.getAppSchema().equals(other.getAppSchema()))
                && (this.getAppUrl() == null ? other.getAppUrl() == null : this.getAppUrl().equals(other.getAppUrl()))
                && (this.getAppVersion() == null ? other.getAppVersion() == null : this.getAppVersion().equals(other.getAppVersion()))
                && (this.getCustomerServiceUrl() == null ? other.getCustomerServiceUrl() == null : this.getCustomerServiceUrl().equals(other.getCustomerServiceUrl()))
                && (this.getH5gateway() == null ? other.getH5gateway() == null : this.getH5gateway().equals(other.getH5gateway()))
                && (this.getLatelyUpdateTime() == null ? other.getLatelyUpdateTime() == null : this.getLatelyUpdateTime().equals(other.getLatelyUpdateTime()))
                && (this.getLocalStoragePath() == null ? other.getLocalStoragePath() == null : this.getLocalStoragePath().equals(other.getLocalStoragePath()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAppSchema() == null) ? 0 : getAppSchema().hashCode());
        result = prime * result + ((getAppUrl() == null) ? 0 : getAppUrl().hashCode());
        result = prime * result + ((getAppVersion() == null) ? 0 : getAppVersion().hashCode());
        result = prime * result + ((getCustomerServiceUrl() == null) ? 0 : getCustomerServiceUrl().hashCode());
        result = prime * result + ((getH5gateway() == null) ? 0 : getH5gateway().hashCode());
        result = prime * result + ((getLatelyUpdateTime() == null) ? 0 : getLatelyUpdateTime().hashCode());
        result = prime * result + ((getLocalStoragePath() == null) ? 0 : getLocalStoragePath().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", appSchema=").append(appSchema);
        sb.append(", appUrl=").append(appUrl);
        sb.append(", appVersion=").append(appVersion);
        sb.append(", customerServiceUrl=").append(customerServiceUrl);
        sb.append(", h5gateway=").append(h5gateway);
        sb.append(", latelyUpdateTime=").append(latelyUpdateTime);
        sb.append(", localStoragePath=").append(localStoragePath);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}