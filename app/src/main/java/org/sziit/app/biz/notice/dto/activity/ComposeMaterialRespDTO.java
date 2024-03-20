package org.sziit.app.biz.notice.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/19 21:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComposeMaterialRespDTO {

    private Integer quantity;

    private String materialId;

    private String materialName;

    private String materialCover;
}
