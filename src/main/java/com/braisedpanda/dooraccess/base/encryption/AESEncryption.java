package com.braisedpanda.dooraccess.base.encryption;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**@ClassName: AesEncryption
 * @Description: aes 加密
 * @author JiC
 * @date 2019/2/20
 */
public final class AESEncryption {

    private static final Logger logger = LoggerFactory.getLogger(AESEncryption.class);
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法

    /**
     * @Description: 加密
     * @author JiC
     * @date 2019/2/20
    */
    public static String encrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);
            return Base64.encodeBase64String(result);//通过Base64转码返回
        } catch (Exception e) {
            logger.error("加密失败,错误信息是", e);
        }
        return null;
    }

    /**
     * @Description: 解密
     * @author JiC
     * @date 2019/2/20
    */
    public static String decrypt(String content, String password) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));
            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));
            return new String(result, "utf-8");
        } catch (Exception e) {
            logger.error("解密异常,错误信息是：", e);
        }
        return null;
    }

    private static SecretKeySpec getSecretKey(String password) throws Exception {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg =  KeyGenerator.getInstance(KEY_ALGORITHM);
        //AES 要求密钥长度为 128
        kg.init(128, new SecureRandom(password.getBytes()));
        //生成一个密钥
        SecretKey secretKey = kg.generateKey();
        // 转换为AES专用密钥
        return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
    }

    private AESEncryption() { throw new IllegalAccessError();}

    public static  void main(String[] args){
        String content = "{" +
                "\"customercardcode\":1810191015853" +
                "}";
        String password = "123456789";
        String body = AESEncryption.encrypt(content,password);
        System.out.println(body);
    }
}
