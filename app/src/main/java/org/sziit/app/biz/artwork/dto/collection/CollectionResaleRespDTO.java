package org.sziit.app.biz.artwork.dto.collection;

import lombok.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/19 18:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionResaleRespDTO extends CollectionBaseDTO{

    private String id;

    private String collectionName;

    private String collectionCover;

    private Integer quantity;

    private Integer collectionSerialNumber;

    private Double resalePrice;

    private String creatorName;

}
