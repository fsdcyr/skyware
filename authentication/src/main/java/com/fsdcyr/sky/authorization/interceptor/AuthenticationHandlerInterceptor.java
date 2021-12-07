package com.fsdcyr.sky.authorization.interceptor;

import com.fsdcyr.sky.authorization.annotation.RequiresLogin;
import com.fsdcyr.sky.authorization.context.AuthContext;
import com.fsdcyr.sky.authorization.context.AuthThreadContext;
import com.fsdcyr.sky.authorization.handler.AuthenticationHandler;
import com.fsdcyr.sky.common.exception.SkywareExceptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 认证拦截器
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 5:00 下午
 */
@Slf4j
public class AuthenticationHandlerInterceptor implements HandlerInterceptor {

    private AuthenticationHandler authenticationHandler;

    public AuthenticationHandlerInterceptor(AuthenticationHandler authenticationHandler) {
        this.authenticationHandler = authenticationHandler;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        boolean requiresLogin = isRequiresLogin(method);
        if (requiresLogin) {
            try {
                AuthContext authContext = authenticationHandler.doGetAuthContext(request);
                if (authContext == null) {
                    throw SkywareExceptions.UN_LOGIN;
                }
                // 绑定到当前线程
                AuthThreadContext.bind(authContext);
            } catch (Exception e) {
                log.error("", e);
                throw SkywareExceptions.UN_LOGIN;
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 @Nullable Exception ex) throws Exception {
        // do nothing
    }

    private boolean isRequiresLogin(Method method) {
        if (method.isAnnotationPresent(RequiresLogin.class)) {
            return true;
        }
        return false;
    }


}
