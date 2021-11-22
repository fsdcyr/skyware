package com.fsdcyr.sky.authorization;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 5:23 下午
 */
public interface AuthHandler {

    /**
     * 获取登录的用户
     * @param request
     * @return
     */
    Object getLoginUser(HttpServletRequest request);
}
