package org.sziit.presentation.config;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/16 16:08
 */
// 实现角色权限接口 - 用于角色授权
@Component
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> authorityList = new ArrayList<>();
        if (loginType.equals("admin")) {
            authorityList.add("admin");
        } else if (loginType.equals("user")) {
            authorityList.add("user.myArtwork");
            authorityList.add("user.transaction");
            authorityList.add("user.storage");
            authorityList.add("user.notice");
            authorityList.add("user.member");
            authorityList.add("user.dictconfig");
            authorityList.add("user.collection");
        }
        return authorityList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> authorityList = new ArrayList<>();
        if (loginType.equals("admin")) {
            authorityList.add("admin");
        } else if (loginType.equals("user")) {
            authorityList.add("user");
        }
        return authorityList;
    }
}
