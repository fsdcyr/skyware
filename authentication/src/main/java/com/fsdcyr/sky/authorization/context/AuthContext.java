package com.fsdcyr.sky.authorization.context;


import lombok.Data;

import java.io.Serializable;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-18 5:18 下午
 */
@Data
public class AuthContext implements Serializable {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 散列摘要
     */
    private String token;

}
