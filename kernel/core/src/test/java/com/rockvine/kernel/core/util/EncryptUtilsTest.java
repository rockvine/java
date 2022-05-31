package com.rockvine.kernel.core.util;

import com.rockvine.kernel.core.BaseTest;

/**
 * @author rocky
 * @date 2022-05-17 22:04
 * @description 加密工具类测试类
 */
public class EncryptUtilsTest extends BaseTest {

    public static void main(String[] args) {
        testEncryptByAES();
    }

    private static void testEncryptByAES() {
        String key = "admin123456";
        String str = "hello world";

        String encryptData = EncryptUtils.encryptByAES(str, key);
        System.out.println(encryptData);

        String data = EncryptUtils.decryptByAES(encryptData, key);
        System.out.println(data);
    }
}
