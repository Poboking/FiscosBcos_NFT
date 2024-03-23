package org.sziit.app.biz.artwork.dto.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/21 22:48
 */
@Data
@AllArgsConstructor
public class ForSaleCollectionRespDTO {

    private String id;

    private String name;

    private String cover;

    private Double price;

    private Integer quantity;

    private String creatorName;
}
