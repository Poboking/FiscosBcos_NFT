package org.sziit.app.biz.notice.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 19:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDetailRespDto extends NoticeBaseDto {
    private String id;

    private String title;

    private LocalDateTime publishTime;

    private LocalDateTime lastModifyTime;

    private String content;

    private String type;

    private String typeName;
}
