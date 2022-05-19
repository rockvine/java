package com.rockvine.loader;

/**
 * @author rocky
 * @date 2022-05-18 23:18
 * @description 自定义类加载器
 */
public class Client {
    public static void main(String[] args) throws Exception {
        String dirPath = System.getProperty("user.dir");
        String basePath = dirPath + "/jvm/src/main/java/";

        MyClassLoader myClassLoader = new MyClassLoader();
        myClassLoader.setBasePath(basePath);

        Class<?> clazz = myClassLoader.findClass("com.rockvine.loader.User");
        System.out.println(clazz.getClassLoader());

        Object o = clazz.newInstance();
        System.out.println(o);
    }
}
