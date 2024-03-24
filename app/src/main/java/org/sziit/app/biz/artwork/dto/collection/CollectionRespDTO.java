package org.sziit.app.biz.artwork.dto.collection;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 20:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionRespDTO extends CollectionBaseDTO {

    private String id;

    private String cover;

    private String collectionHash;

    private Double price;

    private Integer quantity;

    private Integer stock;

    private LocalDateTime saleTime;

    private Boolean externalSaleFlag;

    private Boolean preSaleFlag;

    private String commodityType;

    private String commodityTypeName;

    private LocalDateTime createTime;

    private String creatorName;

    private String name;

    private List<CollectionStoryDTO> collectionStorys;
}
