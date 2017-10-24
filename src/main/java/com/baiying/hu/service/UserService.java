package com.baiying.hu.service;

import com.baiying.hu.entity.User;
import com.baiying.hu.entity.UserExample;
import com.baiying.hu.entity.dto.UserLoginDto;
import com.baiying.hu.entity.dto.UserRegisterDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by jmx on
 * 2017/9/22.
 */
public interface UserService {
    int registerUser(User record);

    void loginUser(UserLoginDto record, HttpServletResponse response);

    User queryUserByParam(UserExample example);

    int updateUserById(User user);

    int countRegister(long phone);

    User selectUserInfo(HttpServletRequest request);

    List<User> getUserList();

    int companyRegister(UserRegisterDto dto);

    List<Map> getServiceList();

    int authorServiceProvider(Map map);
}
