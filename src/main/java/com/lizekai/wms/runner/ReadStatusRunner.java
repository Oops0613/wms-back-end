package com.lizekai.wms.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 项目启动时进行的预处理操作
 */
@Component
public class ReadStatusRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("项目启动");
    }
}
