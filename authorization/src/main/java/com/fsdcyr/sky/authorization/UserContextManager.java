package com.fsdcyr.sky.authorization;


import com.fsdcyr.sky.authorization.constants.GlobalConstant;
import com.fsdcyr.sky.authorization.dto.TokenInfoDTO;
import com.fsdcyr.sky.authorization.service.ITokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;


/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 5:17 下午
 */
@Slf4j
@Component
public class UserContextManager {

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private RedisTemplate redisTemplate;

    public void save(UserContext userContext) {
        TokenInfoDTO tokenInfoDTO = new TokenInfoDTO();
        String token = tokenService.sign(tokenInfoDTO);
        String contextKey = getContextKey(userContext.getUserId(), token);
        long expires = getExpires();
        redisTemplate.opsForValue().set(contextKey, userContext, expires, TimeUnit.SECONDS);
        userContext.setToken(token);
        UserContextHolder.save(userContext);
    }

    public UserContext get(HttpServletRequest request) {
        String userIdHeader = request.getHeader(GlobalConstant.X_USER_ID);
        String token = request.getHeader(GlobalConstant.X_USER_TOKEN);
        Integer userId = StringUtils.isEmpty(userIdHeader) ? null : Integer.valueOf(userIdHeader);
        if (ObjectUtils.isEmpty(userId) || ObjectUtils.isEmpty(token)) {
            // 用户未登录
            return null;
        }
        UserContext userContext = getFromCache(userId, token);
        if (userContext == null) {
            return null;
        }
        return userContext;
    }

    /**
     * 清除缓存
     * @param userContext
     */
    public void remove(UserContext userContext) {
        if (userContext == null) {
            return;
        }
        remove(userContext.getUserId(), userContext.getToken());
    }


    private String getContextKey(Integer userId, String token) {
        return userId + ":" + token;
    }

    private void refreshExpire(Integer userId, String token) {
        String contextKey = getContextKey(userId, token);
        long expire = getExpires();
        redisTemplate.expire(contextKey, expire, TimeUnit.SECONDS);
    }

    private void remove(Integer userId, String token) {
        try {
            String contextKey = getContextKey(userId, token);
            redisTemplate.delete(contextKey);
            tokenService.delete(token);
        } catch (Exception e) {
            log.warn("", e);
        }
    }

    private long getExpires() {
        return 1000000;
    }

    private UserContext getFromCache(Integer userId, String token) {
        String contextKey = getContextKey(userId, token);
        try {
            return (UserContext) redisTemplate.opsForValue().get(contextKey);
        } catch (Exception e) {
            remove(userId, token);
            log.warn("", e);
        }
        return null;
    }


}
