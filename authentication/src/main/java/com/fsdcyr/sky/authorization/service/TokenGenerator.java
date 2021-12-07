package com.fsdcyr.sky.authorization.service;

import com.fsdcyr.sky.authorization.model.AuthenticationToken;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-26 5:55 下午
 */
public interface TokenGenerator {

    /**
     * 生成token
     * @param authenticationToken
     * @return
     */
    String generate(AuthenticationToken authenticationToken);

    /**
     * 校验token
     * @param token
     * @return
     */
    boolean validate(String token);

}
