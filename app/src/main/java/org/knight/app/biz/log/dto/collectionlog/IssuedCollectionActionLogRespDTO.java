package org.knight.app.biz.log.dto.collectionlog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/25 11:22
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IssuedCollectionActionLogRespDTO {

    private String id;

    private String actionTime;

    private String actionDesc;

    private String memberNickName;
}
