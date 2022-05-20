package com.rockvine.jvm.loader;

import java.io.File;

/**
 * @author rocky
 * @date 2022-05-18 09:07
 * @description 异或加密工具类测试类
 */
public class XorEncryptTest {
    public static void main(String[] args) throws Exception {
        String dirPath = System.getProperty("user.dir");
        String basePath = dirPath + "/jvm/src/main/java/com/rockvine/loader";

        File src = new File(basePath + "/UserSrc.class");
        File des = new File(basePath + "/User.class");

        XorEncryptUtil.encrypt(src, des);
    }
}
