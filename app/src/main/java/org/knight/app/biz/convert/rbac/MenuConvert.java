package org.knight.app.biz.convert.rbac;

import org.knight.app.biz.rbac.dto.MenuRespDTO;
import org.knight.infrastructure.common.SortUtil;
import org.knight.infrastructure.dao.domain.MenuEntity;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 17:32
 */
public class MenuConvert {

    public static MenuRespDTO convertToRespDTO(MenuEntity menuEntity){
        return MenuRespDTO.builder()
                .id(menuEntity.getId())
                .name(menuEntity.getName())
                .url(menuEntity.getUrl())
                .type(menuEntity.getType())
                .orderNo(menuEntity.getOrderNo())
                .parentId(menuEntity.getParentId())
                .build();
    }

    public static List<MenuRespDTO> convertToRespDTO(List<MenuEntity> menuEntitys){
        List<MenuRespDTO> parents = null;
        for (MenuEntity entity : menuEntitys) {
            if (entity.getParentId() == null) {
                parents.add(convertToRespDTO(entity));
                menuEntitys.remove(entity);
            }
        }
        for (MenuRespDTO parent : parents) {
            for (MenuEntity entity : menuEntitys) {
                if (entity.getParentId().equals(parent.getId())) {
                    parent.getSubMenus().add(convertToRespDTO(entity));
                    menuEntitys.remove(entity);
                }
            }
        }
        for (MenuRespDTO parent : parents) {
            if (parent.getSubMenus().size() > 1) {
                SortUtil.defaultQuickSort(parent.getSubMenus());
            }
        }
        SortUtil.defaultQuickSort(parents);
        return parents;
    }
}
