package org.knight.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import org.knight.infrastructure.dao.domain.IssuedCollectionActionLogEntity;

import java.sql.Timestamp;

/**
 * @author poboking
 * @description 针对表【issued_collection_action_log】的数据库操作Service
 * @createDate 2024-03-07 17:31:42
 */
public interface IssuedCollectionActionLogRepository extends IService<IssuedCollectionActionLogEntity> {

    /**
     * 检查藏品是否被锁定 - 用户购买
     *
     * @param issuedCollectionId 发行藏品ID
     * @param now                操作时间
     * @return boolean
     */
    boolean checkMultithreadingCollectionLock(String issuedCollectionId, Timestamp now);

    /**
     * 检查藏品是否被锁定 - 根据流转状态
     *
     * @param issuedCollectionId 发行藏品ID
     * @return boolean
     */
    Boolean checkCollectionLock(String issuedCollectionId);

    /**
     * 锁定藏品 - 用户购买或转赠等
     *
     * @param desc               描述
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户ID
     * @return boolean
     */
    Boolean lockCollection(String desc, String issuedCollectionId, String memberId);

    /**
     * 更新操作日志
     *
     * @param desc               描述
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户ID
     * @return boolean
     */
    Boolean updateActionLog(String desc, String issuedCollectionId, String memberId);

    /**
     * 根据发行藏品ID获取持有用户ID
     *
     * @param issuedCollectionId 发行藏品ID
     * @return string 用户ID
     */
    String getHoldMemberIdByIssuedId(String issuedCollectionId);

    /**
     * 解锁藏品 - 用户购买或转赠等
     *
     * @param issuedCollectionId 发行藏品ID
     * @return boolean
     */
    public Boolean unlockCollection(String issuedCollectionId, String memberId);

    /**
     * 检查被锁定藏品类别
     *
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户ID
     * @return string descType
     */
    String checkLockType(String issuedCollectionId, String memberId);

}
