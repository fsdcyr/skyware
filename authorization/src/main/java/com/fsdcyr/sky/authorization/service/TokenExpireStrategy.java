package com.fsdcyr.sky.authorization.service;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-23 10:09 上午
 */
public interface TokenExpireStrategy {

    /**
     * 根据来源设置token过期时间
     * @param source
     * @return seconds
     */
    long getExpire(String source);
}
