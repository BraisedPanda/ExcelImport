package com.braisedpanda.dooraccess.base.exception;

import com.braisedpanda.dooraccess.base.constant.HttpStatusConstant;

/**
 * @Description 业务异常
 * @author JiC
 * @date 2019-09-05 9:10
 */
public class BizException extends AbstractHttpException {

    public BizException(String message, Throwable t) {
        super(HttpStatusConstant.INTERNAL_SERVER_ERROR, message, t);
    }
}
