package io.phiysng.springmybatish2;

import io.phiysng.springmybatish2.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringMybatisH2Application {

    public static void main(String[] args) {
        var ctx = SpringApplication.run(SpringMybatisH2Application.class, args);
    }

}
