package com.fsdcyr.sky.common.model;

import lombok.Data;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-22 7:07 下午
 */
@Data
public class SkywareResponse<T> {

    private int code;

    private String message;

    private T data;

}
