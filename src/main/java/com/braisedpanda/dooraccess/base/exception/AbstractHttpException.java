package com.braisedpanda.dooraccess.base.exception;

import com.braisedpanda.dooraccess.base.constant.HttpStatusConstant;

/**
 * 项目自定义异常 顶级抽象类
 * @author JiC
 * @date 2019-06-27 10:02
 */
public abstract class AbstractHttpException extends RuntimeException {

    private static final long serialVersionUID = -4547543145035538731L;

    protected HttpStatusConstant httpStatus;

    public AbstractHttpException(HttpStatusConstant httpStatus, String message, Throwable t){
        super(message, t);
        this.httpStatus = httpStatus;
    }

}
