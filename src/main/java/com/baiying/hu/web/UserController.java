package com.baiying.hu.web;

import com.alibaba.fastjson.JSON;
import com.baiying.hu.VerificationUser;
import com.baiying.hu.common.validate.ValidateService;
import com.baiying.hu.config.Constant;
import com.baiying.hu.entity.*;
import com.baiying.hu.entity.dto.UserLoginDto;
import com.baiying.hu.entity.dto.UserRegisterDto;
import com.baiying.hu.enums.LoginTypeEnum;
import com.baiying.hu.mapper.CheckPhoneCodeMapper;
import com.baiying.hu.redis.CommonRedisDao;
import com.baiying.hu.service.ProblemService;
import com.baiying.hu.service.ProjectService;
import com.baiying.hu.service.SendSmsService;
import com.baiying.hu.service.UserService;
import com.baiying.hu.util.CookieUtils;
import com.baiying.hu.util.TokenProcessor;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by jmx
 * 2017/9/21.
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "用户相关api", description = "用户相关api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private SendSmsService smsService;

    @Autowired
    private CheckPhoneCodeMapper codeMapper;
    @Autowired
    private VerificationUser verificationUser;

    @Autowired
    private CommonRedisDao redisDao;

    /**
     * 用户注册
     *
     * @param user 用户实体
     * @return resultModel
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/register")
    public ResultModel register(@RequestBody User user) throws Exception {
        ValidateService.valid(user);
        //校验注册用户数据
        verificationUser.variateUser(user);

        int count = userService.countRegister(user.getPhone());
        if (count > 0) {
            return new ResultModel(400, JSON.toJSON(Constant.USER_HAS_REGISTER));
        }
        CheckPhoneCode flag = checkVerifactionCode(user);
        if (Objects.isNull(flag)) {
            return new ResultModel(400, JSON.toJSON("验证码不正确"));
        }
        flag.setIsUse(1);
        codeMapper.updateByPrimaryKey(flag);
        userService.registerUser(user);
        return new ResultModel(200, JSON.toJSON(Constant.OPERATION_OK));
    }

    private CheckPhoneCode checkVerifactionCode(User user) {
        long phone = user.getPhone();
        int code = user.getVerificationCode();
        CheckPhoneCodeExample example = new CheckPhoneCodeExample();
        example.createCriteria().andPhoneEqualTo(phone).andCheckCodeEqualTo(code).andIsUseEqualTo(0);
        List<CheckPhoneCode> list = codeMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @ApiOperation(value = "selectUserInfo", notes = "获取用户信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = User.class)
    @GetMapping("/selectUserInfo")
    public ResultModel selectUserInfo(HttpServletRequest request) {
        User user = userService.selectUserInfo(request);
        return new ResultModel(200, user);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PostMapping("/login")
    public ResultModel login(@RequestBody UserLoginDto record, HttpServletResponse response) throws Exception {
        //登录数据校验
        ValidateService.valid(record);
        UserExample example = new UserExample();
        User user;
        //短信校验是否用此用户
        if (LoginTypeEnum.MOBILE.getType().equals(record.getType())) {
            example.createCriteria().andPhoneEqualTo(record.getPhone());
            try {
                user = userService.queryUserByParam(example);
            } catch (Exception e) {
                return new ResultModel(400, "用户名或密码错误");
            }
            return new ResultModel(200, user);

        } else { //账号密码数据校验
            example.createCriteria().andPhoneEqualTo(record.getPhone()).andPasswordEqualTo(record.getPassword());
            try {
                user = userService.queryUserByParam(example);
            } catch (Exception e) {
                return new ResultModel(400, "用户名或密码错误");
            }

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

    /**
     * 个人信息我的项目
     */
    @GetMapping("/getProInfoBySelf")
    @ApiOperation(value = "我的項目列表", notes = "我的項目列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Project.class
    )
    public ResultModel getProInfoBySelf(HttpServletRequest request) {
        User user = userService.selectUserInfo(request);
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        ProjectExample example = new ProjectExample();
        example.createCriteria().andPublisherIdEqualTo(user.getId());
        PageInfo pageInfo = PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(() -> projectService.getProjectListBySelect(example));
        Map<String, Object> mapResult = new LinkedHashMap<>();
        mapResult.put("rows", pageInfo.getList());
        mapResult.put("total", pageInfo.getTotal());
        return new ResultModel(200, mapResult);
    }

    /**
     * 个人信息我的问题
     */
    @GetMapping("/getProblemBySelf")
    @ApiOperation(value = "我的提問列表", notes = "我的提問列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Problem.class
    )
    public ResultModel getProblemBySelf(HttpServletRequest request) {
        User user = userService.selectUserInfo(request);
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        ProblemExample example = new ProblemExample();
        example.createCriteria().andCreateIdEqualTo(user.getId());
        PageInfo pageInfo = PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(() -> problemService.queryAllProblem(example));
        Map<String, Object> mapResult = new LinkedHashMap<>();
        mapResult.put("rows", pageInfo.getList());
        mapResult.put("total", pageInfo.getTotal());
        return new ResultModel(200, mapResult);
    }

    /**
     * 申請企业用户
     */
    @PostMapping("/companyAuthenticate")
    @ApiOperation(value = "申请企业认证", notes = "申请企业认证", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel companyAuthenticate(@RequestBody UserRegisterDto dto, HttpServletRequest request) {
        User user = userService.selectUserInfo(request);
        dto.setId(user.getId());
        dto.setCompanyAuthenticate(1);
        userService.companyRegister(dto);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 申请服务商认证
     */
    @PostMapping("/serviceAuthenticate")
    @ApiOperation(value = "申请服务商认证", notes = "申请服务商认证", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel serviceAuthenticate(@RequestBody UserRegisterDto dto, HttpServletRequest request) {
        User user = userService.selectUserInfo(request);
        dto.setId(user.getId());
        dto.setServiceProvider(1);
        userService.companyRegister(dto);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 短信发送接口
     */
    @GetMapping("/sendSms")
    public ResultModel sendSms(@RequestParam long phone, HttpServletRequest request) {
        boolean flag = smsService.sendSms(phone, request);
        return new ResultModel(200, flag);
    }
}
