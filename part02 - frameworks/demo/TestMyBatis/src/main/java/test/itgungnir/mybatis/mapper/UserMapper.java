package test.itgungnir.mybatis.mapper;

import test.itgungnir.mybatis.bean.User;

public interface UserMapper {
    User getUserById(int id);
}