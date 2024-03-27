package org.knight.app.biz.artwork.dto.collection;

import lombok.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 15:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionStatisticDataRespDTO extends CollectionBaseDTO {

    private Long collectionCount;

    private Long mysteryBoxCount;
}
