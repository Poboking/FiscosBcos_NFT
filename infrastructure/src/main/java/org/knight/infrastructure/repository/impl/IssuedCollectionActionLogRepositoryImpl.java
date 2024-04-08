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
     * @return boolean
     */
    @Override
    public boolean checkCollectionLock(String issuedCollectionId) {
        return issuedCollectionActionLogMapper.exists(new QueryWrapper<IssuedCollectionActionLogEntity>()
                .eq("issued_collection_id", issuedCollectionId));
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
}




