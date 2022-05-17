package com.rockvine.spi;

import com.rockvine.spi.service.SpiDemoService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author rocky
 * @date 2022-05-17 13:16
 * @description
 */
@SuppressWarnings("all")
public class SpiApp {
    public static void main(String[] args) {
        ServiceLoader<SpiDemoService> services = ServiceLoader.load(SpiDemoService.class);
        Iterator<SpiDemoService> iterator = services.iterator();
        while (iterator.hasNext()){
            SpiDemoService service = iterator.next();
            service.sayHello();
        }
    }
}
