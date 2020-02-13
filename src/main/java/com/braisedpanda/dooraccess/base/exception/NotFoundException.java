package com.braisedpanda.dooraccess.base.exception;

import com.braisedpanda.dooraccess.base.constant.HttpStatusConstant;

/**
 * @Description: 数据未找到异常
 * @author JiC
 * @date 2019/6/27
*/
public class NotFoundException extends AbstractHttpException {

    public NotFoundException(String message, Object ...obj) {
      super(HttpStatusConstant.NOT_FOUND, String.format(message, obj), null);
    }
}
