package org.sziit.app.biz.artwork.dto.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/21 22:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupByDateCollectionRespDTO {
    private String date;

    private List<GroupByTimeCollectionRespDTO> timeCollections = new ArrayList<>();
}
