package org.knight.app.biz.artwork.collection;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.knight.app.biz.artwork.dto.collection.CollectionAddReqDTO;
import org.knight.app.biz.artwork.dto.issuedcollection.IssuedCollectionRespDTO;
import org.knight.app.biz.exception.BizException;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.dao.domain.IssuedCollectionEntity;
import org.knight.infrastructure.repository.impl.IssuedCollectionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public IssuedCollectionService(IssuedCollectionRepositoryImpl issuedCollectionRepository) {
        this.issuedCollectionRepository = issuedCollectionRepository;
    }


    public Boolean addIssuedCollection(CollectionAddReqDTO reqDTO, String collectionId, Integer serialNumber) {
        IssuedCollectionEntity issuedEntity = CollectionAddReqDTO.buildIssuedEntity(reqDTO, collectionId, serialNumber);
        return issuedCollectionRepository.save(issuedEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean addIssuedCollection(CollectionAddReqDTO reqDTO, String collectionId) {
       for (int i = 0; i < reqDTO.getQuantity(); i++) {
           IssuedCollectionEntity issuedEntity = CollectionAddReqDTO.buildIssuedEntity(reqDTO, collectionId, i + 1);
           if (Boolean.FALSE.equals(issuedCollectionRepository.save(issuedEntity))){
               throw new BizException("IssuedCollectionEntity save failed");
           }
       }
       return true;
    }


    public List<IssuedCollectionRespDTO> findIssuedCollection(String collectionId) {
        List<IssuedCollectionEntity> listEntity = issuedCollectionRepository.list(new QueryWrapper<IssuedCollectionEntity>()
                .eq(Optional.ofNullable(collectionId).isPresent(),"collection_id", collectionId)
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
}
