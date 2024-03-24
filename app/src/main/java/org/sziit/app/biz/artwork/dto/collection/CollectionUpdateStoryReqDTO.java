package org.sziit.app.biz.artwork.dto.collection;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 21:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CollectionUpdateStoryReqDTO extends CollectionBaseDTO{

    @NotNull
    private String collectionId;

    @NotNull
    private Map<Double, String> picLinks;
}
