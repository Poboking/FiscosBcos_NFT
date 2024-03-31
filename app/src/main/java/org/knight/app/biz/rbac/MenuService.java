package org.knight.app.biz.rbac;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.knight.app.biz.convert.rbac.MenuConvert;
import org.knight.app.biz.rbac.dto.MenuRespDTO;
import org.knight.infrastructure.dao.domain.AccountRoleEntity;
import org.knight.infrastructure.dao.domain.MenuEntity;
import org.knight.infrastructure.dao.domain.RoleMenuEntity;
import org.knight.infrastructure.repository.impl.AccountRoleRepositoryImpl;
import org.knight.infrastructure.repository.impl.MenuRepositoryImpl;
import org.knight.infrastructure.repository.impl.RoleMenuRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 17:07
 */
@Service
public class MenuService {
    private final MenuRepositoryImpl menuRepository;

    private final RoleMenuRepositoryImpl roleMenuRepository;

    private final AccountRoleRepositoryImpl accountRoleRepository;

    @Autowired
    public MenuService(MenuRepositoryImpl menuRepository, RoleMenuRepositoryImpl roleMenuRepository, AccountRoleRepositoryImpl accountRoleRepository) {
        this.menuRepository = menuRepository;
        this.roleMenuRepository = roleMenuRepository;
        this.accountRoleRepository = accountRoleRepository;
    }

    public List<MenuRespDTO> getMyMenuTree(String userId) {
        AccountRoleEntity roleEntity = accountRoleRepository.getOne(new QueryWrapper<AccountRoleEntity>().eq("account_id", userId));
        if (roleEntity == null) {
            return null;
        }
        String roleId = roleEntity.getRoleId();
        if (roleId == null) {
            return null;
        }
        List<RoleMenuEntity> roleList = roleMenuRepository.list(new QueryWrapper<RoleMenuEntity>().eq("role_id", roleId));
        if (roleList == null || roleList.isEmpty()) {
            return null;
        }
        List<String> menuIds = new ArrayList<>();
        roleList.forEach(roleMenuEntity -> menuIds.add(roleMenuEntity.getMenuId()));
        List<MenuEntity> menuEntitys = new ArrayList<>();
        menuIds.forEach(menuId -> {
            MenuEntity menuEntity = menuRepository.getOne(new QueryWrapper<MenuEntity>().eq("id", menuId));
            if (menuEntity != null) {
                menuEntitys.add(menuEntity);
            }
        });
        return MenuConvert.convertToRespDTO(menuEntitys);
    }
}
