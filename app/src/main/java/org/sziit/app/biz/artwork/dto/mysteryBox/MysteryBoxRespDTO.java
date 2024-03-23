package org.sziit.app.biz.artwork.dto.mysteryBox;

import lombok.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/19 20:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MysteryBoxRespDTO extends MysteryBoxBaseDTO {

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
