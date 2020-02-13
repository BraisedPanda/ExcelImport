package com.braisedpanda.dooraccess.base.model;

import com.braisedpanda.dooraccess.base.constant.BizStatusConstant;
import com.braisedpanda.dooraccess.base.service.Response;
import com.braisedpanda.dooraccess.base.service.ResultCode;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * 响应状态状态
 * @author JiC
 * @date 2019-06-05 15:09
 */
@Data
@Accessors(chain = true)
public class ResponseStatus implements Response {

    private static final long serialVersionUID = -2084916197659988086L;

    private Integer status;

    private String message;

    private String tid = TraceContext.traceId();

    public ResponseStatus() {}

    public ResponseStatus(ResultCode resultCode) {
        this.status = resultCode.getStatus();
        this.message = resultCode.getMessage();
    }

    /**
     * @Description: 请求成功
     * @author JiC
     * @date 2019/7/4
    */
    public static ResponseStatus succeed() {
        return new ResponseStatus(BizStatusConstant.SUCCEED);
    }

    public boolean isSucceed() {
        return this.status == BizStatusConstant.SUCCEED.getStatus().intValue();
    }

    /**
     * @Deprecated 请求错误
     * @param message
     * @return: com.braisedpanda.dooraccess.base.model.ResponseStatus
     * @Author: JiC
     * @date: 2019/9/3
     */
    public static ResponseStatus faild(String message) {
        ResponseStatus responseStatus = new ResponseStatus(BizStatusConstant.FAILD);
        responseStatus.setMessage(message);
        return responseStatus;
    }

    public static ResponseStatus faild() {
        return new ResponseStatus(BizStatusConstant.FAILD);
    }

}
