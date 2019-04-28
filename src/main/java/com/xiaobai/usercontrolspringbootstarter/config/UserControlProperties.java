package com.xiaobai.usercontrolspringbootstarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置信息类
 */
@ConfigurationProperties("usercontorl")
public class UserControlProperties {
    //用户表名
    private String table;
    //用户表用户名字段
    private String username;
    //用户表密码字段
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
}
