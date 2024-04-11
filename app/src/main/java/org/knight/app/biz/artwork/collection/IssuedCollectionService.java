package org.knight.app.biz.artwork.collection;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.knight.app.biz.artwork.dto.collection.CollectionAddReqDTO;
import org.knight.app.biz.artwork.dto.issuedcollection.IssuedCollectionRespDTO;
import org.knight.app.biz.exception.collection.IssuedCollectionCastFailedException;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.dao.domain.IssuedCollectionEntity;
import org.knight.infrastructure.fisco.module.BlockChainNFT;
import org.knight.infrastructure.fisco.service.biz.ChainService;
import org.knight.infrastructure.repository.impl.IssuedCollectionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/25 20:46
 */
@Service
public class IssuedCollectionService {

    private final IssuedCollectionRepositoryImpl issuedCollectionRepository;

    private final ChainService chainService;

    @Autowired
    public IssuedCollectionService(IssuedCollectionRepositoryImpl issuedCollectionRepository, ChainService chainService) {
        this.issuedCollectionRepository = issuedCollectionRepository;
        this.chainService = chainService;
    }


    public Boolean castIssuedCollection(CollectionAddReqDTO reqDTO, String collectionId, Integer serialNumber) {
        IssuedCollectionEntity issuedEntity = CollectionAddReqDTO.buildIssuedEntity(reqDTO, collectionId, serialNumber);
        return issuedCollectionRepository.save(issuedEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Async
    public Boolean castIssuedCollection(CollectionAddReqDTO reqDTO, String collectionId) {
        for (int i = 0; i < reqDTO.getQuantity(); i++) {
            IssuedCollectionEntity issuedEntity = CollectionAddReqDTO.buildIssuedEntity(reqDTO, collectionId, i + 1);
            BlockChainNFT nft = chainService.issuedCollection(reqDTO.getName(), reqDTO.getCreatorId(), issuedEntity.getId(), i + 1, reqDTO.getQuantity());
            if (nft == null) {
                throw new IssuedCollectionCastFailedException("IssuedCollectionEntity cast failed on FISCO BCOS Chain");
            }
            issuedEntity.setUniqueId(nft.getUniqueId());
            issuedEntity.setSyncChainTime(nft.getSyncChainTime());
            if (Boolean.FALSE.equals(issuedCollectionRepository.save(issuedEntity))) {
                throw new IssuedCollectionCastFailedException("IssuedCollectionEntity save failed");
            }
        }
        return true;
    }


    public List<IssuedCollectionRespDTO> findIssuedCollection(String collectionId) {
        List<IssuedCollectionEntity> listEntity = issuedCollectionRepository.list(new QueryWrapper<IssuedCollectionEntity>()
                .eq(Optional.ofNullable(collectionId).isPresent(), "collection_id", collectionId)
                .eq("deleted_flag", false)
                .orderByDesc("collection_serial_number"));
        List<IssuedCollectionRespDTO> respDTOS = new ArrayList<>();
        listEntity.forEach(item -> {
            respDTOS.add(IssuedCollectionRespDTO.builder()
                    .id(item.getId())
                    .collectionSerialNumber(item.getCollectionSerialNumber())
                    .issueTime(LocalDateTime.parse(item.getIssueTime().toLocalDateTime().format(NftConstants.DATE_FORMAT)))
                    .uniqueId(item.getUniqueId())
                    .syncChainTime(item.getSyncChainTime().toLocalDateTime())
                    .build());
        });
        return respDTOS;
    }

    // TODO: 2024/4/9 待完善
    public String getCollectionHash(String collectionId) {
        return issuedCollectionRepository.list(new QueryWrapper<IssuedCollectionEntity>()
                .eq("collection_id", collectionId)).get(0).getUniqueId();
    }
}
