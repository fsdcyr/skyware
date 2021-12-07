package com.fsdcyr.sky.authorization.config;

import com.fsdcyr.sky.authorization.filter.AuthContextHttpFilter;
import com.fsdcyr.sky.authorization.service.ExpireStrategy;
import com.fsdcyr.sky.authorization.service.TokenGenerator;
import com.fsdcyr.sky.authorization.service.impl.DefaultExpireStrategy;
import com.fsdcyr.sky.authorization.service.impl.MD5TokenGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-23 10:12 上午
 */
@Configuration
@ConditionalOnBean(RedisTemplate.class)
@EnableConfigurationProperties({AuthenticationProperties.class})
public class AuthenticationAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ExpireStrategy.class)
    public ExpireStrategy defaultTokenExpireStrategy() {
        return new DefaultExpireStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(TokenGenerator.class)
    public TokenGenerator tokenGenerator() {
        return new MD5TokenGenerator();
    }

    @Bean
    public AuthenticationWebConfiguration webMvcConfiguration() {
        return new AuthenticationWebConfiguration();
    }

    @Bean
    public AuthContextHttpFilter authContextHttpFilter() {
        return new AuthContextHttpFilter();
    }

    @Bean
    public AuthenticationInitializer authenticationInitializer() {
        return new AuthenticationInitializer();
    }

}
