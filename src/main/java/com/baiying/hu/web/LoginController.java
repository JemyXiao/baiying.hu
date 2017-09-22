package com.baiying.hu.web;

import com.alibaba.fastjson.JSON;
import com.baiying.hu.common.validate.ValidateService;
import com.baiying.hu.config.Constant;
import com.baiying.hu.entity.ResultModel;
import com.baiying.hu.entity.User;
import com.baiying.hu.entity.UserExample;
import com.baiying.hu.entity.dto.UserLoginDto;
import com.baiying.hu.redis.CommonRedisDao;
import com.baiying.hu.service.UserService;
import com.baiying.hu.util.CookieUtils;
import com.baiying.hu.util.TokenProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created by jmx
 * 2017/9/21.
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonRedisDao redisDao;

    /**
     * 用户注册
     *
     * @param user 用户实体
     * @return resultModel
     */
    @PostMapping("/register")
    public ResultModel register(@RequestBody User user) {
        userService.registerUser(user);
        return new ResultModel(200, JSON.toJSON(Constant.OPERATION_OK));
    }

    @PostMapping("/login")
    public ResultModel login(@RequestBody UserLoginDto record, HttpServletResponse response) throws Exception {
        //登录数据校验
        ValidateService.valid(record);
        //短信校验是否用此用户
        if ("verificationCode".equals(record.getType())) {
            return new ResultModel(200, new User());

        } else { //账号密码数据校验
            UserExample example = new UserExample();
            example.createCriteria().andPhoneEqualTo(record.getPhone()).andPasswordEqualTo(record.getPassWord());
            User user = userService.queryUserByParam(example);
            if (Objects.isNull(user)) {
                return new ResultModel(400, "用户名或密码错误");
            } else {
                //生成token
                String token = TokenProcessor.getInstance().generateToken(String.valueOf(record.getPhone()), true);
//                redisDao.cacheValue("token",token);
                user.setToken(token);
                userService.updateUserById(user);
                CookieUtils.setTokenToCookie(response, token);
                return new ResultModel(200, user);
            }
        }
    }

}
