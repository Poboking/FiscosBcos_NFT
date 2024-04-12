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

    /**
     * 这里用 GiveRecordId 作为替代
     */
    private String orderNo;

    private String giveFromId;

    private String giveFromMobile;

    private String giveToId;

    private String giveToMobile;

    private String collectionName;

    private LocalDateTime giveTime;

    private String holdCollectionId;
}
