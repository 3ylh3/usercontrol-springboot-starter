package com.xiaobai.usercontrolspringbootstarter.service;

import com.xiaobai.usercontrolspringbootstarter.config.UserControlProperties;
import com.xiaobai.usercontrolspringbootstarter.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class QueryUserService {
    private UserControlProperties properties;
    private Logger logger = LoggerFactory.getLogger("query");
    @Autowired
    private UserMapper userMapper;

    public QueryUserService(UserControlProperties properties){
        this.properties = properties;
    }

    /**
     * 查询用户信息
     * @param username 用户名
     * @param fields 查询字段
     * @return 用户信息List
     */
    public Map<String,String> query(String username, List<String> fields){
        logger.info("query--------------------------------------------------------------------");
        //获取配置文件中配置的表名和字段名
        String table = properties.getTable();
        String t_username = properties.getUsername();
        logger.info("用户表名:" + table);
        logger.info(t_username + ":" + username);
        try {
            //判断用户是否存在
            String flag = userMapper.queryUser(username, table, t_username, username);
            if (flag != null) {
                //查询用户信息
                logger.info("执行查询SQL...");
                Map<String,String> map = userMapper.query(fields,table, t_username, username);
                logger.info("执行成功");
                logger.info("查询结果:");
                for(Map.Entry<String,String> entry : map.entrySet()){
                    logger.info(entry.getKey() + ":" + entry.getValue());
                }
                return map;

            } else {
                //用户不存在
                logger.error("用户不存在");
                return null;
            }
        }
        catch (Exception e){
            logger.error(e.toString());
            return null;
        }
    }
}
