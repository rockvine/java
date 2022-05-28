package com.rockvine.jvm.classloader;

/**
 * @author rocky
 * @date 2022-05-18 23:18
 * @description 自定义类加载器
 */
public class App {
    public static void main(String[] args) throws Exception {
        String dirPath = System.getProperty("user.dir");
        String basePath = dirPath + "/jvm/src/main/java/";

        MyClassLoader myClassLoader = new MyClassLoader();
        myClassLoader.setBasePath(basePath);

        Class<?> clazz = myClassLoader.findClass("com.rockvine.jvm.classloader.User");
        System.out.println(clazz.getClassLoader());

        Object o = clazz.newInstance();
        System.out.println(o);
    }
}
