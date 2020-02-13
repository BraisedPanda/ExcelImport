package com.braisedpanda.dooraccess.base.util;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Deprecated：
 * @Author: WangKang
 * @Date: 2019/11/26 17:44
 */
public class ConvertUtil {
    /**
     * 字符串转化成为16进制字符串
     * @param s
     * @return
     */
    public static String stringToHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }
    /**
     * 16进制转换成为string类型字符串
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }


    /**
     * @功能: 10进制串转为BCD码
     * @参数: 10进制串
     * @结果: BCD码
     */
    public static byte[] strToBcd(String asc) {
        int len = asc.length();
        int mod = len % 2;
        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }
        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }
        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;
        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }

    /**
     * @Deprecated 表读数转8位长度十六进制字符串
     */
    public static String decim8ToHex(int num){
        return fmtToString(Integer.toHexString(num),8);
    }

    /**
     * @Deprecated 上报周期转4位长度十六进制字符串
     */
    public static String decim4ToHex(int num){
        return fmtToString(Integer.toHexString(num),4);
    }

    /**
     * @param: [content]
     * @return: int
     * @description: 十六进制转十进制
     */
    public static int hexToInt(String content){
        int number=0;
        String [] HighLetter = {"a","b","c","d","e","f"};
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0;i <= 9;i++){
            map.put(i+"",i);
        }
        for(int j= 10;j<HighLetter.length+10;j++){
            map.put(HighLetter[j-10],j);
        }
        String[]str = new String[content.length()];
        for(int i = 0; i < str.length; i++){
            str[i] = content.substring(i,i+1);
        }
        for(int i = 0; i < str.length; i++){
            number += map.get(str[i])*Math.pow(16,str.length-1-i);
        }
        return number;
    }


    /**
     * @Deprecated num转十六进制，然后取后两位，再转成10进制返回
     */
    public static int cutLast2Num(Integer num){
        String newnum = Integer.toHexString(num);
        return hexToInt(newnum.substring(newnum.length()-2));
    }

    /**
     * @Deprecated 水表号求各位和
     */
    public static int imeiToCount(String imei){
        int count = 0;
        if(imei!=null&&imei.length()==16){
            for(int i = 0;i < 16;i += 2){
                String newstr = imei.substring(i,i+2);
                count += hexToInt(newstr);
            }
        }
        return count;
    }

    /**
     * @Deprecated 表读数求各位和
     */
    public static int waterReadCount(int readValue){
        String value = decim8ToHex(readValue);
        int count = 0;
        for(int i = 0;i < 8;i += 2){
            String newstr = value.substring(i,i+2);
            count += hexToInt(newstr);
        }
        return count;
    }

    /**
     * @Deprecated 上报周期各位求和
     */
    public static int cycleCount(int readValue){
        String value = decim4ToHex(readValue);
        int count = 0;
        for(int i = 0;i < 4;i += 2){
            String newstr = value.substring(i,i+2);
            count += hexToInt(newstr);
        }
        return count;
    }

    /**
     * @Deprecated 格式化十六进制字符串为num位，不足num位前面补'0',超过num位取末尾num位
     */
    public static String fmtToString(String string,int num){
        if(StringUtils.hasLength(string)){
            if(string.length()<num){
                // 计算实际需要补0长度
                int formatLength = num-string.length();
                // 补0操作
                return String.format("%0" + formatLength + "d", 0)   + string;
            }else{
                return string.substring(string.length()-num);
            }
        }else{
            return string;
        }
    }

    public static void main(String[] args) {
        System.out.println(fmtToString("5c",8));
    }
}
