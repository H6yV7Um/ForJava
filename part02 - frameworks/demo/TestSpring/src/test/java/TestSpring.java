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