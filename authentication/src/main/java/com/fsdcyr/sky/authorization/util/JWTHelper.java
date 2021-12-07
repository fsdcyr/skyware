package com.fsdcyr.sky.authorization.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.fsdcyr.sky.authorization.model.AuthenticationToken;
import com.fsdcyr.sky.authorization.model.JwtInfo;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Map;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 6:06 下午
 */
public final class JWTHelper {

    private static final byte[] KEY = "123".getBytes();

    private static final String id = "HS265";

    public static String generateToken(AuthenticationToken authenticationToken) {
        JwtInfo jwtInfo = new JwtInfo();
        BeanUtils.copyProperties(authenticationToken, jwtInfo);
        JWTSigner signer = JWTSignerUtil.hs256(KEY);
        Map<String, Object> payload = JSONUtil.toBean(JSONUtil.toJsonStr(authenticationToken), new TypeReference<Map<String, Object>>() {}, true);
        return JWT.create()
                .setJWTId(jwtInfo.generatorJwtId())
                .addPayloads(payload)
                .setExpiresAt(new Date(System.currentTimeMillis() + authenticationToken.getExpire() * 1000L))
                .setNotBefore(DateUtil.date())
                .sign(signer);
    }

    public static boolean validate(String token) {
        JWTSigner signer = JWTSignerUtil.hs256(KEY);
        return JWT.of(token).setSigner(signer).validate(0);

    }

    public static JwtInfo get(String token) {
        JWT jwt = JWT.of(token);
        return jwt.getPayloads().toBean(JwtInfo.class);
    }

    public static void main(String[] args) {
        AuthenticationToken authenticationTokenInfo = new AuthenticationToken();
        authenticationTokenInfo.setSource("pc");
        authenticationTokenInfo.setAppId(1);
        authenticationTokenInfo.setExpire(120L);
        authenticationTokenInfo.setUserId(123);
        String token = JWTHelper.generateToken(authenticationTokenInfo);
        System.out.println(token);
        System.out.println(JWTHelper.validate(token));
        JwtInfo jwtInfo = JWTHelper.get(token);
        System.out.println("---");
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJwYzoxMjMiLCJhcHBJZCI6MSwiZXhwaXJlIjoxMjAsInNvdXJjZSI6InBjIiwidXNlcklkIjoxMjMsImV4cCI6MTYzNzcxODM3MCwibmJmIjoxNjM3NzE4MjUwfQ.kxHvIyKtcWkXsi2VQM78XRLoVz9-5lS0d8cW0W-dYXY";
//        System.out.println(JWTHelper.validate(token));
    }
}
