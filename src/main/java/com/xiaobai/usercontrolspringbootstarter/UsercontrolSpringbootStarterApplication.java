package com.xiaobai.usercontrolspringbootstarter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaobai.usercontrolspringbootstarter.dao")
public class UsercontrolSpringbootStarterApplication {

    public static void main(String[] args){
        SpringApplication.run(UsercontrolSpringbootStarterApplication.class, args);
    }

}
