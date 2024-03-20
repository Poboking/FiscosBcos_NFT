package org.sziit.presentation.config;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/16 16:17
 * 自定义侦听器的实现
 */
@Log4j2
@Component
public class MySaTokenListener implements SaTokenListener {

    /** 每次登录时触发 */
    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        log.info("---------- doLogin侦听器: " + loginType + "登录成功，tokenValue: " + tokenValue);
    }

    /** 每次注销时触发 */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
        log.info("---------- doLogout侦听器: " + loginType + "注销成功，tokenValue: " + tokenValue);
    }

    /** 每次被踢下线时触发 */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
        log.info("---------- doKickout侦听器: " + loginType + "被踢下线，tokenValue: " + tokenValue);
    }

    /** 每次被顶下线时触发 */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
        log.info("---------- doReplaced侦听器: " + loginType + "被顶下线，tokenValue: " + tokenValue);
    }

    /** 每次被封禁时触发 */
    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {
        log.info("---------- doDisable侦听器: " + loginType + "被封禁，tokenValue: " + service);
    }

    /** 每次被解封时触发 */
    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {
        log.info("---------- doUntieDisable侦听器: " + loginType + "被解封，tokenValue: " + service);
    }

    /** 每次二级认证时触发 */
    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {
        log.info("---------- doOpenSafe侦听器: " + loginType + "二级认证成功，tokenValue: " + tokenValue);
    }

    /** 每次退出二级认证时触发 */
    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {
        log.info("---------- doCloseSafe侦听器: " + loginType + "退出二级认证，tokenValue: " + tokenValue);
    }

    /** 每次创建Session时触发 */
    @Override
    public void doCreateSession(String id) {
        log.info("---------- doCreateSession侦听器: 创建Session，id: " + id);
    }

    /** 每次注销Session时触发 */
    @Override
    public void doLogoutSession(String id) {
        log.info("---------- doLogoutSession侦听器: 注销Session，id: " + id);
    }

    /** 每次Token续期时触发 */
    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
        log.info("---------- doRenewTimeout侦听器: Token续期，tokenValue: " + tokenValue);
    }
}
