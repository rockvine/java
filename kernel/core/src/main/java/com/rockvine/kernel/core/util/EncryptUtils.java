package com.rockvine.kernel.core.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

/**
 * @author rocky
 * @date 2022-02-13 13:52
 * @description 加密工具类
 */
public class EncryptUtils {
    private static final Logger logger = LoggerFactory.getLogger(EncryptUtils.class);

    /**
     * AES加密
     *
     * @param data 明文
     * @param key  密钥
     * @return 密文
     */
    public static String encryptByAES(String data, String key) {
        if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
            return null;
        }

        // 构建AES密钥
        SecretKeySpec secretKeySpec = generatorAESKey(key, "AES");
        Objects.requireNonNull(secretKeySpec, "secretKeySpec can not be null");

        // 定义填充向量
        IvParameterSpec ivParameterSpec = new IvParameterSpec("0000000000000000".getBytes());

        try {
            // 创建密码器，并设置加密算法/加密模式/填充算法
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // 以加密的方式用密钥和填充向量初始化密码器
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            // 执行加密，并获得byte[]格式的加密结果。String转byte[]的编码格式为UTF-8
            byte[] encrypt = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // 对byte[]格式结果做Base64转码得到密文结果。
            return Base64.encodeBase64URLSafeString(encrypt);
            // return new String(new BASE64Encoder().encode(encrypt));
        } catch (Exception e) {
            logger.error("加密失败, data={}, key={} ", data, key, e);
            return null;
        }
    }

    /**
     * AES解密
     *
     * @param encryptData 密文
     * @param key         密钥
     * @return 明文
     */
    public static String decryptByAES(String encryptData, String key) {
        if (StringUtils.isBlank(encryptData) || StringUtils.isBlank(key)) {
            return null;
        }

        // 对（URL安全的）密文做Base64转码（decode解码）得到byte[]格式加密结果
        byte[] encryptDataBytes = Base64.decodeBase64(encryptData);

        // 构建AES密钥
        SecretKeySpec secretKeySpec = generatorAESKey(key, "AES");
        Objects.requireNonNull(secretKeySpec, "secretKeySpec can not be null");

        // 定义填充向量
        IvParameterSpec ivParameterSpec = new IvParameterSpec("0000000000000000".getBytes());

        try {
            // 创建密码器，并设置解密算法/解密模式/填充算法
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            // 以解密的方式用密钥和填充向量初始化密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            // 执行解密，并获得byte[]格式的解密结果。
            byte[] decrypt = cipher.doFinal(encryptDataBytes);

            // byte[]转String。编码格式是UTF-8
            return new String(decrypt, StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("解密失败, encryptData={}, key={}", encryptData, key, e);
            return null;
        }
    }

    /*
     * SecureRandom实例化的方式默认为：
     * SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
     * 即：keyGenerator.init(new SecureRandom(key.getBytes()));
     * 会导致在windows上测试正常，但在部分linux操作系统下却出现加密解密异常，如会发现加密的结果值一直会变
     *
     * 所以需要改成：
     * SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
     * secureRandom.setSeed(key.getBytes());
     * keyGenerator.init(128, secureRandom);
     * ...
     */
    private static SecretKeySpec generatorAESKey(String key, String algorithm) {
        try {
            // 随机数生成器，指定SHA1PRNG算法
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            // 给定随机种子
            secureRandom.setSeed(key.getBytes());

            // 构造密钥生成器，指定为AES算法，不区分大小
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            // 指定密钥长度为128位
            keyGenerator.init(128, secureRandom);

            // 生成密钥
            SecretKey secretKey = keyGenerator.generateKey();

            // 构建AES密钥
            return new SecretKeySpec(secretKey.getEncoded(), algorithm);
        } catch (NoSuchAlgorithmException e) {
            logger.error("生成秘钥失败, key={}, algorithm={}", key, algorithm);
            return null;
        }
    }

    /**
     * 签名
     *
     * @param data 数据
     * @param key  密钥
     * @return 签名
     */
    public static String sign(String data, String key) {
        // 构建AES密钥
        SecretKeySpec secretKeySpec = generatorAESKey(key, "HmacSHA1");
        Objects.requireNonNull(secretKeySpec, "secretKeySpec can not be null");

        try {
            // 指定签名算法
            Mac mac = Mac.getInstance("HmacSHA1");

            // 设置签名密钥
            mac.init(secretKeySpec);

            // 执行签名，并获得byte[]格式签名结果。
            byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // 对byte[]格式签名结果做（URL安全的）Base64转码（encode编码）得到最终签名
            return Base64.encodeBase64URLSafeString(rawHmac);
        } catch (Exception e) {
            logger.error("签名失败, data={}, key={}", data, key, e);
            return null;
        }
    }

    /**
     * 签名校验
     *
     * @param data      数据
     * @param key       密钥
     * @param signature 需校验的签名
     * @return 校验结果
     */
    public static boolean verifySign(String data, String key, String signature) {
        if (StringUtils.isBlank(data) || StringUtils.isBlank(key) || StringUtils.isBlank(signature)) {
            return false;
        }
        return signature.equals(sign(data, key));
    }

    /**
     * MD5加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encryptByMD5(String data) {
        if (StringUtils.isBlank(data)) {
            return null;
        }

        // 获取随机盐
        Random random = new Random();
        StringBuilder salt = new StringBuilder(16);
        salt.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = salt.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                salt.append("0");
            }
        }

        // 加密
        String encryptData = md5(data + salt);
        if (StringUtils.isBlank(encryptData)) {
            return null;
        }

        char[] chars = new char[48];
        for (int i = 0; i < 48; i += 3) {
            chars[i] = encryptData.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            chars[i + 1] = c;
            chars[i + 2] = encryptData.charAt(i / 3 * 2 + 1);
        }
        return new String(chars);
    }

    /**
     * MD5加密校验
     *
     * @param data        明文
     * @param encryptData 密文
     * @return 校验结果
     */
    public static boolean verifyMD5(String data, String encryptData) {
        if (StringUtils.isBlank(data) || StringUtils.isBlank(encryptData)) {
            return false;
        }

        char[] ch1 = new char[32];
        char[] ch2 = new char[16];
        try {
            for (int i = 0; i < 48; i += 3) {
                ch1[i / 3 * 2] = encryptData.charAt(i);
                ch1[i / 3 * 2 + 1] = encryptData.charAt(i + 2);
                ch2[i / 3] = encryptData.charAt(i + 1);
            }
            String salt = new String(ch2);
            return md5(data + salt).equals(new String(ch1));
        } catch (Exception e) {
            logger.warn("校验失败, data={}, encryptData={}", data, encryptData, e);
            return false;
        }
    }

    private static String md5(String data) {
        String encStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(data.getBytes());
            encStr = bytesToHex(md5.digest());
            // encStr = bytesToHex2(md5.digest());
            // encStr = bytesToHex3(md5.digest());
        } catch (Exception e) {
            logger.error("MD5加密失败, data={}", data, e);
        }
        return encStr;
    }

    /*
     * 把密文转换成十六进制的字符串形式
     */
    private static String bytesToHex(byte[] bytes) {
        // return new String(new Hex().encode(bytes));
        return Hex.encodeHexString(bytes);
    }

    private static String bytesToHex2(byte[] bytes) {
        char[] HEX_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};

        char[] chars = new char[32];
        for (int i = 0; i < chars.length; i = i + 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
            chars[i + 1] = HEX_CHARS[b & 0xf];
        }
        return new String(chars);
    }

    private static String bytesToHex3(byte[] bytes) {
        StringBuilder hexValue = new StringBuilder();
        String tmp;
        for (byte b : bytes) {
            tmp = (Integer.toHexString(b & 0xFF));
            if (tmp.length() == 1) {
                hexValue.append("0");
            }
            hexValue.append(tmp);
        }
        return hexValue.toString();
    }

    /**
     * 自定义加密
     *
     * @param data 明文
     * @param key  密钥
     * @return 密文
     */
    public static String encrypt(String data, String key) {
        if (StringUtils.isBlank(data)) {
            return null;
        }
        return StringUtils.isNotBlank(key) ? encrypt(union(data, key)) : encrypt(data);
    }

    /**
     * 自定义加密
     * 先将data进行一次MD5加密，加密后再取加密后的字符串的第1、3、5个字符追加到加密串，再拿这个加密串进行加密
     *
     * @param data 明文
     * @return 密文
     */
    public static String encrypt(String data) {
        if (StringUtils.isBlank(data)) {
            return null;
        }

        String encryptData = md5(data);
        if (StringUtils.isNotBlank(encryptData)) {
            encryptData = encryptData + encryptData.charAt(0) + encryptData.charAt(2) + encryptData.charAt(4);
            encryptData = md5(encryptData);
        }
        return encryptData;
    }

    /*
     * 将要加密的字符串与key联合为一个字符串
     */
    private static String union(String str, String key) {
        int strLen = str.length();
        int keyLen = key.length();
        Character[] s = new Character[strLen + keyLen];

        boolean flag = true;
        int strIdx = 0;
        int keyIdx = 0;
        for (int i = 0; i < s.length; i++) {
            if (flag) {
                if (strIdx < strLen) {
                    s[i] = str.charAt(strIdx);
                    strIdx++;
                }
                if (keyIdx < keyLen) {
                    flag = false;
                }

            } else {
                if (keyIdx < keyLen) {
                    s[i] = key.charAt(keyIdx);
                    keyIdx++;
                }
                if (strIdx < strLen) {
                    flag = true;
                }

            }
        }
        return StringUtils.join(s);
    }
}
