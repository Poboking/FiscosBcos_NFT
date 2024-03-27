package org.knight.app.biz.artwork.dto.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/21 23:23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDateQueryReqDTO extends CollectionQueryReqDTO {

    private LocalDateTime saleTimeStart;

    private LocalDateTime saleTimeEnd;
}
