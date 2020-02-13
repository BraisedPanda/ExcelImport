package com.braisedpanda.dooraccess.base.exception;

import com.braisedpanda.dooraccess.base.constant.HttpStatusConstant;

/**
 * 参数转换异常
 * @author JiC
 * @date 2019-07-10 16:10
 */
public class ParamConvertException extends AbstractHttpException {

    private static final long serialVersionUID = 639159231531226703L;

    public ParamConvertException(String message) {
        super(HttpStatusConstant.INTERNAL_SERVER_ERROR, message, null);
    }

    public ParamConvertException(String message, Throwable t) {
        super(HttpStatusConstant.INTERNAL_SERVER_ERROR, message, t);
    }
}
