package com.fsdcyr.sky.authorization.service.impl;

import com.fsdcyr.sky.authorization.service.ExpireStrategy;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-23 10:11 上午
 */
public class DefaultExpireStrategy implements ExpireStrategy {

    /**
     * 24小时
     */
    private static final long DEFAULT_SECONDS = 24 * 60 * 60;

    @Override
    public long getExpire(String source) {
        return DEFAULT_SECONDS;
    }

}
