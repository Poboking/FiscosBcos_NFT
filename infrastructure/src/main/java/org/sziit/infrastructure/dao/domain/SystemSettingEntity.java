package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @TableName system_setting
 * 系统设置实体类，用于存储系统级别的配置信息
 */
@TableName(value = "system_setting")
@Data
public class SystemSettingEntity extends Model<SystemSettingEntity> implements Serializable {
    /**
     * 序列化版本UID，用于类在序列化和反序列化过程中保持版本兼容性
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 系统设置项的唯一标识符
     */
    @TableId
    private String id;
    /**
     * 应用程序的schema名称，用于区分不同环境下的数据库schema
     */
    private String appSchema;
    /**
     * 应用程序的访问基础URL，用于构建完整的API请求地址
     */
    private String appUrl;
    /**
     * 应用程序的版本号，包含主版本号、次版本号以及修订号
     */
    private Double appVersion;
    /**
     * 客户服务功能的访问URL，用于提供用户支持和帮助
     */
    private String customerServiceUrl;
    /**
     * H5网关的URL地址，用于页面跳转和路由管理
     */
    private String h5gateway;
    /**
     * 系统设置最后更新的时间点，用于记录最近的修改时间
     */
    private Timestamp latelyUpdateTime;
    /**
     * 本地存储的路径，用于指定客户端存储数据的位置
     */
    private String localStoragePath;

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