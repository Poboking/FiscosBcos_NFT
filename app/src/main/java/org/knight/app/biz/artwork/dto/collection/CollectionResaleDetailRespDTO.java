package org.knight.app.biz.artwork.dto.collection;

import lombok.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/31 18:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionResaleDetailRespDTO extends CollectionBaseDTO{

    private String id;

    private String issuedCollectionId;

    private String lockPayMemberId;

    private String commodityType;

    private Double resalePrice;

    private String collectionName;

    private String collectionCover;

    private Integer quantity;

    private Integer collectionSerialNumber;

    private String uniqueId;

    private String collectionHash;

    private String transactionHash;

    private String creatorId;

    private String creatorAvatar;

    private String creatorName;

    private String holderNickName;

    private String holderAvatar;

    private String holderBlockChainAddr;

    private String[] storyPicLinks;

    private String[] subCommoditys;
}
