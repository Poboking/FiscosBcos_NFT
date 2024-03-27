package org.knight.app.biz.artwork.dto.collection;

import lombok.Data;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/21 22:48
 */
@Data
public class GroupByTimeCollectionRespDTO {

    private String time;

    private List<ForSaleCollectionRespDTO> collections;
}
