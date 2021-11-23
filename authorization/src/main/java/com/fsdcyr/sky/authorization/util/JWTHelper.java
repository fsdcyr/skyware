package com.fsdcyr.sky.authorization.util;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.AlgorithmUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.fsdcyr.sky.authorization.dto.JWTInfoDTO;
import com.fsdcyr.sky.authorization.dto.TokenInfoDTO;
import org.springframework.beans.BeanUtils;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 6:06 下午
 */
public final class JWTHelper {

    private static final String seed = "123";

    private static final String id = "RS256";


    private static KeyPair keyPair;

    static {

        keyPair = KeyUtil.generateKeyPair(AlgorithmUtil.getAlgorithm(id), 1024, seed.getBytes());
    }

    public static String generateToken(TokenInfoDTO tokenInfoDTO, long expire) {
        JWTInfoDTO jwtInfo = new JWTInfoDTO();
        BeanUtils.copyProperties(tokenInfoDTO, jwtInfo);
        JWTSigner signer = JWTSignerUtil.createSigner(id, keyPair);
        Map<String, Object> payload = JSONUtil.toBean(JSONUtil.toJsonStr(tokenInfoDTO), new TypeReference<Map<String, Object>>() {}, true);
        String token = JWTUtil.createToken(payload, signer);
        return token;
    }

    public static void main(String[] args) {
        KeyPair keyPair = KeyUtil.generateKeyPair(AlgorithmUtil.getAlgorithm(id), 1024, seed.getBytes());
        JWTSigner signer = JWTSignerUtil.createSigner(id, keyPair.getPrivate().getEncoded());
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "小明");
        String token = JWTUtil.createToken(payload, signer);
        System.out.println(token);
    }
}
