package org.knight.app.biz.transaction.dto.giverecord;

import lombok.*;
import org.knight.app.biz.transaction.dto.TransactionBaseDTO;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/18 11:32
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionGiveRecordRespDTO extends TransactionBaseDTO {

    private String giveFromId;

    private String giveToId;

    private LocalDateTime giveTime;

    private String holdCollectionId;
}
