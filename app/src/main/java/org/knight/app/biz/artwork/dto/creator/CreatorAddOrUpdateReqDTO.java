package org.knight.app.biz.artwork.dto.creator;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 20:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatorAddOrUpdateReqDTO extends CreatorBaseDTO {

    private String id;

    @NotNull
    private String name;

    @NotNull
    private String avatar;

    private Timestamp createTime;

    private Timestamp lastModifyTime;
}
