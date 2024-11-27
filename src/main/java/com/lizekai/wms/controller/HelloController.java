package com.lizekai.wms.controller;

import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.UserTest;
import com.lizekai.wms.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private UserTestService userTestService;
    @GetMapping
    public String hello(){
        return "Hello!";
    }
    @GetMapping("/list")
    public ResponseResult getAllUser(Long pageNum, Long pageSize){
        return userTestService.getAllUser(pageNum, pageSize);
    }
    @PostMapping("/add")
    public ResponseResult addUser(@RequestBody UserTest user){
        return userTestService.addUser(user);
    }
}
