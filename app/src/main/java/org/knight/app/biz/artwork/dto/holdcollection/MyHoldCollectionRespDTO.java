package org.knight.app.biz.artwork.dto.holdcollection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.knight.infrastructure.dao.connector.MyHoldCollectionRespDTOParent;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 17:51
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyHoldCollectionRespDTO implements MyHoldCollectionRespDTOParent {

    private String id;

    private String name;

    private String cover;

    private String holdDate;
}
