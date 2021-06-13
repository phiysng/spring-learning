package io.phiysng.springmybatish2.controller;


import io.phiysng.springmybatish2.entity.User;
import io.phiysng.springmybatish2.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class HelloController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    @ResponseBody
    public User createPerson(@RequestParam(value = "id",required = false) Integer id, HttpServletRequest request, HttpServletResponse response) {
        log.info("id : {}",id);
        var r = userMapper.findById(id);
        log.info("{}",r);
        return r;
    }
}