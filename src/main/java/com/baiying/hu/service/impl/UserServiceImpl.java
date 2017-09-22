package com.baiying.hu.service.impl;

import com.baiying.hu.entity.User;
import com.baiying.hu.entity.UserExample;
import com.baiying.hu.entity.dto.UserLoginDto;
import com.baiying.hu.mapper.UserMapper;
import com.baiying.hu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by jmx
 * 2017/9/22
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int registerUser(User record) {
        return userMapper.insert(record);
    }

    @Override
    public void loginUser(UserLoginDto record, HttpServletResponse response) {

    }

    @Override
    public User queryUserByParam(UserExample example) {
        return userMapper.selectByExample(example).get(0);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

}
