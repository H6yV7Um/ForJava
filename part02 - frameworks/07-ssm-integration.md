# **SSM框架整合**

## **使用SSM框架搭建服务端**
#### （1）创建一个新的Maven项目
在IntelliJ IDEA中创建一个Maven项目，设置项目名称，删除项目中的src目录。

#### （2）创建四个Module，分别对应四层
在项目下创建四个Module，分别对应bean层、mapper层、service层、controller层。其中，controller层创建为JavaWeb项目，其它层都创建为Java项目，然后将四层对应的项目全部添加Maven依赖。最终得到的项目架构如下图。

![SSM项目框架分层](https://github.com/ITGungnir/ForLearn/blob/master/java-frameworks/images/02-ssm-structure.PNG?raw=true)

## **配置Maven依赖**
#### （1）项目整体的Maven代码：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>my.itgungnir</groupId>
    <artifactId>itgungnir-ssm</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>

    <!-- 整个项目包含的Module及其名称 -->
    <modules>
        <module>itgungnir-ssm-bean</module>
        <module>itgungnir-ssm-mapper</module>
        <module>itgungnir-ssm-service</module>
        <module>itgungnir-ssm-controller</module>
    </modules>

    <properties>
        <spring.version>4.3.10.RELEASE</spring.version>
        <aspectj.version>1.8.6</aspectj.version>
        <jackson.version>2.8.5</jackson.version>
        <slf4j.version>1.7.25</slf4j.version>
        <logback.version>1.2.3</logback.version>
    </properties>

    <dependencies>
        <!-- Spring -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${spring.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- Log -->
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.1.1</version>
        </dependency>

        <!-- JUnit -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>

        <!-- AOP -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjrt</artifactId>
            <version>${aspectj.version}</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${aspectj.version}</version>
        </dependency>

        <!-- JSON -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
            <version>${jackson.version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>${jackson.version}</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${jackson.version}</version>
        </dependency>

        <!-- LOG -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>${logback.version}</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>${logback.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>slf4j-api</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <!-- 配置打包插件 -->
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.3.2</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-resources-plugin</artifactId>
                <version>2.6</version>
                <configuration>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

#### （2）bean层的Maven配置代码：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <!-- 当前Module所在父Project的信息 -->
    <parent>
        <groupId>my.itgungnir</groupId>
        <artifactId>itgungnir-ssm</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <!-- 当前Module的名称 -->
    <artifactId>itgungnir-ssm-bean</artifactId>

</project>
```

#### （3）mapper层的Maven配置代码：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <!-- 当前Module所在父Project的信息 -->
    <parent>
        <groupId>my.itgungnir</groupId>
        <artifactId>itgungnir-ssm</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <!-- 当前Module的名称 -->
    <artifactId>itgungnir-ssm-mapper</artifactId>

    <properties>
        <druid.version>1.0.18</druid.version>
        <mysql-connector.version>5.1.18</mysql-connector.version>
        <mybatis.version>3.2.2</mybatis.version>
        <mybatis-spring.version>1.2.0</mybatis-spring.version>
    </properties>

    <dependencies>
        <!-- Bean层的依赖 -->
        <dependency>
            <groupId>my.itgungnir</groupId>
            <artifactId>itgungnir-ssm-bean</artifactId>
            <version>${project.version}</version>
        </dependency>

        <!-- Druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${druid.version}</version>
        </dependency>

        <!-- MySql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql-connector.version}</version>
        </dependency>

        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>${mybatis.version}</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>${mybatis-spring.version}</version>
        </dependency>
    </dependencies>

</project>
```

#### （4）service层的Maven配置文件：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <!-- 当前Module所在父Project的信息 -->
    <parent>
        <groupId>my.itgungnir</groupId>
        <artifactId>itgungnir-ssm</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <!-- 当前Module的名称 -->
    <artifactId>itgungnir-ssm-service</artifactId>

    <dependencies>
        <!-- Mapper层的依赖 -->
        <dependency>
            <groupId>my.itgungnir</groupId>
            <artifactId>itgungnir-ssm-mapper</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>

</project>
```

#### （5）controller层的Maven配置文件：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <!-- 当前Module所在父Project的信息 -->
    <parent>
        <groupId>my.itgungnir</groupId>
        <artifactId>itgungnir-ssm</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>

    <!-- 当前Module的名称 -->
    <artifactId>itgungnir-ssm-controller</artifactId>

    <!-- 其它Module打包时都是直接打成JAR包 -->
    <!-- 由于这个Module需要直接挂到服务器上，因此需要打成WAR包 -->
    <!-- name和url表示目标WAR包的基本信息 -->
    <packaging>war</packaging>
    <name>itgungnir-ssm-controller Maven Webapp</name>
    <url>http://maven.apache.org</url>
    <build>
        <finalName>itgungnir-ssm-controller</finalName>
    </build>

    <dependencies>
        <!-- Service层的依赖 -->
        <dependency>
            <groupId>my.itgungnir</groupId>
            <artifactId>itgungnir-ssm-service</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>

    <!-- 在不同开发模式下，使用不同的数据库和日志配置 -->
    <profiles>
        <profile>
            <id>development</id>
            <properties>
                <log.path>D:/logs/ssm.itgungnir.com</log.path>
                <log.level>DEBUG</log.level>
                <log.ConversionPattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%p] [%C{1},%M\(\),%L] [%X{logid}] - %m%n</log.ConversionPattern>
                <log.totalSizeCap>512MB</log.totalSizeCap>
                <master.mysql.url>jdbc:mysql://127.0.0.1:3306/test</master.mysql.url>
                <master.mysql.user>root</master.mysql.user>
                <master.mysql.password>root</master.mysql.password>
            </properties>
        </profile>
        <profile>
            <id>production</id>
            <properties>
                <log.path>/export/Logs/ssm.itgungnir.com</log.path>
                <log.level>INFO</log.level>
                <log.ConversionPattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%p] [%C{1},%M\(\),%L] [%X{logid}] - %m%n</log.ConversionPattern>
                <log.maxHistory>24</log.maxHistory>
                <log.totalSizeCap>2GB</log.totalSizeCap>
                <master.mysql.url><![CDATA[jdbc:mysql://my15736m.mysql.jddb.com:3358/paimai_biz?useUnicode=true&amp;characterEncoding=UTF-8]]></master.mysql.url>
            </properties>
        </profile>
    </profiles>

</project>
```

**注意：** <br/>
（1）bean、mapper、service、controller四层之间层层调用，不要循环调用，最终在最顶层的controller层中配置war包的配置，从这一层导出war包。<br/>
（2）如果某个Module中的resources文件夹中没有内容，则可以删除这个文件夹，不影响系统正常运行。

## **web.xml文件配置**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <display-name>itgungnir-ssm</display-name>

    <!-- Spring配置文件 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring-config.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <!-- 设置Servlet编码 -->
    <filter>
        <filter-name>CharacterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <servlet-name>DispatcherServlet</servlet-name>
    </filter-mapping>

    <!-- Spring-MVC配置文件 -->
    <servlet>
        <servlet-name>DispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/spring-config-mvc.xml</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>DispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!-- 开始页面配置 -->
    <welcome-file-list>
        <welcome-file>/index.html</welcome-file>
    </welcome-file-list>

</web-app>
```

## **Spring配置文件**
#### （1）spring-config-mapper.xml文件配置：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 数据库连接池配置 -->
    <bean id="ssmDataSource" class="com.alibaba.druid.pool.DruidDataSource" destroy-method="close">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="${master.mysql.url}"/>
        <property name="username" value="${master.mysql.user}"/>
        <property name="password" value="${master.mysql.password}"/>
        <property name="initialSize" value="10"/>
        <property name="minIdle" value="3"/>
        <property name="maxActive" value="20"/>
        <property name="removeAbandoned" value="true"/>
        <property name="removeAbandonedTimeout" value="300"/>
        <property name="maxWait" value="-1"/>
        <property name="timeBetweenEvictionRunsMillis" value="60000"/>
        <property name="minEvictableIdleTimeMillis" value="300000"/>
        <property name="validationQuery" value="SELECT 'x'"/>
        <property name="testWhileIdle" value="true"/>
        <property name="testOnBorrow" value="false"/>
        <property name="testOnReturn" value="false"/>
        <property name="poolPreparedStatements" value="false"/>
        <property name="filters" value="stat"/>
    </bean>

    <!-- 数据库事务配置 -->
    <bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="ssmDataSource"></property>
    </bean>

    <!-- MyBatis配置 -->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="ssmDataSource"></property>
        <property name="configLocation" value="classpath:sqlmap-config.xml"></property>
        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    </bean>
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="my.itgungnir.ssm.mapper"/>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    </bean>
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate" scope="prototype">
        <constructor-arg index="0" ref="sqlSessionFactory"></constructor-arg>
    </bean>

</beans>
```

#### （2）spring-config-mvc.xml文件配置：
```xml
<?xml version="1.0" encoding="utf-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
       http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <context:component-scan base-package="my.itgungnir.ssm" use-default-filters="false">
        <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
        <context:include-filter type="annotation"
                                expression="org.springframework.web.bind.annotation.ControllerAdvice"/>
    </context:component-scan>

    <aop:aspectj-autoproxy proxy-target-class="true"/>

    <!-- 启用Spring MVC注解 -->
    <mvc:annotation-driven>
        <mvc:message-converters>
            <bean class="org.springframework.http.converter.ByteArrayHttpMessageConverter"/>
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"/>
                <property name="writeAcceptCharset" value="false"/>
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=UTF-8</value>
                        <value>text/plain;charset=UTF-8</value>
                    </list>
                </property>
            </bean>

        </mvc:message-converters>
    </mvc:annotation-driven>

    <!-- 当在web.xml中DispatcherServlet使用<url-pattern>/</url-pattern>映射时，能映射静态资源 -->
    <mvc:default-servlet-handler/>

</beans>
```

#### （3）spring-config.xml文件配置：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

    <!-- 组件扫描配置 -->
    <context:component-scan base-package="my.itgungnir.ssm"/>

    <!-- 本Module中的其它Spring相关配置文件 -->
    <import resource="classpath:spring/*.xml"/>

</beans>
```

## **Mapper配置文件**
#### sqlmap-config.xml文件配置：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
        <setting name="defaultStatementTimeout" value="15"/>
    </settings>

    <!-- 别名，通过指定包来批量设置 -->
    <typeAliases>
        <package name="my.itgungnir.ssm.entity"/>
    </typeAliases>

</configuration>
```

## **日志配置文件**
#### logback.xml文件配置：
```xml
<?xml version='1.0' encoding='UTF-8' ?>
<configuration>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${log.ConversionPattern}</pattern>
        </encoder>
    </appender>

    <appender name="LOGGER-INFO" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${log.path}/info.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${log.path}/info.log.%d{yyyy-w}</fileNamePattern>
            <maxHistory>${log.maxHistory}</maxHistory>
            <totalSizeCap>${log.totalSizeCap}</totalSizeCap>
        </rollingPolicy>
        <encoder>
            <pattern>${log.ConversionPattern}</pattern>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <appender name="LOGGER-ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${log.path}/error.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${log.path}/error.log.%d{yyyy-w}</fileNamePattern>
            <maxHistory>${log.maxHistory}</maxHistory>
            <totalSizeCap>${log.totalSizeCap}</totalSizeCap>
        </rollingPolicy>
        <encoder>
            <pattern>${log.ConversionPattern}</pattern>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <appender name="LOGGER-SQL" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${log.path}/sql.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${log.path}/sql.log.%d{yyyy-w}</fileNamePattern>
            <maxHistory>${log.maxHistory}</maxHistory>
            <totalSizeCap>${log.totalSizeCap}</totalSizeCap>
        </rollingPolicy>
        <encoder>
            <pattern>${log.ConversionPattern}</pattern>
        </encoder>
    </appender>

    <!-- sql日志：不打印在控制台，sql相关出错日志需要记录到error.log文件中-->
    <logger name="org.apache.ibatis" additivity="false" level="TRACE">
        <appender-ref ref="LOGGER-SQL"/>
        <appender-ref ref="LOGGER-ERROR"/>
    </logger>

    <logger name="org.mybatis" additivity="false" level="TRACE">
        <appender-ref ref="LOGGER-SQL"/>
        <appender-ref ref="LOGGER-ERROR"/>
    </logger>

    <logger name="com.jd.auction" additivity="false" level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="LOGGER-INFO"/>
        <appender-ref ref="LOGGER-ERROR"/>
    </logger>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="LOGGER-INFO"/>
    </root>

</configuration>
```

## **具体业务代码**
#### 实体类User.java
```java
package my.itgungnir.ssm.entity;

public class User {
    private int id;
    private String username;
    private String birthday;
    private char sex;
    private String address;

    public User() {
    }

    public User(int id, String username, String birthday, char sex, String address) {
        this.id = id;
        this.username = username;
        this.birthday = birthday;
        this.sex = sex;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                '}';
    }
}
```

#### UserMapper.xml文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="my.itgungnir.ssm.mapper.UserMapper">

    <select id="getUserById" parameterType="int" resultType="User">
        SELECT * FROM users WHERE id = #{id}
    </select>

</mapper>
```

#### UserMapper.java文件
```xml
package my.itgungnir.ssm.mapper;

import my.itgungnir.ssm.entity.User;

public interface UserMapper {
    User getUserById(int id);
}
```

#### UserService.java文件
```xml
package my.itgungnir.ssm.service;

import my.itgungnir.ssm.entity.User;

public interface UserService {
    User getUserById(int id);
}
```

#### UserServiceImpl.java文件
```java
package my.itgungnir.ssm.service.impl;

import my.itgungnir.ssm.entity.User;
import my.itgungnir.ssm.mapper.UserMapper;
import my.itgungnir.ssm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }
}
```

#### UserController.java文件
```java
package my.itgungnir.ssm.controller;

import my.itgungnir.ssm.entity.User;
import my.itgungnir.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public String getUserById(int id) {
        return userService.getUserById(id).toString();
    }
}
```