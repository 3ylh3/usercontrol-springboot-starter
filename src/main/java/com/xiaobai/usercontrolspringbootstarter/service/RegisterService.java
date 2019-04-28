package com.xiaobai.usercontrolspringbootstarter.service;

import com.xiaobai.usercontrolspringbootstarter.config.UserControl;
import com.xiaobai.usercontrolspringbootstarter.config.UserControlProperties;
import com.xiaobai.usercontrolspringbootstarter.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 用户注册服务
 */
public class RegisterService {
    private UserControlProperties properties;
    Logger logger = LoggerFactory.getLogger("register");
    @Autowired
    private UserMapper userMapper;

    public RegisterService(UserControlProperties properties){
        this.properties = properties;
    }

    /**
     * 通用用户注册
     * @param username 用户名
     * @param password 密码
     * @return 返回码
     */
    public String register(String username,String password){
        logger.info("register-----------------------------------------------------------------");
        //获取配置文件中配置的表名和字段名
        String table = properties.getTable();
        String t_username = properties.getUsername();
        String t_password = properties.getPassword();
        logger.info("用户表名:" + table);
        logger.info(t_username + ":" + username);
        logger.info(t_password + ":" + password);
        logger.info("判重...");
        //判重
        String flag = userMapper.isRepeat(table,t_username,username);
        if(flag != null && flag.equals("true")){
            logger.error("用户已存在");
            return UserControl.REPEAT;
        }
        //插入用户表中
        else {
            logger.info("执行插入SQL...");
            try {
                userMapper.register(table, t_username, t_password, username, password);
                logger.info("执行成功");
                return UserControl.SUCCESS;
            }
            catch(Exception e){
                logger.error(e.toString());
                return UserControl.EXCEPTION;
            }
        }
    }

    /**
     * 带额外字段的注册
     * @param username 用户名
     * @param password 密码
     * @param extralFields 额外字段Map,key为表中字段名，value为字段值
     * @Param checkFields 需要判重的字段Map,key为表中字段名，value为字段值
     * @return 返回码
     */
    public String register(String username, String password, Map<String,String>extralFields,Map<String,String>checkFields){
        logger.info("register-----------------------------------------------------------------");
        //获取配置文件中配置的表名和字段名
        String table = properties.getTable();
        String t_username = properties.getUsername();
        String t_password = properties.getPassword();
        logger.info("用户表名:" + table);
        logger.info(t_username + ":" + username);
        logger.info(t_password + ":" + password);
        for(Map.Entry<String,String> entry : extralFields.entrySet()){
            logger.info(entry.getKey() + ":" + entry.getValue());
        }
        logger.info("判重...");
        //判重
        String flag = userMapper.isRepeat(table,t_username,username);
        if(flag != null && flag.equals("true")){
            logger.error("用户已存在");
            return UserControl.REPEAT;
        }
        for(Map.Entry<String,String> entry : checkFields.entrySet()){
            String flag1 = userMapper.isRepeat(table,entry.getKey(),entry.getValue());
            if(flag1 != null && flag1.equals("true")){
                logger.error(entry.getKey() + "字段重复");
                return entry.getKey() + ":" + entry.getValue();
            }
        }
        //插入用户表中
        logger.info("执行插入SQL...");
        try {
            userMapper.registerWithExtralFields(table, t_username, t_password, username, password, extralFields);
            logger.info("执行成功");
            return UserControl.SUCCESS;
        }
        catch(Exception e){
            logger.error(e.toString());
            return UserControl.EXCEPTION;
        }
    }
}
