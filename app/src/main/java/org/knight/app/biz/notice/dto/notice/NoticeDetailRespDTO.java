package org.knight.app.biz.notice.dto.notice;

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
public class NoticeDetailRespDTO extends NoticeBaseDTO {
    private String id;

    private String title;

    private LocalDateTime publishTime;

    private LocalDateTime lastModifyTime;

    private String content;

    private String type;

    private String typeName;
}
