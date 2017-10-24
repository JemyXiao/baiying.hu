package com.baiying.hu.web;

import com.baiying.hu.common.aop.Operation;
import com.baiying.hu.config.Constant;
import com.baiying.hu.entity.ResultModel;
import com.baiying.hu.entity.User;
import com.baiying.hu.entity.dto.NewsDto;
import com.baiying.hu.entity.vo.ConsultantResult;
import com.baiying.hu.mapper.NewsMapper;
import com.baiying.hu.service.NewsService;
import com.baiying.hu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jmx
 * 2017/9/28.
 */
@Slf4j
@RestController
@RequestMapping("/news")
@Api(value = "News Api", description = "消息相关api")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;

    @PostMapping("/insertNews")
    @ApiOperation(value = "问题消息添加", notes = "问题消息添加", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel insertNews(@RequestBody NewsDto dto, HttpServletRequest request) {
        User user = userService.selectUserInfo(request);
        dto.setReplyId(user.getId());
        newsService.insertNewsByUser(dto);
        return new ResultModel(200, Constant.OPERATION_OK);
    }
}
