package com.baiying.hu.service.impl;

import com.baiying.hu.config.Constant;
import com.baiying.hu.entity.User;
import com.baiying.hu.entity.UserExample;
import com.baiying.hu.entity.dto.UserLoginDto;
import com.baiying.hu.entity.dto.UserRegisterDto;
import com.baiying.hu.enums.UserStatusEnum;
import com.baiying.hu.mapper.CheckPhoneCodeMapper;
import com.baiying.hu.mapper.UserMapper;
import com.baiying.hu.service.UserService;
import com.baiying.hu.util.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int countRegister(long phone) {
        UserExample example = new UserExample();
        example.createCriteria().andPhoneEqualTo(phone);
        return userMapper.countByExample(example);
    }

    @Override
    public User selectUserInfo(HttpServletRequest request) {
        String token = CookieUtils.getCookieValue(request, "COFFEE_TOKEN");
        UserExample example = new UserExample();
        example.createCriteria().andTokenEqualTo(token);
        return queryUserByParam(example);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectByExample(new UserExample());
    }

    @Override
    public int companyRegister(UserRegisterDto dto) {
        return userMapper.userCompanyRegister(dto);
    }

    @Override
    public List<Map> getServiceList() {
        return userMapper.getServiceList();
    }

    @Override
    public int authorServiceProvider(Map map) {
        return userMapper.authorServiceProvider(map);
    }

}
