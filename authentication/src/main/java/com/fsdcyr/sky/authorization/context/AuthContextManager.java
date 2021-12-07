package com.fsdcyr.sky.authorization.context;


import cn.hutool.core.util.BooleanUtil;
import com.fsdcyr.sky.authorization.constant.Constants;
import com.fsdcyr.sky.authorization.model.AuthenticationToken;
import com.fsdcyr.sky.authorization.service.ExpireStrategy;
import com.fsdcyr.sky.authorization.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 5:17 下午
 */
@Slf4j
@RequiredArgsConstructor
public class AuthContextManager {

    private TokenService tokenService;

    private ExpireStrategy expireStrategy;

    private AuthContextGenerator authContextGenerator;

    /**
     * TODO
     * @param authContext
     */
    public void save(AuthContext authContext) {
        AuthenticationToken authenticationTokenInfo = new AuthenticationToken();
        String token = tokenService.sign(authenticationTokenInfo);
        authContext.setToken(token);
        AuthThreadContext.bind(authContext);
    }

    public AuthContext getAuthContext(HttpServletRequest request) {
        String userIdHeader = request.getHeader(Constants.X_USER_ID);
        String token = request.getHeader(Constants.X_USER_TOKEN);
        Integer userId = StringUtils.isEmpty(userIdHeader) ? null : Integer.valueOf(userIdHeader);
        // token or userId empty
        if (ObjectUtils.isEmpty(userId) || ObjectUtils.isEmpty(token)) {
            return null;
        }
        // validate token
        Boolean valid = tokenService.validate(token);
        if (BooleanUtil.isFalse(valid)) {
            return null;
        }
        // generate userContext
        AuthenticationToken authenticationToken = tokenService.getInfo(token);
        if (authenticationToken == null) {
            return null;
        }
        AuthContext authContext = authContextGenerator.generate(authenticationToken);
        if (authContext == null) {
            return null;
        }
        return authContext;
    }

    public void remove() {
        remove(AuthThreadContext.getAuthContext());
    }

    /**
     * 清除缓存
     * @param authContext
     */
    private void remove(AuthContext authContext) {
        if (authContext == null) {
            return;
        }
        remove(authContext.getToken());
    }
    private void remove(String token) {
        try {
            tokenService.delete(token);
        } catch (Exception e) {
            log.warn("", e);
        }
    }

}
