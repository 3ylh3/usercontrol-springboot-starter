package com.xiaobai.usercontrolspringbootstarter.service;

import com.xiaobai.usercontrolspringbootstarter.config.UserControl;
import com.xiaobai.usercontrolspringbootstarter.config.UserControlProperties;
import com.xiaobai.usercontrolspringbootstarter.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户登录服务
 */
public class LoginService {
    private UserControlProperties properties;
    private Logger logger = LoggerFactory.getLogger("login");
    @Autowired
    private UserMapper userMapper;

    public LoginService(UserControlProperties properties){
        this.properties = properties;
    }

    public String login(String username,String password){
        logger.info("login--------------------------------------------------------------------");
        //获取配置文件中配置的表名和字段名
        String table = properties.getTable();
        String t_username = properties.getUsername();
        String t_password = properties.getPassword();
        logger.info("用户表名:" + table);
        logger.info(t_username + ":" + username);
        logger.info(t_password + ":" + password);
        //判断用户是否存在
        try {
            String t_pass = userMapper.queryUser(t_password, table, t_username, username);
            if(t_pass != null){
                //用户存在，判断密码是否正确
                logger.info("进行身份验证...");
                if(t_pass.equals(password)){
                    //密码正确
                    logger.info("身份验证通过");
                    return UserControl.SUCCESS;
                }
                else{
                    //密码错误
                    logger.info("密码错误");
                    return UserControl.ERROR_PASSWORD;
                }
            }
            else{
                //用户不存在
                logger.error("用户不存在");
                return UserControl.NULL;
            }
        }
        catch (Exception e){
            logger.error(e.toString());
            return UserControl.EXCEPTION;
        }
    }
}
