package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @TableName oper_log
 */
@TableName(value = "oper_log")
@Data
public class OperLogEntity extends Model<OperLogEntity> implements Serializable {
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
    private String ipAddr;
    /**
     *
     */
    private String module;
    /**
     *
     */
    private String operAccountId;
    /**
     *
     */
    private String operName;
    /**
     *
     */
    private Timestamp operTime;
    /**
     *
     */
    private String operate;
    /**
     *
     */
    private String requestMethod;
    /**
     *
     */
    private String requestParam;
    /**
     *
     */
    private String requestUrl;
    /**
     *
     */
    private String subSystem;

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
        OperLogEntity other = (OperLogEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getIpAddr() == null ? other.getIpAddr() == null : this.getIpAddr().equals(other.getIpAddr()))
                && (this.getModule() == null ? other.getModule() == null : this.getModule().equals(other.getModule()))
                && (this.getOperAccountId() == null ? other.getOperAccountId() == null : this.getOperAccountId().equals(other.getOperAccountId()))
                && (this.getOperName() == null ? other.getOperName() == null : this.getOperName().equals(other.getOperName()))
                && (this.getOperTime() == null ? other.getOperTime() == null : this.getOperTime().equals(other.getOperTime()))
                && (this.getOperate() == null ? other.getOperate() == null : this.getOperate().equals(other.getOperate()))
                && (this.getRequestMethod() == null ? other.getRequestMethod() == null : this.getRequestMethod().equals(other.getRequestMethod()))
                && (this.getRequestParam() == null ? other.getRequestParam() == null : this.getRequestParam().equals(other.getRequestParam()))
                && (this.getRequestUrl() == null ? other.getRequestUrl() == null : this.getRequestUrl().equals(other.getRequestUrl()))
                && (this.getSubSystem() == null ? other.getSubSystem() == null : this.getSubSystem().equals(other.getSubSystem()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIpAddr() == null) ? 0 : getIpAddr().hashCode());
        result = prime * result + ((getModule() == null) ? 0 : getModule().hashCode());
        result = prime * result + ((getOperAccountId() == null) ? 0 : getOperAccountId().hashCode());
        result = prime * result + ((getOperName() == null) ? 0 : getOperName().hashCode());
        result = prime * result + ((getOperTime() == null) ? 0 : getOperTime().hashCode());
        result = prime * result + ((getOperate() == null) ? 0 : getOperate().hashCode());
        result = prime * result + ((getRequestMethod() == null) ? 0 : getRequestMethod().hashCode());
        result = prime * result + ((getRequestParam() == null) ? 0 : getRequestParam().hashCode());
        result = prime * result + ((getRequestUrl() == null) ? 0 : getRequestUrl().hashCode());
        result = prime * result + ((getSubSystem() == null) ? 0 : getSubSystem().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ipAddr=").append(ipAddr);
        sb.append(", module=").append(module);
        sb.append(", operAccountId=").append(operAccountId);
        sb.append(", operName=").append(operName);
        sb.append(", operTime=").append(operTime);
        sb.append(", operate=").append(operate);
        sb.append(", requestMethod=").append(requestMethod);
        sb.append(", requestParam=").append(requestParam);
        sb.append(", requestUrl=").append(requestUrl);
        sb.append(", subSystem=").append(subSystem);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}