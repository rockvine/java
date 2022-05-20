package com.rockvine.example.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author rocky
 * @date 2022-05-17 11:08
 * @description rmi远程接口
 */
public interface RmiDemoService extends Remote {
    String sayHello(String name) throws RemoteException;
}
