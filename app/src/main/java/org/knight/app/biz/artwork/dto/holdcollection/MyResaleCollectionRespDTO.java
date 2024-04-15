package org.knight.app.biz.artwork.dto.holdcollection;

import lombok.*;
import org.knight.app.biz.artwork.dto.collection.CollectionBaseDTO;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/1 17:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyResaleCollectionRespDTO extends CollectionBaseDTO {
    private String id;

    private String name;

    private Double resalePrice;

    private String cover;

    private String resaleDate;
}
