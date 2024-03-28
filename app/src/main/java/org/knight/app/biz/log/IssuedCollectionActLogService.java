package org.knight.app.biz.log;

import org.knight.app.biz.log.dto.collectionlog.IssuedCollectionActionLogRespDTO;
import org.knight.infrastructure.repository.impl.IssuedCollectionActionLogRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/28 11:12
 */
@Service
public class IssuedCollectionActLogService {
    private final IssuedCollectionActionLogRepositoryImpl actionLogRepository;

    @Autowired
    public IssuedCollectionActLogService(IssuedCollectionActionLogRepositoryImpl actionLogRepository) {
        this.actionLogRepository = actionLogRepository;
    }

    // TODO: 2024/3/28
    public List<IssuedCollectionActionLogRespDTO> findIssuedCollectionActionLog(String issuedCollectionId) {
        return null;
    }
}
