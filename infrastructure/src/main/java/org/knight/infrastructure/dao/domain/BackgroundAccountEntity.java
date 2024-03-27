package org.knight.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 后台账户实体类，用于存储后台用户账户信息。
 *
 * @TableName background_account
 */
@TableName(value = "background_account")
@Data
public class BackgroundAccountEntity extends Model<BackgroundAccountEntity> implements Serializable {
    /**
     * 序列化版本UID，用于类版本控制
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 账户的唯一标识ID
     */
    @TableId
    private String id;
    /**
     * 删除标志，用于软删除
     */
    private Boolean deletedFlag;
    /**
     * 账户删除时间
     */
    private Timestamp deletedTime;
    /**
     * Google二次验证绑定时间
     */
    private Timestamp googleAuthBindTime;
    /**
     * Google二次验证密钥
     */
    private String googleSecretKey;
    /**
     * 最近一次登录时间
     */
    private Timestamp latelyLoginTime;
    /**
     * 登录密码
     */
    private String loginPwd;
    /**
     * 账户注册时间
     */
    private Timestamp registeredTime;
    /**
     * 账户状态
     */
    private String state;
    /**
     * 超级管理员标志
     */
    private Boolean superAdminFlag;
    /**
     * 用户名
     */
    private String userName;
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
        BackgroundAccountEntity other = (BackgroundAccountEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getDeletedFlag() == null ? other.getDeletedFlag() == null : this.getDeletedFlag().equals(other.getDeletedFlag()))
                && (this.getDeletedTime() == null ? other.getDeletedTime() == null : this.getDeletedTime().equals(other.getDeletedTime()))
                && (this.getGoogleAuthBindTime() == null ? other.getGoogleAuthBindTime() == null : this.getGoogleAuthBindTime().equals(other.getGoogleAuthBindTime()))
                && (this.getGoogleSecretKey() == null ? other.getGoogleSecretKey() == null : this.getGoogleSecretKey().equals(other.getGoogleSecretKey()))
                && (this.getLatelyLoginTime() == null ? other.getLatelyLoginTime() == null : this.getLatelyLoginTime().equals(other.getLatelyLoginTime()))
                && (this.getLoginPwd() == null ? other.getLoginPwd() == null : this.getLoginPwd().equals(other.getLoginPwd()))
                && (this.getRegisteredTime() == null ? other.getRegisteredTime() == null : this.getRegisteredTime().equals(other.getRegisteredTime()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getSuperAdminFlag() == null ? other.getSuperAdminFlag() == null : this.getSuperAdminFlag().equals(other.getSuperAdminFlag()))
                && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDeletedFlag() == null) ? 0 : getDeletedFlag().hashCode());
        result = prime * result + ((getDeletedTime() == null) ? 0 : getDeletedTime().hashCode());
        result = prime * result + ((getGoogleAuthBindTime() == null) ? 0 : getGoogleAuthBindTime().hashCode());
        result = prime * result + ((getGoogleSecretKey() == null) ? 0 : getGoogleSecretKey().hashCode());
        result = prime * result + ((getLatelyLoginTime() == null) ? 0 : getLatelyLoginTime().hashCode());
        result = prime * result + ((getLoginPwd() == null) ? 0 : getLoginPwd().hashCode());
        result = prime * result + ((getRegisteredTime() == null) ? 0 : getRegisteredTime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getSuperAdminFlag() == null) ? 0 : getSuperAdminFlag().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
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
        sb.append(", deletedFlag=").append(deletedFlag);
        sb.append(", deletedTime=").append(deletedTime);
        sb.append(", googleAuthBindTime=").append(googleAuthBindTime);
        sb.append(", googleSecretKey=").append(googleSecretKey);
        sb.append(", latelyLoginTime=").append(latelyLoginTime);
        sb.append(", loginPwd=").append(loginPwd);
        sb.append(", registeredTime=").append(registeredTime);
        sb.append(", state=").append(state);
        sb.append(", superAdminFlag=").append(superAdminFlag);
        sb.append(", userName=").append(userName);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}