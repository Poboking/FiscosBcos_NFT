package org.knight.presentation.admin;

import com.feiniaojin.gracefulresponse.api.ValidationStatusCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.knight.app.biz.account.BackgroundAccountService;
import org.knight.app.biz.account.dto.account.background.BackgroundAccountRespDTO;
import org.knight.app.biz.rbac.MenuService;
import org.knight.app.biz.rbac.dto.MenuRespDTO;
import org.knight.presentation.utils.StpAdminUtil;

import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 14:31
 */
@Log4j2
@RestController
@RequestMapping("/back/rbac/")
public class RBACController {
    private final BackgroundAccountService accountService;

    private final MenuService menuService;

    @Autowired
    public RBACController(BackgroundAccountService backgroundAccountService, MenuService menuService) {
        this.accountService = backgroundAccountService;
        this.menuService = menuService;
    }

    @GetMapping("findMyMenuTree")
    @ValidationStatusCode(code = "500")
    public List<MenuRespDTO> findMyMenuTree() {
        String loginId = StpAdminUtil.getLoginIdAsString();
        return menuService.getMyMenuTree(loginId);
    }

    @GetMapping("getAccountInfo")
    @ValidationStatusCode(code = "500")
    public BackgroundAccountRespDTO getAccountInfo() {
        String loginId = StpAdminUtil.getLoginIdAsString();
        return accountService.getAccountInfo(loginId);
    }

}
