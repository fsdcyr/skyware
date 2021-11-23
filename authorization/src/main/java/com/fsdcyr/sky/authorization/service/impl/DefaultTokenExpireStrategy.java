package com.fsdcyr.sky.authorization.service.impl;

import com.fsdcyr.sky.authorization.service.TokenExpireStrategy;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-23 10:11 上午
 */
public class DefaultTokenExpireStrategy implements TokenExpireStrategy {

    private static final long DEFAULT_SECONDS = 24 * 60 * 60;

    @Override
    public long getExpire(String source) {
        return DEFAULT_SECONDS;
    }

}
