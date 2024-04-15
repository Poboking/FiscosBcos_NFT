package org.knight.app.biz.artwork.dto.holdcollection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/25 11:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberHoldCollectionRespDTO {

    private String id;

    private Double price;

    private String issuedCollectionId;

    private String gainWay;

    private String state;

    private String stateName;

    private String holdTime;

    private String loseTime;

    private String uniqueId;

    private String transactionHash;

    private String collectionName;

    private String collectionCover;

    private String memberMobile;

    private String memberBlockChainAddr;

    private Long collectionSerialNumber;

    private Long quantity;
}
