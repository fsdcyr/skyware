package com.fsdcyr.sky.authorization.config;

import com.fsdcyr.sky.authorization.context.AuthContextManager;
import com.fsdcyr.sky.authorization.handler.AuthenticationHandler;
import com.fsdcyr.sky.authorization.handler.DefaultAuthenticationHandler;
import com.fsdcyr.sky.authorization.interceptor.AuthenticationHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 认证 web环境配置
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-23 10:50 上午
 */
public class AuthenticationWebConfiguration implements WebMvcConfigurer {

    @Resource
    private AuthenticationProperties authenticationProperties;

    @Resource
    private AuthenticationHandlerInterceptor authenticationHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationHandlerInterceptor)
                .excludePathPatterns(authenticationProperties.getExcludePathPattern().split(","))
                .order(-1);
    }

    @Bean
    public AuthContextManager authContextManager() {
        return new AuthContextManager();
    }

    @Bean
    public AuthenticationHandler authHandler(AuthContextManager authContextManager) {
        return new DefaultAuthenticationHandler(authContextManager);
    }

    @Bean
    public AuthenticationHandlerInterceptor authHandlerInterceptor(AuthenticationHandler authenticationHandler) {
        return new AuthenticationHandlerInterceptor(authenticationHandler);
    }


}
