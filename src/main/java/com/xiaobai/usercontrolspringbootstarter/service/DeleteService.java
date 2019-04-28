package com.xiaobai.usercontrolspringbootstarter.service;

import com.xiaobai.usercontrolspringbootstarter.config.UserControl;
import com.xiaobai.usercontrolspringbootstarter.config.UserControlProperties;
import com.xiaobai.usercontrolspringbootstarter.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 销户服务
 */
public class DeleteService {
    private UserControlProperties properties;
    private Logger logger = LoggerFactory.getLogger("delete");
    @Autowired
    private UserMapper userMapper;

    public DeleteService(UserControlProperties properties){
        this.properties = properties;
    }

    /**
     * 销户
     * @param username 用户名
     * @return 返回码
     */
    public String delete(String username){
        logger.info("delete-------------------------------------------------------------------");
        //获取配置文件中配置的表名和字段名
        String table = properties.getTable();
        String t_username = properties.getUsername();
        logger.info("用户表名:" + table);
        logger.info(t_username + ":" + username);
        try {
            //判断用户是否存在
            String flag = userMapper.queryUser(username, table, t_username, username);
            if (flag != null) {
                //销户
                logger.info("执行删除SQL...");
                userMapper.delete(table, t_username, username);
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
