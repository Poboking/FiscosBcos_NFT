package org.knight.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.knight.infrastructure.dao.connector.MyHoldCollectionRespDTOParent;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 成员转售收藏品实体类，用于记录成员转售收藏品的信息。
 *
 * @TableName member_resale_collection
 */
@Data
@TableName(value = "member_resale_collection")
public class MemberResaleCollectionEntity implements Serializable, MyHoldCollectionRespDTOParent {
    /**
     * 序列化版本UID，用于类版本控制
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 转售记录的唯一标识ID
     */
    @TableId
    private String id;
    /**
     * 收藏品名称
     */
    @TableField(exist = false)
    private String name;

//    /**
//     * 收藏品顺序值，例如 #12/100
//     */
//    @TableField(exist = false)
//    private String collectionSerialNumber;
    /**
     * 收藏品封面
     */
    @TableField(exist = false)
    private String cover;
    /**
     * 取消转售的时间
     */
    private Timestamp cancelTime;
    /**
     * 转售的收藏品ID
     */
    private String collectionId;
    /**
     * 发行的收藏品ID
     */
    private String issuedCollectionId;
    /**
     * 锁定支付的成员ID & 购买藏品的成员ID
     */
    private String lockPayMemberId;
    /**
     * 成员持有收藏品ID
     */
    private String memberHoldCollectionId;
    /**
     * 转售的成员ID
     */
    private String memberId;
    /**
     * 转售价格
     */
    private Double resalePrice;
    /**
     * 转售时间
     */
    private Timestamp resaleTime;
    /**
     * 收藏品售出的时间
     */
    private Timestamp soldTime;
    /**
     * 转售状态
     */
    private String state;
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
        MemberResaleCollectionEntity other = (MemberResaleCollectionEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCancelTime() == null ? other.getCancelTime() == null : this.getCancelTime().equals(other.getCancelTime()))
                && (this.getCollectionId() == null ? other.getCollectionId() == null : this.getCollectionId().equals(other.getCollectionId()))
                && (this.getIssuedCollectionId() == null ? other.getIssuedCollectionId() == null : this.getIssuedCollectionId().equals(other.getIssuedCollectionId()))
                && (this.getLockPayMemberId() == null ? other.getLockPayMemberId() == null : this.getLockPayMemberId().equals(other.getLockPayMemberId()))
                && (this.getMemberHoldCollectionId() == null ? other.getMemberHoldCollectionId() == null : this.getMemberHoldCollectionId().equals(other.getMemberHoldCollectionId()))
                && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getResalePrice() == null ? other.getResalePrice() == null : this.getResalePrice().equals(other.getResalePrice()))
                && (this.getResaleTime() == null ? other.getResaleTime() == null : this.getResaleTime().equals(other.getResaleTime()))
                && (this.getSoldTime() == null ? other.getSoldTime() == null : this.getSoldTime().equals(other.getSoldTime()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCancelTime() == null) ? 0 : getCancelTime().hashCode());
        result = prime * result + ((getCollectionId() == null) ? 0 : getCollectionId().hashCode());
        result = prime * result + ((getIssuedCollectionId() == null) ? 0 : getIssuedCollectionId().hashCode());
        result = prime * result + ((getLockPayMemberId() == null) ? 0 : getLockPayMemberId().hashCode());
        result = prime * result + ((getMemberHoldCollectionId() == null) ? 0 : getMemberHoldCollectionId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getResalePrice() == null) ? 0 : getResalePrice().hashCode());
        result = prime * result + ((getResaleTime() == null) ? 0 : getResaleTime().hashCode());
        result = prime * result + ((getSoldTime() == null) ? 0 : getSoldTime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
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
        sb.append(", cancelTime=").append(cancelTime);
        sb.append(", collectionId=").append(collectionId);
        sb.append(", issuedCollectionId=").append(issuedCollectionId);
        sb.append(", lockPayMemberId=").append(lockPayMemberId);
        sb.append(", memberHoldCollectionId=").append(memberHoldCollectionId);
        sb.append(", memberId=").append(memberId);
        sb.append(", resalePrice=").append(resalePrice);
        sb.append(", resaleTime=").append(resaleTime);
        sb.append(", soldTime=").append(soldTime);
        sb.append(", state=").append(state);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}