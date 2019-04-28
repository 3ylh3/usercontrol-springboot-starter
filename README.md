# usercontrol-springboot-starter
一个简单的基于Mybatis的用户控制模块，提供Spring Boot工程的用户注册、登录、信息更新以及销户功能的支持。  
### 使用方法：  
 1. 引入编译好的jar包以及Mybatis依赖。
 2. 在工程启动类上添加注解：
    ```java
    @MapperScan(UserControl.BASE_PACKAGE)
    ```
 3. 在工程配置文件(application.yml/properties)中配置用户表名以及表中用户名和密码字段的字段名，例子如下(以yml配置文件为例)：
    ```yaml
    usercontrol:
      table: testTable
      username: testusername
      password: testpassword
    ```
 4. 调用相应API即可进行相关操作。

日志使用方法：在项目日志配置文件中新增对应name的logger配置，支持logback、log4j以及log4j2。logger对应name如下：
 - 注册：register
 - 登录：login
 - 更新信息：update
 - 查询信息：query
 - 销户：delete

以logback为例，如果需要开启注册服务的日志，那么需要在对应配置文件中新增对应配置即可（注意logger的name必须为register）：
```xml
<appender name="register" class="ch.qos.logback.core.rolling.RollingFileAppender">
   <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
      <fileNamePattern>usercontrol/register_%d{yyyyMMdd}.log</fileNamePattern>
   </rollingPolicy>
   <encoder>
      <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%-5level]:%msg%n</pattern>
   </encoder>
</appender>

<logger name="register" level="info" addtivity="false">
   <appender-ref ref="register"/>
</logger>
```

### API列表
#### 通用用户注册服务：RegisterService.register(String username,String password)。
参数：
 - username:需要注册用户的用户名。
 - password:需要注册用户的密码。
 
功能：
对username进行堵重判断，将记录插入对应用户表中。
返回码(String类型)：
 - 0000:成功。
 - 0001:用户名重复。
 - 0004:异常。

#### 带额外字段的注册服务：RegisterService.register(String username,String password,Map<String,String>extralFields,Map<String,String>checkFields)
参数：
 - username:需要注册用户的用户名。
 - password:需要注册用户的密码。
 - extralFields:存储额外字段的Map,key为表中相应字段的字段名，value为要插入的字段值。
 - checkFields:存储需要进行堵重判断字段的Map,key为表中相应字段的字段名，value为要插入的字段值。

功能：
对username以及checkFields中的字段进行堵重判断，将记录插入对应用户表中。
返回码：
 - 0000:成功。
 - 0004:异常。

若有字段重复，则返回: 重复字段名:重复字段值。
#### 用户登录服务：LoginService.login(String username,String password)
参数：
 - username:登录用户的用户名。
 - password:登录用户的密码。
功能:
对登录的用户进行存在性判断以及密码正确性校验。
返回码(String类型)：
 - 0000:成功。
 - 0002:用户不存在。
 - 0003:密码错误。
 - 0004:异常。

#### 用户信息更新服务：UpdateService.update(String username,Map<String,String>fields)
参数：
 - username:待更新用户的用户名。
 - fields:存储待更新信息的Map,key为表中对应的字段名，value为需要更新的字段值。

返回码(String类型)：
 - 0000:成功。
 - 0002:用户不存在。
 - 0004:异常。

#### 用户销户服务：DeleteService.delete(String username)
参数：
 - username:待销户用户名。

功能：
删除指定用户在表中的信息。
返回码(String类型)：
 - 0000:成功。
 - 0002:用户不存在。
 - 0004:异常。

#### 查询用户信息服务：QueryUserService.query(String username,List<String> fields)
参数:
 - username:用户名。
 - fields:待查询字段的List。

功能：
查询指定用户的指定信息。
返回值(Map<String,String>类型):
查询结果Map，kay为字段名，value为值。
#### 更新记录
 - 2019-04-24:v1.0.0
 - 2019-04-28:v1.1.0
### 1.1.0版本特性
 1. 新增用户信息查询接口。
 2. 新增日志系统。
 3. 优化注册接口。

欢迎访问我的个人博客：
https://www.xiaobai.pub/
