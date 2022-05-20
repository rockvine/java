package com.rockvine.example.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author rocky
 * @date 2022-05-17 11:10
 * @description rmi远程实现类
 */
public class RmiDemoServiceImpl extends UnicastRemoteObject implements RmiDemoService{
    private static final long serialVersionUID = -5765904591195416230L;

    protected RmiDemoServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello, " + name;
    }
}
