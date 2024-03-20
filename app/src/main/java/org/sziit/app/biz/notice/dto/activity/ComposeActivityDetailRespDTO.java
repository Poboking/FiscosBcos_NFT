package org.sziit.app.biz.notice.dto.activity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/19 21:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComposeActivityDetailRespDTO extends ComposeActivityBaseDTO{

    private String id;

    private String title;

    private String collectionName;

    private String collectionCover;

    private List<ComposeMaterialRespDTO> materials = new ArrayList<>();
}
