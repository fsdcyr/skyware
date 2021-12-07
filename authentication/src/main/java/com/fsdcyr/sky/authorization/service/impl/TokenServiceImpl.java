package com.fsdcyr.sky.authorization.service.impl;

import com.fsdcyr.sky.authorization.model.AuthenticationToken;
import com.fsdcyr.sky.authorization.service.TokenGenerator;
import com.fsdcyr.sky.authorization.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 4:48 下午
 */
@Slf4j
public class TokenServiceImpl implements TokenService {

    private RedisTemplate redisTemplate;

    private TokenGenerator tokenGenerator;

    @Value("${authentication.prefix:authentication}")
    private String tokenPrefix;

    public TokenServiceImpl(RedisTemplate redisTemplate, TokenGenerator tokenGenerator) {
        this.redisTemplate = redisTemplate;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public String sign(AuthenticationToken authenticationTokenInfo) {
        String token = tokenGenerator.generate(authenticationTokenInfo);
        String cacheKey = getCacheKey(token);
        redisTemplate.opsForValue().set(cacheKey, authenticationTokenInfo, authenticationTokenInfo.getExpire(), TimeUnit.SECONDS);
        return token;
    }

    @Override
    public AuthenticationToken getInfo(String token) {
        String cacheKey = getCacheKey(token);
        Object obj = redisTemplate.opsForValue().get(cacheKey);
        if (obj != null) {
            return (AuthenticationToken) obj;
        }
        return null;
    }

    @Override
    public Boolean validate(String token) {
        boolean valid = tokenGenerator.validate(token);
        if (valid) {
            String cacheKey = getCacheKey(token);
            redisTemplate.hasKey(cacheKey);
        }
        return null;
    }

    @Override
    public void refresh(String token) {

    }

    @Override
    public void delete(String token) {
        redisTemplate.delete(token);
    }

    private String getCacheKey(String token) {
        return tokenPrefix + ":" + token;
    }

}
