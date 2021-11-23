package com.fsdcyr.sky.authorization.config;

import com.fsdcyr.sky.authorization.service.TokenExpireStrategy;
import com.fsdcyr.sky.authorization.service.impl.DefaultTokenExpireStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-23 10:12 上午
 */
@Configuration
public class AuthorizationConfiguration {

    @Bean
    @ConditionalOnMissingBean(TokenExpireStrategy.class)
    public TokenExpireStrategy defaultTokenExpireStrategy() {
        return new DefaultTokenExpireStrategy();
    }

}
