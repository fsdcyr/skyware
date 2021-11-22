package com.fsdcyr.sky.authorization.service.impl;

import com.fsdcyr.sky.authorization.JWTHelper;
import com.fsdcyr.sky.authorization.dto.TokenInfoDTO;
import com.fsdcyr.sky.authorization.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 4:48 下午
 */
@Slf4j
@Component
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String sign(TokenInfoDTO tokenInfoDTO) {
        int expire = 1000000;
        String token = JWTHelper.generateToken(tokenInfoDTO, expire);
        redisTemplate.opsForValue().set(token, tokenInfoDTO, expire, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public TokenInfoDTO getInfo(String token) {
        Object obj = redisTemplate.opsForValue().get(token);
        if (obj != null) {
            return (TokenInfoDTO) obj;
        }
        return null;
    }

    @Override
    public Boolean validate(String token) {
        return null;
    }

    @Override
    public void delete(String token) {
        redisTemplate.delete(token);
    }
}
