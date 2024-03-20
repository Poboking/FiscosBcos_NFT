package org.sziit.app.biz.artwork.dto.creator;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/10 20:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatorRespDTO extends CreatorBaseDTO {

    private String id;

    private String name;

    private String avatar;

    private LocalDateTime createTime;

    private LocalDateTime lastModifyTime;
}
