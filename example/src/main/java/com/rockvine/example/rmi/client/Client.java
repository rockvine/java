package com.rockvine.example.rmi.client;

import com.rockvine.example.rmi.server.RmiDemoService;

import java.rmi.Naming;

/**
 * @author rocky
 * @date 2022-05-17 11:18
 * @description rmi客户端
 */
public class Client {
    public static void main(String[] args) {
        try {
            // 通过RMI发现服务并且转成一个对象
            RmiDemoService demoService = (RmiDemoService) Naming.lookup("rmi://127.0.0.1:8888/rmi-demo");
            // 远程调用下服务方法
            System.out.println(demoService.sayHello("RMI"));
        } catch (Exception e) {
            System.out.println("调用远程对象失败，错误信息：" + e.getMessage());
        }
    }
}
