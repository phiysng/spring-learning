package io.phiysng.springmybatish2;

import io.phiysng.springmybatish2.mapper.UserMapper;
import io.phiysng.springmybatish2.service.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringMybatisH2ApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    HelloService helloService;

    @Test
    void contextLoads() {
        System.out.println();
        System.out.println(userMapper.findById(4));
        Assertions.assertEquals(3, (long) userMapper.findById(3).getId());
        Assertions.assertNotNull(userMapper.findById(1));
        Assertions.assertNull(userMapper.findById(11));
//        Assertions.assertNull(userMapper.findById(1));
    }

    @Test
    void serviceLoaded(){
        Assertions.assertEquals(helloService.getUserById(1).getId(),1);
        Assertions.assertNull(helloService.getUserById(111));
    }

}
