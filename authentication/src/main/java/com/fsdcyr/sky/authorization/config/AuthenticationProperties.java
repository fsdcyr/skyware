package com.fsdcyr.sky.authorization.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-12-07 3:19 下午
 */
@Data
@ConfigurationProperties(prefix = "authentication")
public class AuthenticationProperties {

    /**
     * 不进行拦截的路径，多个用逗号分隔
     */
    private String excludePathPattern = "/login";

    /**
     * token缓存前缀
     */
    private String prefix;
}
