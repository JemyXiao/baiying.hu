package com.baiying.hu.service;

import com.baiying.hu.entity.User;
import com.baiying.hu.entity.UserExample;
import com.baiying.hu.entity.dto.UserLoginDto;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by jmx on
 * 2017/9/22.
 */
public interface UserService {
    int registerUser(User record);

    void loginUser(UserLoginDto record, HttpServletResponse response);

    User queryUserByParam(UserExample example);

    int updateUserById(User user);
}
