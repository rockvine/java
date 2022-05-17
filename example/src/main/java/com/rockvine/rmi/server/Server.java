package com.rockvine.rmi.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @author rocky
 * @date 2022-05-17 11:13
 * @description
 */
public class Server {
    public static void main(String[] args) {
        try {
            // 接口实例化
            RmiDemoService rmiDemoService = new RmiDemoServiceImpl();
            // 将服务注册到8888端口
            LocateRegistry.createRegistry(8888);
            // 把接口实例绑定到端口的一个路径上
            Naming.bind("rmi://127.0.0.1:8888/rmi-demo", rmiDemoService);
        } catch (Exception e) {
            System.out.println("注册远程对象失败，错误信息：" + e.getMessage());
        }
    }
}
