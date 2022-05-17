package com.rockvine.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author rocky
 * @date 2022-05-17 23:21
 * @description 方法区溢出
 */
public class MethodAreaOOMTest {

    /*
     * JVM参数配置：-XX:MaxMetaspaceSize=10m
     */
    public static void main(String[] args) {
        testMethodAreaOOM();
    }

    private static void testMethodAreaOOM() {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Target.class);
            enhancer.setCallback(new CglibProxy());
            enhancer.setUseCache(false);
            enhancer.create();
        }
    }

    static class Target{
    }

    static class CglibProxy implements MethodInterceptor {
        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            return methodProxy.invokeSuper(o,objects);
        }
    }
}
