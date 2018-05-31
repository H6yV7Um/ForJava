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
            User user = userMapper.getUserById(1);
            System.out.println(user.getUsername());
            System.out.println(user.getAddress());
        } finally {
            session.close();
        }
    }
}