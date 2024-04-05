package org.knight.infrastructure.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.knight.infrastructure.dao.domain.IssuedCollectionActionLogEntity;
import org.knight.infrastructure.dao.mapper.IssuedCollectionActionLogMapper;
import org.knight.infrastructure.repository.IssuedCollectionActionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}




