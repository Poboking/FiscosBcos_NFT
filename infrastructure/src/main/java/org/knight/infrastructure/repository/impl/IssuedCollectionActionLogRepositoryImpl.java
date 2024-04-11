package org.knight.infrastructure.repository.impl;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.common.IdUtils;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.dao.domain.IssuedCollectionActionLogEntity;
import org.knight.infrastructure.dao.mapper.IssuedCollectionActionLogMapper;
import org.knight.infrastructure.repository.IssuedCollectionActionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author poboking
 * @description 针对表【issued_collection_action_log】的数据库操作Service实现
 * @createDate 2024-03-07 17:31:42
 */
@Service
public class IssuedCollectionActionLogRepositoryImpl extends ServiceImpl<IssuedCollectionActionLogMapper, IssuedCollectionActionLogEntity>
        implements IssuedCollectionActionLogRepository {

    private final IssuedCollectionActionLogMapper issuedCollectionActionLogMapper;

    @Autowired
    public IssuedCollectionActionLogRepositoryImpl(IssuedCollectionActionLogMapper issuedCollectionActionLogMapper) {
        this.issuedCollectionActionLogMapper = issuedCollectionActionLogMapper;
    }

    /**
     * 检查藏品是否被锁定 - 用户购买
     *
     * @param issuedCollectionId 发行藏品ID
     * @param now                操作时间
     * @return boolean
     */
    @Override
    public boolean checkCollectionLock(String issuedCollectionId, Timestamp now) {
        return issuedCollectionActionLogMapper.exists(new QueryWrapper<IssuedCollectionActionLogEntity>()
                .eq("issued_collection_id", issuedCollectionId)
                .gt("action_time", now));
    }

    /**
     * 锁定藏品 - 用户购买或转赠等
     *
     * @param desc               描述
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户ID
     * @return boolean
     */
    @Override
    public Boolean lockCollection(String desc, String issuedCollectionId, String memberId) {
        if (CharSequenceUtil.isBlank(issuedCollectionId) || CharSequenceUtil.isBlank(memberId)) {
            return false;
        }
        IssuedCollectionActionLogEntity entity = new IssuedCollectionActionLogEntity();
        entity.setId(IdUtils.snowFlakeId());
        entity.setActionDesc(desc);
        entity.setMemberId(memberId);
        entity.setIssuedCollectionId(issuedCollectionId);
        entity.setActionTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)));
        return issuedCollectionActionLogMapper.insert(entity) > 0;
    }


    /**
     * 更新操作日志
     *
     * @param desc               描述
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户ID
     * @return boolean
     */
    @Override
    public Boolean updateActionLog(String desc, String issuedCollectionId, String memberId) {
        if (CharSequenceUtil.isBlank(issuedCollectionId) || CharSequenceUtil.isBlank(memberId)) {
            return false;
        }
        IssuedCollectionActionLogEntity entity = issuedCollectionActionLogMapper.selectOne(new QueryWrapper<IssuedCollectionActionLogEntity>()
                .eq("issued_collection_id", issuedCollectionId)
                .eq("member_id", memberId));
        if (Objects.isNull(entity)) {
            return false;
        }
        entity.setActionDesc(desc);
        entity.setActionTime(Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)));
        return issuedCollectionActionLogMapper.insert(entity) > 0;
    }


    /**
     * 根据发行藏品ID获取持有用户ID
     *
     * @param issuedCollectionId 发行藏品ID
     * @return string 用户ID
     */
    @Override
    public String getHoldMemberIdByIssuedId(String issuedCollectionId) {
        IssuedCollectionActionLogEntity entity = issuedCollectionActionLogMapper.selectOne(new QueryWrapper<IssuedCollectionActionLogEntity>()
                .eq("issued_collection_id", issuedCollectionId));
        if (Objects.isNull(entity)) {
            throw new RuntimeException("未找到发行藏品ID对应的持有用户ID");
        }
        return entity.getMemberId();
    }

    /**
     * 解锁藏品 - 用户购买或转赠等
     *
     * @param issuedCollectionId 发行藏品ID
     * @return boolean
     */
    @Override
    public Boolean unlockCollection(String issuedCollectionId, String memberId) {
        if (CharSequenceUtil.isBlank(issuedCollectionId)) {
            return false;
        }
        return issuedCollectionActionLogMapper.delete(new QueryWrapper<IssuedCollectionActionLogEntity>()
                .eq("issued_collection_id", issuedCollectionId)
                .eq("member_id", memberId)
                .lt("action_time", Timestamp.valueOf(LocalDateTime.now().format(NftConstants.DATE_FORMAT)))) > 0;
    }

    /**
     * 检查被锁定藏品类别
     *
     * @param issuedCollectionId 发行藏品ID
     * @param memberId           用户ID
     * @return string descType
     */
    @Override
    public String checkLockType(String issuedCollectionId, String memberId) {
        if (CharSequenceUtil.isBlank(issuedCollectionId) || CharSequenceUtil.isBlank(memberId)){
            return null;
        }
        IssuedCollectionActionLogEntity entity = issuedCollectionActionLogMapper.selectOne(new QueryWrapper<IssuedCollectionActionLogEntity>()
                .eq("issued_collection_id", issuedCollectionId)
                .eq("member_id", memberId));
        if (Objects.isNull(entity)){
            return null;
        }
        return entity.getActionDesc();
    }
}




