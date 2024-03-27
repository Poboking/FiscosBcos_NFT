package org.knight.infrastructure.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 存储实体类，用于记录和管理文件存储相关信息。
 *
 * @TableName storage
 */
@Data
@TableName(value = "storage")
public class StorageEntity extends Model<StorageEntity> implements Serializable {
    /**
     * 序列化版本UID，用于类版本控制
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 存储记录的唯一标识ID
     */
    @TableId
    private String id;
    /**
     * 关联业务类型，如用户头像、商品图片等
     */
    private String associateBiz;
    /**
     * 关联业务的唯一标识ID
     */
    private String associateId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件大小，单位为字节
     */
    private Long fileSize;
    /**
     * 文件类型，如图片、视频等
     */
    private String fileType;
    /**
     * 文件上传时间
     */
    private Timestamp uploadTime;
    /**
     * 文件访问URL
     */
    private String url;
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
        StorageEntity other = (StorageEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAssociateBiz() == null ? other.getAssociateBiz() == null : this.getAssociateBiz().equals(other.getAssociateBiz()))
                && (this.getAssociateId() == null ? other.getAssociateId() == null : this.getAssociateId().equals(other.getAssociateId()))
                && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
                && (this.getFileSize() == null ? other.getFileSize() == null : this.getFileSize().equals(other.getFileSize()))
                && (this.getFileType() == null ? other.getFileType() == null : this.getFileType().equals(other.getFileType()))
                && (this.getUploadTime() == null ? other.getUploadTime() == null : this.getUploadTime().equals(other.getUploadTime()))
                && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
                && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAssociateBiz() == null) ? 0 : getAssociateBiz().hashCode());
        result = prime * result + ((getAssociateId() == null) ? 0 : getAssociateId().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFileSize() == null) ? 0 : getFileSize().hashCode());
        result = prime * result + ((getFileType() == null) ? 0 : getFileType().hashCode());
        result = prime * result + ((getUploadTime() == null) ? 0 : getUploadTime().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
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
        sb.append(", associateBiz=").append(associateBiz);
        sb.append(", associateId=").append(associateId);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", fileType=").append(fileType);
        sb.append(", uploadTime=").append(uploadTime);
        sb.append(", url=").append(url);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}