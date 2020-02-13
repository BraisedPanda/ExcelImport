package com.braisedpanda.dooraccess.base.util;

import com.braisedpanda.dooraccess.base.constant.CommonConstants;
import com.braisedpanda.dooraccess.base.exception.BizException;
import com.braisedpanda.dooraccess.base.model.JwtUserModel;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description 公共实现类
 * @author JiC
 * @date 2019-09-05 8:56
 */
public final class Common {

    /**
     * @Deprecated 读取数据流
     * @param in
     * @param charset
     * @return: java.lang.String
     * @Author: JiC
     * @date: 2019/9/5
     */
    public static String readInputStream(InputStream in, String charset) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            return outputStream.toString(charset);
        } catch (IOException ex) {
            throw new BizException("读取数据流异常：", ex);
        }
    }
    
    /**
     * @Deprecated 获取jwt
     * @Author: JiC
     * @date: 2020/1/4
     */
    public static JwtUserModel requestJwt(HttpServletRequest request) {
        return (JwtUserModel) request.getAttribute(CommonConstants.JWT_USER_KEY);
    }
}
