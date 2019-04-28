package com.xiaobai.usercontrolspringbootstarter.config;

import com.xiaobai.usercontrolspringbootstarter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动装配类
 */
@Configuration
@EnableConfigurationProperties(UserControlProperties.class)
public class UserControlAutoConfigure {
    @Autowired
    UserControlProperties properties;

    /**
     * 初始化RegisterService
     * @return RegisterService
     */
    @Bean
    @ConditionalOnMissingBean
    public RegisterService initRegisterService(){
        return new RegisterService(properties);
    }

    /**
     * 初始化LoginService
     * @return LoginService
     */
    @Bean
    @ConditionalOnMissingBean
    public LoginService initLoginService(){
        return new LoginService(properties);
    }

    /**
     * 初始化UpdateService
     * @return UpdateService
     */
    @Bean
    @ConditionalOnMissingBean
    public UpdateService initUpdateService(){
        return new UpdateService(properties);
    }

    /**
     * 初始化DeleteService
     * @return DeleteService
     */
    @Bean
    @ConditionalOnMissingBean
    public DeleteService initDeleteService(){
        return new DeleteService(properties);
    }

    /**
     * 初始化QueryUserService
     * @return QueryUserService
     */
    @Bean
    @ConditionalOnMissingBean
    public QueryUserService initQueryUserService(){
        return new QueryUserService(properties);
    }
}
