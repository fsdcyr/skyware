package com.fsdcyr.sky.authorization.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 4:42 下午
 */
@Data
public class JwtInfo implements Serializable {

    /**
     * 避免重放攻击-暂不支持
     */
    private String jwtId;

    private String source;

    private Integer userId;

    private Integer appId;

    private Integer tenantId;

    private String unionId;

    private String openId;

    private String phone;

    private String nickName;

    private String wxId;

    private String sessionId;

    private Map<String, Object> extendMap;

    public String generatorJwtId() {
        jwtId = getSource() + ":" + getUserId();
        return jwtId;
    }

}
