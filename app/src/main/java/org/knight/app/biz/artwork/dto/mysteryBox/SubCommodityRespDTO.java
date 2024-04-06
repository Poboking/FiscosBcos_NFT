package org.knight.app.biz.artwork.dto.mysteryBox;

import lombok.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/4/6 14:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubCommodityRespDTO extends MysteryBoxBaseDTO{

    private String commodityId;

    private Double probability;

    private String name;

    private String cover;
}
