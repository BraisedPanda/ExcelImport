package com.braisedpanda.dooraccess.base.constant;

import com.braisedpanda.dooraccess.base.service.ResultCode;

/**
 * http网路请求状态 
 * @author JiC
 * @date 2019-07-08 9:10
 */
public enum  HttpStatusConstant implements ResultCode {

    OK(200, "OK"),

    BAD_REQUEST(400, "Bad Request"),

    NOT_FOUND(404, "Not Found"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    ;

    private final int value;

    private final String message;

    HttpStatusConstant(int status, String message) {
        this.value = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getStatus() {
        return value;
    }
}
