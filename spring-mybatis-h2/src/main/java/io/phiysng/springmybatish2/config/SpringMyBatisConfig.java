package io.phiysng.springmybatish2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
public class SpringMyBatisConfig {
    @Value(value = "${spring-mybatis-h2.port:11809}")
    private int port;

    @Value(value = "${spring-mybatis-h2.name:default}")
    private String name;

    @PostConstruct
    public void initConfig(){
      log.info("SpringMyBatisConfig port:{},name:{}",port , name);
    }
}
