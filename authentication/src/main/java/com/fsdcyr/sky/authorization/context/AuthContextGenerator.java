package com.fsdcyr.sky.authorization.context;

import com.fsdcyr.sky.authorization.model.AuthenticationToken;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-12-07 4:34 下午
 */
public interface AuthContextGenerator {

    /**
     * 根据token生成上下文
     * @param token
     * @return
     */
    AuthContext generate(AuthenticationToken token);
}
