package com.fsdcyr.sky.common.exception;

/**
 * @author lvheng chen
 * @version 1.0
 * @date 2021-11-22 7:07 下午
 */
public class SkywareException extends RuntimeException {

    private int code = -1;

    public SkywareException(String errorMessage) {
        super(errorMessage);
    }

    public SkywareException(int code, String errorMessage) {
        super(errorMessage);
        this.code = code;
    }


    public int getCode() {
        return code;
    }

}
