package com.lizekai.wms;

import com.lizekai.wms.utils.EmailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WmsApplicationTests {
    @Autowired
    private EmailUtil emailUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void sendStringEmail() {
        // 测试文本邮件发送（无附件）
        String to = "2351782@tongji.edu.cn"; // 这是个假邮箱，写成你自己的邮箱地址就可以
        String title = "文本邮件发送测试";
        String content = "Hello World!";
        emailUtil.sendMessage(to, title, content);
    }
}
