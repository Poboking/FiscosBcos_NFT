package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 会员余额变动日志实体类，用于记录会员余额的变动历史
 *
 * @TableName member_balance_change_log
 */
@TableName(value = "member_balance_change_log")
@Data
@Builder
public class MemberBalanceChangeLogEntity extends Model<MemberBalanceChangeLogEntity> implements Serializable {
    /**
     * 序列化版本UID，用于类在序列化和反序列化过程中保持版本兼容性
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 余额变动日志的唯一标识符ID
     */
    @TableId
    private String id;
    /**
     * 变动后的余额
     */
    private Double balanceAfter;
    /**
     * 变动前的余额
     */
    private Double balanceBefore;
    /**
     * 变动的金额，可以是增加或减少的金额
     */
    private Double balanceChange;
    /**
     * 业务订单号，关联具体的业务操作
     */
    private String bizOrderNo;
    /**
     * 变动发生的时间
     */
    private Timestamp changeTime;
    /**
     * 变动类型，如充值、消费、退款等
     */
    private String changeType;
    /**
     * 会员的唯一标识符ID
     */
    private String memberId;
    /**
     * 变动的备注信息，用于说明变动的原因和详情
     */
    private String note;
    /**
     * 版本号，用于乐观锁机制
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