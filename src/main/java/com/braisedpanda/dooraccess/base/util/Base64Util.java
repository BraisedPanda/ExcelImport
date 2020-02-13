package com.braisedpanda.dooraccess.base.util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * @Deprecated：
 * @Author: WangKang
 * @Date: 2019/11/26 17:13
 */
public class Base64Util {
    // 加密
    public static String encode(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new BASE64Encoder().encode(b);
            //据RFC 822规定，每76个字符，还需要加上一个回车换行去掉换行符
            s = s.replaceAll("[\\s*\t\n\r]", "");
        }
        return s;
    }

    // 解密
    public static String decode(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String encode(byte[] bytes){
        return Base64.encodeBase64String(bytes);
    }

    public static void main(String[] args){
        String s = "0";
        System.out.println(encode(s));
    }
}
