package com.xiaobai.usercontrolspringbootstarter.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    @Select("select 'true' from ${table} where ${t_column} = #{value}")
    public String isRepeat(@Param("table")String table, @Param("t_column")String t_column,@Param("value")String value);

    @Insert("insert into ${table} (${t_username},${t_password}) values (#{username},#{password})")
    public void register(@Param("table")String table,@Param("t_username")String t_usernaem,@Param("t_password")String t_password,@Param("username")String username,@Param("password")String password);

    @Insert(
        "<script>" +
            "insert into ${table} (${t_username},${t_password}" +
            "<foreach index='t_column' collection='extralFields'>,${t_column}</foreach>) values(${username},#{password}" +
            "<foreach item='value' collection='extralFields'>,#{value}</foreach>)" +
        "</script>"
    )
    public void registerWithExtralFields(@Param("table")String table,@Param("t_username")String t_username,@Param("t_password")String t_password,@Param("username")String username,@Param("password")String password, @Param("extralFields")Map<String,String> extralFields);

    @Select("select ${t_password} from ${table} where ${t_username} = #{username}")
    public String queryUser(@Param("t_password")String t_password,@Param("table")String table,@Param("t_username")String t_username,@Param("username")String username);

    @Update(
        "<script>" +
            "update ${table} set " +
            "<foreach index='t_column' item='value' collection='fields' separator=','>${t_column} = #{value}</foreach>" +
            "where ${t_username} = #{username}" +
        "</script>"
    )
    public void update(@Param("table")String table,@Param("fields")Map<String,String> fields,@Param("t_username")String t_username,@Param("username")String username);

    @Delete("delete from ${table} where ${t_username} = #{username}")
    public void delete(@Param("table")String table,@Param("t_username")String t_username,@Param("username")String username);

    @Select(
        "<script>select " +
            "<foreach item='t_column' collection='fields' separator=','>${t_column}</foreach>" +
            "from ${table} where ${t_username} = #{username}" +
        "</script>"
    )
    public HashMap<String,String> query(@Param("fields") List<String> fields, @Param("table")String table, @Param("t_username")String t_username, @Param("username")String username);
}
