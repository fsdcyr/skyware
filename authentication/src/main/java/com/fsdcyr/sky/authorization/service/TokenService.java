package com.fsdcyr.sky.authorization.service;


import com.fsdcyr.sky.authorization.model.AuthenticationToken;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 4:46 下午
 */
public interface TokenService {

    /**
     * 签名
     * @param authenticationToken
     * @return
     */
    String sign(AuthenticationToken authenticationToken);

    /**
     * 从token中获取信息
     * @param token
     * @return
     */
    AuthenticationToken getInfo(String token);

    /**
     * 校验签名
     * @param token
     * @return
     */
    Boolean validate(String token);

    /**
     * 刷新token
     * @param token
     */
    void refresh(String token);

    /**
     * 删除token
     * @param token
     */
    void delete(String token);
}
