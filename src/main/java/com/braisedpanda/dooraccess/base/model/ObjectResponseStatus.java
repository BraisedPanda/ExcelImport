package com.braisedpanda.dooraccess.base.model;

import com.braisedpanda.dooraccess.base.constant.BizStatusConstant;
import com.braisedpanda.dooraccess.base.service.ResultCode;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 返回实体对象 
 * @author JiC
 * @date 2019-06-05 15:30
 */
@Data
@ToString(callSuper = true)
@Accessors(chain = true)
public class ObjectResponseStatus<T> extends ResponseStatus {

    private T data;

    private Boolean rel;

    public ObjectResponseStatus() {}

    public ObjectResponseStatus(ResultCode resultCode) {
        super(resultCode);
    }

    /**
     * @Description: 请求成功返回对象值
     * @author JiC
     * @date 2019/7/4
    */
    public static <T> ObjectResponseStatus succeed(T t) {
        return ObjectResponseStatus.succeed(t, true);
    }

    /**
     * @Description: 请求成功返回对象值
     * @author JiC
     * @date 2019/7/4
     */
    public static   ObjectResponseStatus faild(String msg) {
        return ObjectResponseStatus.faild(msg,false);
    }

    /**
     * @Description: 操作是否成功
     * @author JiC
     * @date 2019/7/4
    */
    public static ObjectResponseStatus rel(boolean rel) {
        return ObjectResponseStatus.succeed(null, rel);
    }

    /**
     * @Description: 请求成功 返回对象和操作状态
     * @author JiC
     * @date 2019/7/4
    */
    public static <T> ObjectResponseStatus succeed(T t, boolean rel) {
        return new ObjectResponseStatus(BizStatusConstant.SUCCEED).setData(t).setRel(rel);
    }

    /**
     * @Description: 请求失败 返回对象和操作状态
     * @author JiC
     * @date 2019/7/4
     */
    public static <T> ObjectResponseStatus faild(String msg, boolean rel) {
        ObjectResponseStatus responseStatus = new ObjectResponseStatus(BizStatusConstant.FAILD);
        responseStatus.setData(null);
        responseStatus.setRel(rel);
        responseStatus.setMessage(msg);
        return responseStatus;
    }
}
