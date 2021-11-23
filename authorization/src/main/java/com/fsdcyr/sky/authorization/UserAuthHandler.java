package com.fsdcyr.sky.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 5:24 下午
 */
@Component
public class UserAuthHandler implements AuthHandler {

    @Autowired
    private UserContextManager userContextManager;

    @Override
    public UserContext getLoginUser(HttpServletRequest request) {
        return userContextManager.get(request);

    }

    @Override
    public boolean validate(HttpServletRequest request) {
        return true;
    }


}
