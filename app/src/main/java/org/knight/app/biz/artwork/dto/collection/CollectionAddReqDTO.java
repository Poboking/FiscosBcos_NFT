package org.knight.app.biz.artwork.dto.collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.knight.infrastructure.common.IdUtils;
import org.knight.infrastructure.common.NftConstants;
import org.knight.infrastructure.dao.domain.CollectionEntity;
import org.knight.infrastructure.dao.domain.IssuedCollectionEntity;
import org.knight.infrastructure.fisco.service.NFTService;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/25 11:40
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionAddReqDTO {

    @NotNull
    private String commodityType;

    @NotNull
    private String name;

    @NotNull
    private String cover;

    @NotNull
    private Double price;

    @NotNull
    private Integer quantity;

    @NotNull
    private LocalDateTime saleTime;

    private Boolean notExternalSaleFlag;

    /**
     * 即所谓优先购
     */
    private Boolean preSaleFlag;

    @NotNull
    private String creatorId;

    @JsonIgnore
    public static CollectionEntity buildEntity(CollectionAddReqDTO dto) {
        if (dto.getSaleTime() == null) {
            dto.setSaleTime(LocalDateTime.now());
        }
        CollectionEntity entity = new CollectionEntity();
        String id = IdUtils.snowFlakeId();
        entity.setId(id);
        entity.setCommodityType(dto.getCommodityType());
        // TODO: 2024/3/25 需调用合约
        entity.setCollectionHash(NFTService.getNFTHash(id));
        entity.setName(dto.getName());
        entity.setCover(dto.getCover());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setSaleTime(Timestamp.valueOf(dto.getSaleTime()));
        entity.setExternalSaleFlag(!dto.getNotExternalSaleFlag());
        entity.setDeletedFlag(false);
        entity.setCreatorId(dto.getCreatorId());
        return entity;
    }

    @JsonIgnore
    public static IssuedCollectionEntity buildIssuedEntity(CollectionAddReqDTO dto, String collectionId,Integer collectionSerialNumber ) {
        String now = LocalDateTime.now().format(NftConstants.DATE_FORMAT);
        LocalDateTime nowDateTime = LocalDateTime.parse(now, NftConstants.DATE_FORMAT);
        String id = IdUtils.snowFlakeId();
        return IssuedCollectionEntity.builder()
                .id(id)
                .collectionId(collectionId)
                .collectionSerialNumber(collectionSerialNumber)
                .deletedFlag(false)
                .syncChainTime(Timestamp.valueOf(nowDateTime))
                .issueTime(Timestamp.valueOf(nowDateTime))
                // TODO: 2024/3/25 需调用合约
                .uniqueId(NFTService.getNFTUniqueId(id))
                .build();
    }
}
