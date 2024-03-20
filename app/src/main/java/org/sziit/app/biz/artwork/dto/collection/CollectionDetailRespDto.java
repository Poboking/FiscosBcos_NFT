package org.sziit.app.biz.artwork.dto.collection;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 20:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDetailRespDTO extends CollectionBaseDTO {

    private String id;

    private String commodityType;

    private String name;

    private String cover;

    private Double price;

    private Integer quantity;

    private Integer stock;

    private LocalDateTime saleTime;

    private Boolean preSaleFlag;

    private Long surplusSecond;

    private String creatorId;

    private String creatorName;

    private String creatorAvatar;

    private String[] storyPicLinks;

    private String[] subCommoditys;

}

