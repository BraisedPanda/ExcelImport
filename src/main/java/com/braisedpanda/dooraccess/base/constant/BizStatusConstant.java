package com.braisedpanda.dooraccess.base.constant;

import com.braisedpanda.dooraccess.base.service.ResultCode;

/**
 * @author JiC123
 * @Description: 业务异常
 * @date 2019/9/3
 */
public enum BizStatusConstant implements ResultCode {

    FAILD(-1, "请求错误"),

    SUCCEED(10000, "请求成功"),
    ;

    private final int value;

    private final String message;

    BizStatusConstant(int status, String message) {
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
