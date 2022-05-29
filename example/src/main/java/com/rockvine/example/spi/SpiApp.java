package com.rockvine.example.spi;

import com.rockvine.example.spi.service.SpiDemoService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author rocky
 * @date 2022-05-17 13:16
 * @description Java SPI机制
 */
@SuppressWarnings("WhileLoopReplaceableByForEach")
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
