package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录日志实体类，用于记录用户登录系统的相关日志信息。
 *
 * @TableName login_log
 */
@Data
@TableName(value = "login_log")
public class LoginLogEntity extends Model<LoginLogEntity> implements Serializable {
    /**
     * 登录日志记录的唯一标识ID
     */
    @TableId
    private String id;

    /**
     * 登录时使用的浏览器名称
     */
    private String browser;

    /**
     * 登录时用户的IP地址
     */
    private String ipAddr;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 登录过程中的相关信息或错误消息
     */
    private String msg;

    /**
     * 登录时用户的操作系统
     */
    private String os;

    /**
     * 登录状态，如成功、失败等
     */
    private String state;

    /**
     * 登录日志所属的子系统
     */
    private String subSystem;

    /**
     * 登录用户的用户名
     */
    private String userName;

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
        LoginLogEntity other = (LoginLogEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getBrowser() == null ? other.getBrowser() == null : this.getBrowser().equals(other.getBrowser()))
                && (this.getIpAddr() == null ? other.getIpAddr() == null : this.getIpAddr().equals(other.getIpAddr()))
                && (this.getLoginTime() == null ? other.getLoginTime() == null : this.getLoginTime().equals(other.getLoginTime()))
                && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()))
                && (this.getOs() == null ? other.getOs() == null : this.getOs().equals(other.getOs()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getSubSystem() == null ? other.getSubSystem() == null : this.getSubSystem().equals(other.getSubSystem()))
                && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBrowser() == null) ? 0 : getBrowser().hashCode());
        result = prime * result + ((getIpAddr() == null) ? 0 : getIpAddr().hashCode());
        result = prime * result + ((getLoginTime() == null) ? 0 : getLoginTime().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        result = prime * result + ((getOs() == null) ? 0 : getOs().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getSubSystem() == null) ? 0 : getSubSystem().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", browser=").append(browser);
        sb.append(", ipAddr=").append(ipAddr);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", msg=").append(msg);
        sb.append(", os=").append(os);
        sb.append(", state=").append(state);
        sb.append(", subSystem=").append(subSystem);
        sb.append(", userName=").append(userName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}