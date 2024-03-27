package org.knight.presentation.annotation;

import cn.dev33.satoken.annotation.SaCheckLogin;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/17 21:03
 */
@SaCheckLogin(type = "admin")
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD, java.lang.annotation.ElementType.TYPE})
public @interface CheckAdminLogin {
}
