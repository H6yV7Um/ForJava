package my.itgungnir.ssm.service.impl;

import my.itgungnir.ssm.entity.User;
import my.itgungnir.ssm.mapper.UserMapper;
import my.itgungnir.ssm.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }
}