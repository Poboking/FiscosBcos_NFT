package org.sziit.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.sziit.infrastructure.dao.connector.MyHoldCollectionVoParent;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 成员持有收藏品实体类，用于记录成员持有的收藏品信息。
 *
 * @TableName member_hold_collection
 */
@Data
@TableName(value = "member_hold_collection")
public class MemberHoldCollectionEntity extends Model<MemberHoldCollectionEntity> implements Serializable, MyHoldCollectionVoParent {
    /**
     * 记录的唯一标识ID
     */
    @TableId
    private String id;

    /**
     * 藏品名称
     */
    @TableField(exist = false)
    private String name;

    /**
     * 藏品封面
     */
    @TableField(exist = false)
    private String cover;

    /**
     * 收藏品ID
     */
    private String collectionId;

    /**
     * 获取方式，如购买、赠送等
     */
    private String gainWay;

    /**
     * 持有时间
     */
    private LocalDateTime holdTime;

    /**
     * 发行的收藏品ID
     */
    private String issuedCollectionId;

    /**
     * 失去时间，如转售、赠送等
     */
    private LocalDateTime loseTime;

    /**
     * 成员ID
     */
    private String memberId;

    /**
     * 上一条记录的ID，用于记录变更历史
     */
    private String preId;

    /**
     * 价格
     */
    private Double price;

    /**
     * 状态，如持有、转售等
     */
    private String state;

    /**
     * 与区块链同步的时间
     */
    private LocalDateTime syncChainTime;

    /**
     * 交易哈希，用于记录区块链上的交易信息
     */
    private String transactionHash;

    /**
     * 版本号，用于乐观锁控制
     */
    private Long version;

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
        MemberHoldCollectionEntity other = (MemberHoldCollectionEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCollectionId() == null ? other.getCollectionId() == null : this.getCollectionId().equals(other.getCollectionId()))
                && (this.getGainWay() == null ? other.getGainWay() == null : this.getGainWay().equals(other.getGainWay()))
                && (this.getHoldTime() == null ? other.getHoldTime() == null : this.getHoldTime().equals(other.getHoldTime()))
                && (this.getIssuedCollectionId() == null ? other.getIssuedCollectionId() == null : this.getIssuedCollectionId().equals(other.getIssuedCollectionId()))
                && (this.getLoseTime() == null ? other.getLoseTime() == null : this.getLoseTime().equals(other.getLoseTime()))
                && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getPreId() == null ? other.getPreId() == null : this.getPreId().equals(other.getPreId()))
                && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getSyncChainTime() == null ? other.getSyncChainTime() == null : this.getSyncChainTime().equals(other.getSyncChainTime()))
                && (this.getTransactionHash() == null ? other.getTransactionHash() == null : this.getTransactionHash().equals(other.getTransactionHash()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCollectionId() == null) ? 0 : getCollectionId().hashCode());
        result = prime * result + ((getGainWay() == null) ? 0 : getGainWay().hashCode());
        result = prime * result + ((getHoldTime() == null) ? 0 : getHoldTime().hashCode());
        result = prime * result + ((getIssuedCollectionId() == null) ? 0 : getIssuedCollectionId().hashCode());
        result = prime * result + ((getLoseTime() == null) ? 0 : getLoseTime().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getPreId() == null) ? 0 : getPreId().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getSyncChainTime() == null) ? 0 : getSyncChainTime().hashCode());
        result = prime * result + ((getTransactionHash() == null) ? 0 : getTransactionHash().hashCode());
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
        sb.append(", collectionId=").append(collectionId);
        sb.append(", gainWay=").append(gainWay);
        sb.append(", holdTime=").append(holdTime);
        sb.append(", issuedCollectionId=").append(issuedCollectionId);
        sb.append(", loseTime=").append(loseTime);
        sb.append(", memberId=").append(memberId);
        sb.append(", preId=").append(preId);
        sb.append(", price=").append(price);
        sb.append(", state=").append(state);
        sb.append(", syncChainTime=").append(syncChainTime);
        sb.append(", transactionHash=").append(transactionHash);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}