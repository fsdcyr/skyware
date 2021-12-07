package com.fsdcyr.sky.authorization.handler;

import com.fsdcyr.sky.authorization.context.AuthContext;
import com.fsdcyr.sky.authorization.context.AuthContextManager;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 5:24 下午
 */
@Slf4j
public class DefaultAuthenticationHandler implements AuthenticationHandler {

    private AuthContextManager authContextManager;

    public DefaultAuthenticationHandler(AuthContextManager authContextManager) {
        this.authContextManager = authContextManager;
    }

    @Override
    public AuthContext doGetAuthContext(HttpServletRequest request) {
        return authContextManager.getAuthContext(request);
    }

}
