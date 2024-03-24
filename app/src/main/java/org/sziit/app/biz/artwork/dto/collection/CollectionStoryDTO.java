package org.sziit.app.biz.artwork.dto.collection;

import lombok.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 20:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionStoryDTO extends CollectionBaseDTO {

    private String picLink;
}
