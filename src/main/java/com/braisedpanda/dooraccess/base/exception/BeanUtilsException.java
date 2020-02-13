package com.braisedpanda.dooraccess.base.exception;

import com.braisedpanda.dooraccess.base.constant.HttpStatusConstant;

/**
 * ben 异常 
 * @author JiC
 * @date 2019-07-08 17:53
 */
public class BeanUtilsException extends AbstractHttpException {

    public BeanUtilsException(Exception e) {
        super(HttpStatusConstant.NOT_FOUND, null, e);
    }

    public BeanUtilsException(String message) {
        super(HttpStatusConstant.NOT_FOUND, message, null);
    }

}
