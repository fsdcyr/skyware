package com.fsdcyr.sky.authorization.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 6:17 下午
 */
@Data
public class AuthenticationToken implements Serializable {

    /**
     * 来源
     */
    private String source;

    /**
     * 场景
     */
    private String scene;

    /**
     * 应用ID
     */
    private Integer appId;

    /**
     * 租户ID
     */
    private Integer tenantId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信 unionId
     */
    private String unionId;

    /**
     * 微信 openId
     */
    private String openId;

    /**
     * 过期时间
     */
    private Long expire;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 扩展map
     */
    private Map<String, Object> extendMap;
}
