package com.fsdcyr.sky.authorization.service;


import com.fsdcyr.sky.authorization.dto.TokenInfoDTO;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 4:46 下午
 */
public interface ITokenService {

    /**
     * 签名
     * @param tokenInfoDTO
     * @return
     */
    String sign(TokenInfoDTO tokenInfoDTO);

    /**
     * 从token中获取信息
     * @param token
     * @return
     */
    TokenInfoDTO getInfo(String token);

    /**
     * 校验签名
     * @param token
     * @return
     */
    Boolean validate(String token);

    /**
     * 删除token
     * @param token
     */
    void delete(String token);
}
