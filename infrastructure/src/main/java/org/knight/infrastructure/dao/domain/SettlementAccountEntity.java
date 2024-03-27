package org.knight.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 结算账户实体类，用于记录和管理用户的结算账户信息
 *
 * @TableName settlement_account 指定实体类对应的数据库表名为 "settlement_account"
 */
@TableName(value = "settlement_account")
@Data
public class SettlementAccountEntity extends Model<SettlementAccountEntity> implements Serializable {
    /**
     * 结算账户的唯一标识符ID
     */
    @TableId
    private String id;

    /**
     * 账户号码，用户的银行账户或其他类型的结算账户号码
     */
    private String account;

    /**
     * 账户是否已激活的标志
     */
    private Boolean activated;

    /**
     * 账户激活的时间
     */
    private Timestamp activatedTime;

    /**
     * 银行名称，用户结算账户所属的银行
     */
    private String bankName;

    /**
     * 银行卡号，用户的储蓄卡或信用卡卡号
     */
    private String cardNumber;

    /**
     * 账户创建的时间
     */
    private Timestamp createTime;

    /**
     * 删除标志，用于软删除功能
     */
    private Boolean deletedFlag;

    /**
     * 账户删除的时间
     */
    private Timestamp deletedTime;

    /**
     * 会员ID，结算账户所属用户的ID
     */
    private String memberId;

    /**
     * 真实姓名，与结算账户关联的用户的实名信息
     */
    private String realName;

    /**
     * 账户类型，如银行账户、第三方支付账户等
     */
    private String type;

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
        SettlementAccountEntity other = (SettlementAccountEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
                && (this.getActivated() == null ? other.getActivated() == null : this.getActivated().equals(other.getActivated()))
                && (this.getActivatedTime() == null ? other.getActivatedTime() == null : this.getActivatedTime().equals(other.getActivatedTime()))
                && (this.getBankName() == null ? other.getBankName() == null : this.getBankName().equals(other.getBankName()))
                && (this.getCardNumber() == null ? other.getCardNumber() == null : this.getCardNumber().equals(other.getCardNumber()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getDeletedFlag() == null ? other.getDeletedFlag() == null : this.getDeletedFlag().equals(other.getDeletedFlag()))
                && (this.getDeletedTime() == null ? other.getDeletedTime() == null : this.getDeletedTime().equals(other.getDeletedTime()))
                && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getActivated() == null) ? 0 : getActivated().hashCode());
        result = prime * result + ((getActivatedTime() == null) ? 0 : getActivatedTime().hashCode());
        result = prime * result + ((getBankName() == null) ? 0 : getBankName().hashCode());
        result = prime * result + ((getCardNumber() == null) ? 0 : getCardNumber().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getDeletedFlag() == null) ? 0 : getDeletedFlag().hashCode());
        result = prime * result + ((getDeletedTime() == null) ? 0 : getDeletedTime().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append(", activated=").append(activated);
        sb.append(", activatedTime=").append(activatedTime);
        sb.append(", bankName=").append(bankName);
        sb.append(", cardNumber=").append(cardNumber);
        sb.append(", createTime=").append(createTime);
        sb.append(", deletedFlag=").append(deletedFlag);
        sb.append(", deletedTime=").append(deletedTime);
        sb.append(", memberId=").append(memberId);
        sb.append(", realName=").append(realName);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}