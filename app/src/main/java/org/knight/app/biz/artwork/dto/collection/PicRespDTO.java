package org.knight.app.biz.artwork.dto.collection;

import lombok.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/12 13:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PicRespDTO extends CollectionBaseDTO{
    private Double sequence;

    private String link;
}
