package com.fsdcyr.sky.authorization.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 6:17 下午
 */
@Data
public class TokenInfoDTO implements Serializable {

    private String source;

    private Integer appId;

    private Integer tenantId;

    private Integer userId;

    private String nickName;

    private String phone;

    private String unionId;

    private String openId;

    private String wxId;

    private Long expire;

    private Long createdAt;

    private Map<String, Object> extendMap;
}
