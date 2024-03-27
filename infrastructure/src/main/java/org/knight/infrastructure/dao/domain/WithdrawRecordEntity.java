package org.knight.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 提现记录实体类，用于记录用户提现的详细信息和状态
 *
 * @TableName withdraw_record 指定实体类对应的数据库表名为 "withdraw_record"
 */
@TableName(value = "withdraw_record")
@Builder
@Data
public class WithdrawRecordEntity extends Model<WithdrawRecordEntity> implements Serializable {
    /**
     * 提现记录的唯一标识符ID
     */
    @TableId
    private String id;

    /**
     * 提现金额，单位通常为分
     */
    private Double amount;

    /**
     * 交易处理时间，记录提现处理的具体时间点
     */
    private Timestamp dealTime;

    /**
     * 手续费金额，提现过程中产生的费用
     */
    private Double handlingFee;

    /**
     * 用户ID，提现操作的执行者
     */
    private String memberId;

    /**
     * 提现记录的备注信息，用于提供额外的说明或注意事项
     */
    private String note;

    /**
     * 订单编号，关联特定的提现请求
     */
    private String orderNo;

    /**
     * 结算账户ID，提现资金的目的地账户
     */
    private String settlementAccountId;

    /**
     * 提现状态，如待处理、处理中、已完成、已拒绝等
     */
    private String state;

    /**
     * 提交时间，用户提交提现请求的时间
     */
    private Timestamp submitTime;

    /**
     * 到账金额，实际到达用户账户的金额
     */
    private Double toTheAccount;

    /**
     * 版本号，用于乐观锁机制，确保数据的一致性
     */
    private Long version;

    /**
     * 序列化版本UID，用于类在序列化和反序列化过程中保持版本兼容性
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
        WithdrawRecordEntity other = (WithdrawRecordEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
                && (this.getDealTime() == null ? other.getDealTime() == null : this.getDealTime().equals(other.getDealTime()))
                && (this.getHandlingFee() == null ? other.getHandlingFee() == null : this.getHandlingFee().equals(other.getHandlingFee()))
                && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
                && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
                && (this.getSettlementAccountId() == null ? other.getSettlementAccountId() == null : this.getSettlementAccountId().equals(other.getSettlementAccountId()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getSubmitTime() == null ? other.getSubmitTime() == null : this.getSubmitTime().equals(other.getSubmitTime()))
                && (this.getToTheAccount() == null ? other.getToTheAccount() == null : this.getToTheAccount().equals(other.getToTheAccount()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getDealTime() == null) ? 0 : getDealTime().hashCode());
        result = prime * result + ((getHandlingFee() == null) ? 0 : getHandlingFee().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getSettlementAccountId() == null) ? 0 : getSettlementAccountId().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getSubmitTime() == null) ? 0 : getSubmitTime().hashCode());
        result = prime * result + ((getToTheAccount() == null) ? 0 : getToTheAccount().hashCode());
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
        sb.append(", amount=").append(amount);
        sb.append(", dealTime=").append(dealTime);
        sb.append(", handlingFee=").append(handlingFee);
        sb.append(", memberId=").append(memberId);
        sb.append(", note=").append(note);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", settlementAccountId=").append(settlementAccountId);
        sb.append(", state=").append(state);
        sb.append(", submitTime=").append(submitTime);
        sb.append(", toTheAccount=").append(toTheAccount);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}