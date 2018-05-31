# **Spring基本使用**

## **Spring框架搭建**
**在Maven管理文件pom.xml中导入Spring所需依赖包：**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>test.itgungnir</groupId>
    <artifactId>TestSpring</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <spring.version>4.3.10.RELEASE</spring.version>
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
    </dependencies>
</project>
```

## **Spring IoC容器（基于XML的配置）**
**创建一个目标实体类：**
```java
package test.itgungnir.spring.bean;

public class Message {
    private String msg;

    public String getMsg() { return msg; }

    public void setMsg(String msg) { this.msg = msg; }
}
```

**创建一个XML文件来管理实体类：**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans 
       http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

    <bean id="message" class="test.itgungnir.spring.bean.Message">
        <property name="msg" value="Hello Spring Message..."/>
    </bean>

</beans>
```

**通过IoC创建Bean并调用其方法：**
```java
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import test.itgungnir.spring.bean.Message;

public class TestSpring {
    @Test
    public void testSpring() {
        // 方法一：通过Sping的BeanFactory容器创建Bean（不再推荐使用）
        // XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring-beans.xml"));
        // Message message = (Message) factory.getBean("message");

        // 方法二：通过Spring的ApplicationContext容器创建Bean
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        Message message = (Message) context.getBean("message");

        System.out.println(message.getMsg());
    }
}
```

**Spring XML文件中Bean的配置参数：**<br/>
&emsp;**class：** 指定用来创建Bean的实体类；<br/>
&emsp;**name：** 指定Bean的唯一标识符，也可以用id属性来唯一标识；<br/>
&emsp;**scope：** 指定Bean创建的对象的作用域：<br/>
&emsp;&emsp;**singleton：** 单例，全局仅存在一个Bean，在系统启动的时候就创建；<br/>
&emsp;&emsp;**prototype：** 每次都会创建一个新的Bean，且是在使用Bean的时候才创建；<br/>
&emsp;&emsp;**request：** 每次Http请求都创建一个新的Bean；<br/>
&emsp;&emsp;**session：** 同一个Http Session共享一个Bean<br/>
&emsp;**lazy-init：** 设置为true时对象将在Bean第一次被使用时创建，否则在系统启动时创建；<br/>
&emsp;**init-method：** 在Bean被创建的时候回调执行的方法；<br/>
&emsp;**destroy-method：** 在Bean被从容器中移除时回调的方法；<br/>
&emsp;**autowire：** 自动装配，即让IoC容器自动根据name等属性装载Bean，而不需要再配置XML：<br/>
&emsp;&emsp;**no：** 不自动装配；<br/>
&emsp;&emsp;**byName：** 根据属性名自动装配；<br/>
&emsp;&emsp;**byType：** 根据属性的数据类型自动装配；<br/>
&emsp;&emsp;**constructor：** 根据属性的构造函数参数类型自动装配；<br/>
&emsp;&emsp;**autodetect：** 自动搜索，依次通过constructor和byType方式自动匹配。<br/>

**Spring XML文件中Bean的子元素：**<br/>
&emsp;**property：** 在运行时动态修改Bean中的字段属性；<br/>
&emsp;**constructor-arg：** 向Bean的构造方法中传入参数：<br/>
&emsp;&emsp;（1）`<constructor-arg type="int" value="2000"/>`<br/>
&emsp;&emsp;（2）`<constructor-arg index="0" value="2000"/>`<br/>
&emsp;**注：** 如果是基本数据类型，则使用value；如果是复杂数据类型，则使用ref

## **Spring IoC容器（基于注解的配置）**
#### 基础配置
给予注解的Spring IoC配置，需要首先在Spring配置文件中配置开启：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-3.0.xsd">

   <context:annotation-config/>
   <!-- bean definitions go here -->

</beans>
```

#### @Required 注解
@Required注解应用于Bean的setter方法上，表示需要在配置文件中对这个属性进行配置，受影响的Bean及其被@Required注解的属性都必须在XML文件中进行配置，否则会报异常：BeanInitializationException。

#### @Autowired 注解
@Autowired注解可以作用在属性上、方法上、构造方法上，被标记的属性、方法中的参数和构造方法中的参数，会通过相当于byType的方式自动装载。

#### @Qualifier 注解
当IoC容器中有多个相同type的Bean，则可以通过@Qualifier注解来指定具体指向哪个Bean。

#### @PostConstruct 和 @PreDestroy 注解
相当于XML配置中的init-method和destroy-method属性，即：被@PostConstruct注解的方法在Bean被初始化时被调用；被@PreDestroy注解的方法在Bean被移出容器时被调用。

#### @Resource 注解
@Resource注解应用于Bean的setter方法上，需要指定一个name属性：`@Resource(name="a")`，即相当于使用byName方式寻找方法参数中的对象。

# **Spring AOP**
#### Spring AOP 配置
在Maven管理文件pom.xml文件中添加Spring AOP的依赖：
```xml
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
```

#### **在Spring配置文件中添加AOP配置**
```xml
<aop:config>
    <aop:aspect id="log" ref="logging">
        <aop:pointcut id="selectAll" expression="execution(* my.itgungnir.*.*(..))"/>
        <aop:before pointcut-ref="selectAll" method="beforeAdvice"/>
        <aop:after pointcut-ref="selectAll" method="afterAdvice"/>
        <aop:after-returning pointcut-ref="selectAll" returning="retVal"
            method="afterReturningAdvice"/>
        <aop:after-throwing pointcut-ref="selectAll" throwing="ex"
            method="AfterThrowingAdvice"/>
    </aop:aspect>
</aop:config>

<bean id="logging" class="com.tutorialspoint.Logging"/>
```
上述代码表示：当执行my.itgungnir包下所有类中的所有方法时，都会将这些方法作为切面，并为这些切面添加before、after、after-returning、after-throwing四个处理，分别调用Logging类中的指定方法。