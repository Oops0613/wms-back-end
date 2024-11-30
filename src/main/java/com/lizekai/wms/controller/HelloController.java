package com.lizekai.wms.controller;

import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.UserTestDto;
import com.lizekai.wms.domain.entity.UserTest;
import com.lizekai.wms.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private UserTestService userTestService;
    @GetMapping
    public String hello(){
        return "Hello!";
    }
    @GetMapping("/list")
    public ResponseResult getAllUser(UserTestDto dto){
        return userTestService.listUser(dto);
    }
    @PostMapping("/add")
    public ResponseResult addUser(@RequestBody UserTest user){
        return userTestService.addUser(user);
    }
    @PostMapping("/edit")
    public ResponseResult editUser(@RequestBody UserTest user){
        return userTestService.editUser(user);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseResult remove(@PathVariable Long id) {
        userTestService.removeById(id);
        return ResponseResult.okResult();
    }

}
