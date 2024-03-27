package org.knight.app.biz.notice.dto.activity;

import lombok.*;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 19:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ComposeActivityRespDTO extends ComposeActivityBaseDTO {
    private String id;

    private String title;

    private String content;

    private String type;

    private String typeName;
}
