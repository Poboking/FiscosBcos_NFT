package org.sziit.app.biz.rbac.dto;

import lombok.*;
import org.sziit.infrastructure.dao.domain.MenuEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 14:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuRespDTO extends MenuEntity {

    private String id;

    private String name;

    private String url;

    private String type;

    private Double orderNo;

    private String parentId;

    private List<MenuRespDTO> subMenus = new ArrayList<>();
}
