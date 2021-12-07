package com.fsdcyr.sky.authorization.handler;

import com.fsdcyr.sky.authorization.context.AuthContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 5:23 下午
 */
public interface AuthenticationHandler {

    /**
     * 获取已登录认证信息
     * @param request
     * @return
     */
    AuthContext doGetAuthContext(HttpServletRequest request);


}
