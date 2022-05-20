package com.rockvine.example.spi.service.impl;

import com.rockvine.example.spi.service.SpiDemoService;

/**
 * @author rocky
 * @date 2022-05-17 13:06
 * @description spi实现类B
 */
public class SpiDemoServiceImplB implements SpiDemoService {
    @Override
    public void sayHello() {
        System.out.println("Hello SpiDemoServiceImplB");
    }
}
