package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 成员实体类，用于存储和管理平台成员的信息。
 *
 * @TableName member
 */
@Data
@TableName(value = "member")
public class MemberEntity extends Model<MemberEntity> implements Serializable {
    /**
     * 成员的唯一标识ID
     */
    @TableId
    private String id;

    /**
     * 成员的余额
     */
    private Double balance;

    /**
     * 删除标志，用于软删除
     */
    private Boolean deletedFlag;

    /**
     * 删除时间
     */
    private LocalDateTime deletedTime;

    /**
     * 保持登录的持续时间（单位：秒）
     */
    private Integer keepLoginDuration;

    /**
     * 最近一次登录时间
     */
    private LocalDateTime latelyLoginTime;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 成员的手机号码
     */
    private String mobile;

    /**
     * 成员的昵称
     */
    private String nickName;

    /**
     * 支付密码
     */
    private String payPwd;

    /**
     * 成员的真实姓名
     */
    private String realName;

    /**
     * 注册时间
     */
    private LocalDateTime registeredTime;

    /**
     * 成员状态
     */
    private String state;

    /**
     * 版本号，用于乐观锁控制
     */
    private Long version;

    /**
     * 推送客户端ID
     */
    private String pushClientId;

    /**
     * 区块链地址
     */
    private String blockChainAddr;

    /**
     * 成员的头像
     */
    private String avatar;

    /**
     * 绑定真实姓名的时间
     */
    private LocalDateTime bindRealNameTime;

    /**
     * 成员的身份证号码
     */
    private String identityCard;

    /**
     * 账户等级
     */
    private Integer accountLevel;

    /**
     * 账户等级路径
     */
    private String accountLevelPath;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 邀请人ID
     */
    private String inviterId;

    /**
     * 是否购买过的标志
     */
    private Boolean boughtFlag;

    /**
     * 与区块链同步的时间
     */
    private LocalDateTime syncChainTime;

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
        MemberEntity other = (MemberEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
                && (this.getDeletedFlag() == null ? other.getDeletedFlag() == null : this.getDeletedFlag().equals(other.getDeletedFlag()))
                && (this.getDeletedTime() == null ? other.getDeletedTime() == null : this.getDeletedTime().equals(other.getDeletedTime()))
                && (this.getKeepLoginDuration() == null ? other.getKeepLoginDuration() == null : this.getKeepLoginDuration().equals(other.getKeepLoginDuration()))
                && (this.getLatelyLoginTime() == null ? other.getLatelyLoginTime() == null : this.getLatelyLoginTime().equals(other.getLatelyLoginTime()))
                && (this.getLoginPwd() == null ? other.getLoginPwd() == null : this.getLoginPwd().equals(other.getLoginPwd()))
                && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
                && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
                && (this.getPayPwd() == null ? other.getPayPwd() == null : this.getPayPwd().equals(other.getPayPwd()))
                && (this.getRealName() == null ? other.getRealName() == null : this.getRealName().equals(other.getRealName()))
                && (this.getRegisteredTime() == null ? other.getRegisteredTime() == null : this.getRegisteredTime().equals(other.getRegisteredTime()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
                && (this.getPushClientId() == null ? other.getPushClientId() == null : this.getPushClientId().equals(other.getPushClientId()))
                && (this.getBlockChainAddr() == null ? other.getBlockChainAddr() == null : this.getBlockChainAddr().equals(other.getBlockChainAddr()))
                && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
                && (this.getBindRealNameTime() == null ? other.getBindRealNameTime() == null : this.getBindRealNameTime().equals(other.getBindRealNameTime()))
                && (this.getIdentityCard() == null ? other.getIdentityCard() == null : this.getIdentityCard().equals(other.getIdentityCard()))
                && (this.getAccountLevel() == null ? other.getAccountLevel() == null : this.getAccountLevel().equals(other.getAccountLevel()))
                && (this.getAccountLevelPath() == null ? other.getAccountLevelPath() == null : this.getAccountLevelPath().equals(other.getAccountLevelPath()))
                && (this.getInviteCode() == null ? other.getInviteCode() == null : this.getInviteCode().equals(other.getInviteCode()))
                && (this.getInviterId() == null ? other.getInviterId() == null : this.getInviterId().equals(other.getInviterId()))
                && (this.getBoughtFlag() == null ? other.getBoughtFlag() == null : this.getBoughtFlag().equals(other.getBoughtFlag()))
                && (this.getSyncChainTime() == null ? other.getSyncChainTime() == null : this.getSyncChainTime().equals(other.getSyncChainTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getDeletedFlag() == null) ? 0 : getDeletedFlag().hashCode());
        result = prime * result + ((getDeletedTime() == null) ? 0 : getDeletedTime().hashCode());
        result = prime * result + ((getKeepLoginDuration() == null) ? 0 : getKeepLoginDuration().hashCode());
        result = prime * result + ((getLatelyLoginTime() == null) ? 0 : getLatelyLoginTime().hashCode());
        result = prime * result + ((getLoginPwd() == null) ? 0 : getLoginPwd().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getPayPwd() == null) ? 0 : getPayPwd().hashCode());
        result = prime * result + ((getRealName() == null) ? 0 : getRealName().hashCode());
        result = prime * result + ((getRegisteredTime() == null) ? 0 : getRegisteredTime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getPushClientId() == null) ? 0 : getPushClientId().hashCode());
        result = prime * result + ((getBlockChainAddr() == null) ? 0 : getBlockChainAddr().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getBindRealNameTime() == null) ? 0 : getBindRealNameTime().hashCode());
        result = prime * result + ((getIdentityCard() == null) ? 0 : getIdentityCard().hashCode());
        result = prime * result + ((getAccountLevel() == null) ? 0 : getAccountLevel().hashCode());
        result = prime * result + ((getAccountLevelPath() == null) ? 0 : getAccountLevelPath().hashCode());
        result = prime * result + ((getInviteCode() == null) ? 0 : getInviteCode().hashCode());
        result = prime * result + ((getInviterId() == null) ? 0 : getInviterId().hashCode());
        result = prime * result + ((getBoughtFlag() == null) ? 0 : getBoughtFlag().hashCode());
        result = prime * result + ((getSyncChainTime() == null) ? 0 : getSyncChainTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", balance=").append(balance);
        sb.append(", deletedFlag=").append(deletedFlag);
        sb.append(", deletedTime=").append(deletedTime);
        sb.append(", keepLoginDuration=").append(keepLoginDuration);
        sb.append(", latelyLoginTime=").append(latelyLoginTime);
        sb.append(", loginPwd=").append(loginPwd);
        sb.append(", mobile=").append(mobile);
        sb.append(", nickName=").append(nickName);
        sb.append(", payPwd=").append(payPwd);
        sb.append(", realName=").append(realName);
        sb.append(", registeredTime=").append(registeredTime);
        sb.append(", state=").append(state);
        sb.append(", version=").append(version);
        sb.append(", pushClientId=").append(pushClientId);
        sb.append(", blockChainAddr=").append(blockChainAddr);
        sb.append(", avatar=").append(avatar);
        sb.append(", bindRealNameTime=").append(bindRealNameTime);
        sb.append(", identityCard=").append(identityCard);
        sb.append(", accountLevel=").append(accountLevel);
        sb.append(", accountLevelPath=").append(accountLevelPath);
        sb.append(", inviteCode=").append(inviteCode);
        sb.append(", inviterId=").append(inviterId);
        sb.append(", boughtFlag=").append(boughtFlag);
        sb.append(", syncChainTime=").append(syncChainTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}