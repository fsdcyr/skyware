package com.fsdcyr.sky.authorization.interceptor;

import com.fsdcyr.sky.authorization.AuthHandler;
import com.fsdcyr.sky.authorization.annotation.LoginRequired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 5:00 下午
 */
@Slf4j
@Order(20)
public class AuthHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthHandler authHandler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        boolean requireLogin = isRequireLogin(method);
        if (requireLogin) {
            Object currentUser = authHandler.getLoginUser(request);
            if (currentUser == null) {
                throw new RuntimeException("用户未登录");
            }
        }
        return true;
    }

    private boolean isRequireLogin(Method method) {
        if (method.isAnnotationPresent(LoginRequired.class)) {
            return true;
        }
        return false;
    }

}
