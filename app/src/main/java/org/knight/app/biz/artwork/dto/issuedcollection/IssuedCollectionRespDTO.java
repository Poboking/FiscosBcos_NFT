package org.knight.app.biz.artwork.dto.issuedcollection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/25 11:30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssuedCollectionRespDTO {

    private String id;

    private Integer collectionSerialNumber;

    private String issueTime;

    private String uniqueId;

    private String syncChainTime;
}
