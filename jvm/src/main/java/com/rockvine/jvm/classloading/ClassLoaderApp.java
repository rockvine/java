package com.rockvine.jvm.classloading;

/**
 * @author rocky
 * @date 2022-05-17 17:57
 * @description 查看类加载器
 */
public class ClassLoaderApp {
    public static void main(String[] args) {
        // 启动类加载器（Bootstrap ClassLoader）
        System.out.println(String.class.getClassLoader());

        // 扩展类加载器（Extension ClassLoader）
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());

        // 应用程序类加载器（Application ClassLoader）
        System.out.println(ClassLoaderApp.class.getClassLoader());
    }
}
