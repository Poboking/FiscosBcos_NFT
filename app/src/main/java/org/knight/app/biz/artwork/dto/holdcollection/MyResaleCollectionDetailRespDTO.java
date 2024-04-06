package org.knight.app.biz.artwork.dto.holdcollection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.knight.app.biz.artwork.dto.collection.CollectionBaseDTO;
import org.knight.app.biz.artwork.dto.mysteryBox.SubCommodityRespDTO;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/6 14:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyResaleCollectionDetailRespDTO extends CollectionBaseDTO {

    private String id;

    private String issuedCollectionId;

    private String lockPayMemberId;

    private String commodityType;

    private String resalePrice;

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

    private List<String> storyPicLinks;

    private List<SubCommodityRespDTO> subCommoditys;
}
