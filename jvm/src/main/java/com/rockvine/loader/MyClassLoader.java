package com.rockvine.loader;

import java.io.File;

/**
 * @author rocky
 * @date 2022-05-18 23:15
 * @description
 */
@SuppressWarnings("all")
public class MyClassLoader extends ClassLoader {

    private String basePath;
    private final static String FILE_EXT = ".class";

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    // 解密
    private byte[] loadClassData(String name) {
        try {
            String tempName = name.replaceAll("\\.", System.getProperty("file.separator"));
            return XorEncryptUtil.decrypt(new File(basePath + tempName + FILE_EXT));
        } catch (Exception e) {
            System.out.println("自定义类加载器加载失败，错误原因：" + e.getMessage());
            return null;
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] data = this.loadClassData(name);
        return this.defineClass(name, data, 0, data.length);
    }
}
