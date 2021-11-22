package com.fsdcyr.sky.authorization.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 4:42 下午
 */
@Data
public class JWTInfoDTO implements Serializable {
    /**
     * 通过 generatorJwtId 复制
     */
    private String jwtId;

    private String source;

    private Integer companyId;

    private Integer userId;

    private String phone;

    private String openId;

    private String unionId;

    private String nickName;

    private String wxId;

    private String sessionID;

    private Map<String, Object> extendMap = new HashMap<>();

    private Boolean success;

    public void generatorJwtId() {
        jwtId = getSource() + ":" + getUserId();
    }
}
