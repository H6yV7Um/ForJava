# **MyBatis基本使用**

## **参考资料**
* [MyBatis入门教程01](https://blog.csdn.net/techbirds_bao/article/details/9233599)
* [MyBatis入门教程02](http://limingnihao.iteye.com/blog/781671)

## **一个DEMO**
**开发环境：IntelliJ IDEA**<br/>
**项目配置：Java项目 + Maven管理**<br/>
**DEMO下载地址：[MyBatis-DEMO](https://github.com/ITGungnir/ForLearn/tree/master/java-frameworks/mybatis/demo)**

**项目中用到的数据库表users的创建SQL语句：**<br/>
```sql
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `birthday` date DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
insert into users(id,username,birthday,sex,address) values(1,'张三1','1995-07-29 00:00:00','1','北京');
insert into users(id,username,birthday,sex,address) values(2,'张三2','1995-07-29 01:00:00','0','上海');
insert into users(id,username,birthday,sex,address) values(3,'张三3','1995-07-29 02:00:00','1','天津');
```

**项目结构图：**<br/>
![MyBatis-Demo项目结构图](https://github.com/ITGungnir/ForLearn/blob/master/java-frameworks/images/01-mybatis-structure.PNG?raw=true)

**Maven管理文件pom.xml中的代码：**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>my.itgungnir</groupId>
    <artifactId>TestMyBatis</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <druid.version>1.0.18</druid.version>
        <mysql-connector.version>5.1.18</mysql-connector.version>
        <mybatis.version>3.2.2</mybatis.version>
        <mybatis-spring.version>1.2.0</mybatis-spring.version>
    </properties>

    <dependencies>
        <!-- MySQL连接 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql-connector.version}</version>
        </dependency>

        <!-- MyBatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>${mybatis.version}</version>
        </dependency>

        <!-- JUnit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

**数据库参数配置文件db.properties中的代码：**<br/>
```
jdbc.driverClass = com.mysql.jdbc.Driver
jdbc.jdbcUrl = jdbc:mysql://127.0.0.1:3306/test
jdbc.user = root
jdbc.password = root
```

**实体类User.java中的代码：**<br/>
```java
package test.itgungnir.mybatis.bean;

public class User {
    public int id; // 主键ID
    public String username; // 用户名
    public String birthday; // 生日
    public char sex; // 性别
    public String address; // 住址

    public User() {}

    public User(int id, String username, String birthday, char sex, String address) {
        this.id = id;
        this.username = username;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getBirthday() { return birthday; }

    public void setBirthday(String birthday) { this.birthday = birthday; }

    public char getSex() { return sex; }

    public void setSex(char sex) { this.sex = sex; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' +
                ", birthday='" + birthday + '\'' + ", sex=" + sex +
                ", address='" + address + '\'' + '}';
    }
}
```

**Mapper配置文件UserMapper.xml中的代码：**<br/>
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="test.itgungnir.mybatis.mapper.UserMapper">

    <select id="getUserById" parameterType="int" resultType="User">
        SELECT * FROM users WHERE id = #{id}
    </select>

</mapper>
```

**Mapper接口文件UserMapper.java中的代码：**<br/>
```java
package test.itgungnir.mybatis.mapper;

import test.itgungnir.mybatis.bean.User;

public interface UserMapper {
    User getUserById(int id);
}
```

**MyBatis配置文件sqlmap-config.xml中的代码：**<br/>
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!-- 将外部的数据库配置文件db.properties加载到文件中来 -->
    <properties resource="db.properties"/>

    <!-- 设置MyBatis的运行时行为 -->
    <settings>
        <setting name="cacheEnabled" value="true"/>
    </settings>

    <!-- 设置别名 -->
    <typeAliases>
        <typeAlias alias="int" type="java.lang.Integer"/>
        <typeAlias alias="User" type="test.itgungnir.mybatis.bean.User"/>
    </typeAliases>

    <!-- 配置MyBatis的环境元素 -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driverClass}"/>
                <property name="url" value="${jdbc.jdbcUrl}"/>
                <property name="username" value="${jdbc.user}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>

    <!-- 配置Mapper文件 -->
    <mappers>
        <mapper resource="mapper/UserMapper.xml"/>
    </mappers>

</configuration>
```

**测试文件TestMyBatis.java中的代码：**<br/>
```java
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import test.itgungnir.mybatis.bean.User;
import test.itgungnir.mybatis.mapper.UserMapper;

import java.io.Reader;

public class TestMyBatis {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    @Before
    public void prepareComponents() {
        try {
            reader = Resources.getResourceAsReader("sqlmap-config.xml");
            // 创建SqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUserById() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            // 通过Mapper接口中的抽象方法对数据库进行操作
            User user = userMapper.getUserById(1);
            System.out.println(user.getUsername());
            System.out.println(user.getAddress());
        } finally {
            session.close(); // 最后别忘了关闭Session对象
        }
    }
}
```

## **Mapper的配置**
MyBatis中，将Mapper统一放在MyBatis配置文件中进行管理。如上面的项目中，将Mapper统一托管在`sqlmap-config.xml`文件中进行管理。

Mapper是通过`<mappers>`标签管理的，在`<mappers>`标签中，配置的是Mapper.xml文件存储的位置。

`<mappers>`标签中可以存放两种子标签：`<mapper>`和`<package>`。

`<package>`标签用于指定Mapper所在的包，通常使用这个标签来批量配置Mapper文件。这个标签中只能设置一个`name`属性，用于指定Mapper文件在java包下的路径。因此，`<package>`标签用于配置xml文件和java接口同时都存放在java包下时的Mapper的位置。

`<mapper>`标签用于配置单个Mapper，这个标签有三种属性：`resource`、`class`和`url`。其中，`url`方式不是很常用。

`<mapper resource="">`的方式适用于Mapper文件存储在resources包下的情况，即当Mapper.xml文件存储在resources包下时，可以使用`resource`属性，其它情况下不适合使用这个属性。配置时，只需要将Mapper.xml文件相对于当前文件的相对路径写入即可，如：`<mapper resource="mapper/UserMapper.xml">`。

`<mapper class="">`的方式适用于Mapper文件存储在java包下的情况，即当Mapper.xml文件存储在java包下时，可以使用`class`属性。使用时，需要将Mapper.xml文件所在的完整包名写入。如：`<mapper class="test.itgungnir.mybatis.mapper.UserMapper">`。

**注意：** Maven默认不会将java包下的xml文件打包到classpath，因此，如果我们要将Mapper.xml文件放在java包下，需要在Maven管理文件`pom.xml`中添加如下代码：
```xml
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <excludes>
                <exclude>**/*.xml</exclude>
            </excludes>
        </resource>
    </resources>
</build>
```

## **\<settings\>标签的配置**
\<settings\>标签中的内容是MyBatis中极为重要的调整设置，它们会改变MyBatis的运行时行为。

\<settings\>标签中内容的含义及其默认值参考下面的网页。<br/>
[\<settings\>标签中内容的含义及默认值参考表](https://blog.csdn.net/u014231523/article/details/53056032)

## **\<environments\>标签的配置**
\<environments\>标签主要用于配置两个参数：`事务管理器TransactionManager`和`数据源DataSource`。

**事务管理器(TransactionManager)的配置：** `<transactionManager type="JDBC"/>`，其中type属性有两种值：`JDBC`和`MANAGED`。当type属性配置为`JDBC`时，系统将直接使用JDBC的提交和回滚设置，即通过与数据库的连接来管理事务；当type属性配置为`MANAGED`时，系统会直接让容器来管理事务的整个生命周期。在这种配置下，系统会默认关闭与数据库的连接，但可以通过下面的代码开启默认连接：
```xml
<transactionManager type="MANAGED">
    <property name="closeConnection" value="false"/>
</transactionManager>
```

**数据源(DataSrouce)的配置：** `<dataSource type="POOLED">...</dataSource>`，其中type属性有三种值：`UNPOOLED`、`POOLED`和`JNDI`。当type属性配置为`UNPOOLED`时，则每次访问网络都会重新创建新的连接；当type属性配置为`POOLED`时，则会通过线程池的概念对连接进行组织，可以减少一定的初始化和认证时间。

## **动态SQL语句**
MyBatis中的动态SQL语句包括如下几种：<br/>
&emsp;**where：** 简化SQL语句中where的条件判断，只能处理and和or；<br/>
&emsp;**if：** 用于简单的条件判断；<br/>
&emsp;**sql：** 相当于将语句封装起来，通过include标签引用；<br/>
```xml
<sql id="user_sql_frame">
    user.id as id
    ,user.username as name
    ,user.birthday as birthday
</sql>

<select id="selectUserByFrame" parameterType="UserConstraint" resultMap="User">
    SELECT
    <include refid="user_sql_frame"/>
    FROM users
    <where>
        <if test="id != null ">and user.id = #{id}</if>
        <if test="name != null ">and user.username = #{name}</if>
        <if test="birthday != null ">and user.birthday = #{birthday}</if>
    </where>
</select>
```
&emsp;**choose：** 类似于Java中的switch语句；<br/>
```xml
<select id="selectUserByChoose" resultType="User">
    SELECT * FROM users WHERE 1 = 1
    <choose>
        <when test="id != null">
            AND id = #{title}
        </when>
        <when test="name != null">
            AND name like #{username}
        </when>
        <otherwise>
            AND birthday = #{birthday}
        </otherwise>
    </choose>
</select>
```
&emsp;**set：** 用于update语句中更新数据；<br/>
```xml
<update id="updateUserBySet">
    UPDATE users
    <set>
        <if test="username != null">username=#{username},</if>
        <if test="birthday != null">birthday=#{birthday},</if>
    </set>
    WHERE id = #{id}
</update>
```
&emsp;**foreach：** 对集合或map进行遍历。<br/>
```xml
<select id="selectUserByForeach" resultType="User">
    SELECT * FROM users WHERE id IN
    <!-- collection属性值：list对应List，array对应数组，map对应Map -->
    <foreach collection="list" index="index" item="id" open="(" separator="," close=")">
        #{id}
    </foreach>
</select>
```
&emsp;**trim：** 对包含的内容进行格式化，添加前缀、后缀等；<br/>
```xml
<insert id="insertUserByTrim" parameterType="User" resultType="User">
    INSERT INTO users
    <trim prefix="(" suffix=")" suffixOverrides=",">
        <if test="id != null">id,</if>
        <if test="username != null">username,</if>
        <if test="birthday != null">birthday,</if>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
        <if test="id != null">#{id},</if>
        <if test="username != null">#{username},</if>
        <if test="birthday != null">#{birthday},</if>
    </trim>
</insert>
```