package org.sziit.app.biz.artwork.dto.collection;

import lombok.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/19 17:53
 */
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionIntroRespDTO extends CollectionBaseDTO {

    private String id;

    private String name;

    private String cover;

    private Double price;

    private Integer quantity;

    private Integer stock;

    private String saleTime;

    private Boolean preSaleFlag;

    private Integer surplusSecond;

    private String creatorName;

    private String creatorAvatar;
}
