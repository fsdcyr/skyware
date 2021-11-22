package com.fsdcyr.sky.authorization;


import java.io.Serializable;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 5:18 下午
 */
public abstract class UserContext implements Serializable {

    private Integer userId;

    private String token;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
