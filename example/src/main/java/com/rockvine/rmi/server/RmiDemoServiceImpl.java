package com.rockvine.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author rocky
 * @date 2022-05-17 11:10
 * @description
 */
public class RmiDemoServiceImpl extends UnicastRemoteObject implements RmiDemoService{

    protected RmiDemoServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String name) throws RemoteException {
        return "Hello, " + name;
    }
}
