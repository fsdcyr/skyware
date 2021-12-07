package com.fsdcyr.sky.authorization.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.fsdcyr.sky.authorization.model.AuthenticationToken;
import com.fsdcyr.sky.authorization.service.TokenGenerator;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-26 6:03 下午
 */
public class MD5TokenGenerator implements TokenGenerator {

    /**
     * tenantId:appId:source:userId
     */
    private static final String FORMATTER = "%d:%d:%s:%d";

    @Override
    public String generate(AuthenticationToken authenticationToken) {
        String data = String.format(FORMATTER, authenticationToken.getTenantId(), authenticationToken.getAppId(), authenticationToken.getSource(), authenticationToken.getUserId());
        return MD5.create().digestHex(data).toUpperCase();
    }

    /**
     * MD5不支持验证
     * @param token
     * @return
     */
    @Override
    public boolean validate(String token) {
        return true;
    }

}
