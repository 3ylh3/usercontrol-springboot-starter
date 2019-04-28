package com.xiaobai.usercontrolspringbootstarter.service;

import com.xiaobai.usercontrolspringbootstarter.config.UserControl;
import com.xiaobai.usercontrolspringbootstarter.config.UserControlProperties;
import com.xiaobai.usercontrolspringbootstarter.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 更新用户信息服务
 */
public class UpdateService {
    private UserControlProperties properties;
    private Logger logger = LoggerFactory.getLogger("update");
    @Autowired
    private UserMapper userMapper;

    public UpdateService(UserControlProperties properties){
        this.properties = properties;
    }

    /**
     * 更新用户信息
     * @param username 用户名
     * @param fields 待更新字段Map,key为表中字段名，value为值
     * @return 返回码
     */
    public String update(String username, Map<String,String>fields){
        logger.info("update-------------------------------------------------------------------");
        //获取配置文件中配置的表名和字段名
        String table = properties.getTable();
        String t_username = properties.getUsername();
        String t_password = properties.getPassword();
        logger.info("用户表名:" + table);
        logger.info(t_username + ":" + username);
        for(Map.Entry<String,String> entry : fields.entrySet()){
            logger.info(entry.getKey() + ":" + entry.getValue());
        }
        try {
            //判断用户是否存在
            String flag = userMapper.queryUser(t_password, table, t_username, username);
            if (flag != null) {
                //更新用户信息
                logger.info("执行更新信息SQL...");
                userMapper.update(table, fields, t_username, username);
                logger.info("执行成功");
                return UserControl.SUCCESS;
            } else {
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
