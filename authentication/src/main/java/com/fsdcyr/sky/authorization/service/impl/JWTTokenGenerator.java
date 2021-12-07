package com.fsdcyr.sky.authorization.service.impl;

import com.fsdcyr.sky.authorization.model.AuthenticationToken;
import com.fsdcyr.sky.authorization.service.TokenGenerator;
import com.fsdcyr.sky.authorization.util.JWTHelper;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-26 5:57 下午
 */
public class JWTTokenGenerator implements TokenGenerator {

    @Override
    public String generate(AuthenticationToken authenticationToken) {
        return JWTHelper.generateToken(authenticationToken);
    }

    @Override
    public boolean validate(String token) {
        return JWTHelper.validate(token);
    }

}
