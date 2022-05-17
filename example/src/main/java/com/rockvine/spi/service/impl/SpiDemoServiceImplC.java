package com.rockvine.spi.service.impl;

import com.rockvine.spi.service.SpiDemoService;

/**
 * @author rocky
 * @date 2022-05-17 13:06
 * @description
 */
public class SpiDemoServiceImplC implements SpiDemoService {
    @Override
    public void sayHello() {
        System.out.println("Hello SpiDemoServiceImplC");
    }
}
