package com.rockvine.kernel.core.utils;

import com.rockvine.kernel.core.BaseTest;

/**
 * @author rocky
 * @date 2022-05-17 22:04
 * @description 加密工具类测试类
 */
public class EncryptUtilTest extends BaseTest {

    public static void main(String[] args) {
        testEncryptByAES();
    }

    private static void testEncryptByAES() {
        String key = "admin123456";
        String str = "hello world";

        String encryptData = EncryptUtil.encryptByAES(str, key);
        System.out.println(encryptData);

        String data = EncryptUtil.decryptByAES(encryptData, key);
        System.out.println(data);
    }
}
