package org.sziit.app.biz.notice.dto.notice;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 15:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeAbstractRespDTO extends NoticeBaseDTO {
    private String id;

    private String title;

    private LocalDateTime publishTime;
}
