package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName member_balance_change_log
 */
@TableName(value = "member_balance_change_log")
@Data
public class MemberBalanceChangeLogEntity extends Model<MemberBalanceChangeLogEntity> implements Serializable {
    /**
     *
     */
    @TableId
    private String id;

    /**
     *
     */
    private Double balanceAfter;

    /**
     *
     */
    private Double balanceBefore;

    /**
     *
     */
    private Double balanceChange;

    /**
     *
     */
    private String bizOrderNo;

    /**
     *
     */
    private LocalDateTime changeTime;

    /**
     *
     */
    private String changeType;

    /**
     *
     */
    private String memberId;

    /**
     *
     */
    private String note;

    /**
     *
     */
    private Long version;

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
        MemberBalanceChangeLogEntity other = (MemberBalanceChangeLogEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getBalanceAfter() == null ? other.getBalanceAfter() == null : this.getBalanceAfter().equals(other.getBalanceAfter()))
                && (this.getBalanceBefore() == null ? other.getBalanceBefore() == null : this.getBalanceBefore().equals(other.getBalanceBefore()))
                && (this.getBalanceChange() == null ? other.getBalanceChange() == null : this.getBalanceChange().equals(other.getBalanceChange()))
                && (this.getBizOrderNo() == null ? other.getBizOrderNo() == null : this.getBizOrderNo().equals(other.getBizOrderNo()))
                && (this.getChangeTime() == null ? other.getChangeTime() == null : this.getChangeTime().equals(other.getChangeTime()))
                && (this.getChangeType() == null ? other.getChangeType() == null : this.getChangeType().equals(other.getChangeType()))
                && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBalanceAfter() == null) ? 0 : getBalanceAfter().hashCode());
        result = prime * result + ((getBalanceBefore() == null) ? 0 : getBalanceBefore().hashCode());
        result = prime * result + ((getBalanceChange() == null) ? 0 : getBalanceChange().hashCode());
        result = prime * result + ((getBizOrderNo() == null) ? 0 : getBizOrderNo().hashCode());
        result = prime * result + ((getChangeTime() == null) ? 0 : getChangeTime().hashCode());
        result = prime * result + ((getChangeType() == null) ? 0 : getChangeType().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
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
        sb.append(", balanceAfter=").append(balanceAfter);
        sb.append(", balanceBefore=").append(balanceBefore);
        sb.append(", balanceChange=").append(balanceChange);
        sb.append(", bizOrderNo=").append(bizOrderNo);
        sb.append(", changeTime=").append(changeTime);
        sb.append(", changeType=").append(changeType);
        sb.append(", memberId=").append(memberId);
        sb.append(", note=").append(note);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}