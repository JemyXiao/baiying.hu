package com.baiying.hu.web;

import com.alibaba.fastjson.JSON;
import com.baiying.hu.common.aop.Operation;
import com.baiying.hu.common.validate.ValidateService;
import com.baiying.hu.config.Constant;
import com.baiying.hu.entity.Problem;
import com.baiying.hu.entity.ProblemExample;
import com.baiying.hu.entity.ResultModel;
import com.baiying.hu.entity.User;
import com.baiying.hu.entity.dto.ProblemDto;
import com.baiying.hu.entity.vo.ProblemResult;
import com.baiying.hu.service.ProblemService;
import com.baiying.hu.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jmx
 * 2017/9/28.
 */
@Slf4j
@RestController
@RequestMapping("/problem")
@Api(value = "Problem Api", description = "问题相关api")
public class ProblemController {
    private final ProblemService problemService;
    private final UserService userService;

    @Autowired
    public ProblemController(ProblemService problemService, UserService userService) {
        this.problemService = problemService;
        this.userService = userService;
    }

    /**
     * 查询问题列表
     */

    @ApiOperation(value = "getProblemList", notes = "获取问题列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Problem.class
    )
    @ApiResponse(code = 200, message = "Invalid user supplied", response = Problem.class)
    @GetMapping("/queryAllProblem")
    public ResultModel queryAllProblem(@ApiParam @RequestParam(value = "serviceType", required = false) String serviceType, @RequestParam(value = "title", required = false) String title, int pageSize, int pageNumber) {
        ProblemExample example = new ProblemExample();
        ProblemExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(serviceType)) {
            criteria.andBusinessIdEqualTo(serviceType);
        }
        if (!StringUtils.isEmpty(title)) {
            criteria.andProblemTitleEqualTo(title);
        }
        PageInfo pageInfo = PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(() -> problemService.queryAllProblem(example));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return new ResultModel(200, JSON.toJSON(map));
    }

    /**
     * 提问题
     */
    @PostMapping("/createProblem")
    @ApiOperation(value = "createProblem", notes = "提问题", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel createProblem(@RequestBody ProblemDto dto, HttpServletRequest request) throws Exception {
        //登录数据校验
        ValidateService.valid(dto);
        User user = userService.selectUserInfo(request);
        dto.setCreateId(user.getId());
        dto.setCreateName(user.getNickName());
        problemService.createProblem(dto);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 问题致为已解决
     */
    @GetMapping("/resolvedProblem")
    public ResultModel resolvedProblem(@ApiParam @RequestParam long id) {
        problemService.resolvedProblem(id);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 问题详情
     */
    @ApiOperation(value = "/queryProblemDetail", notes = "问题详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ProblemResult.class
    )
    @GetMapping("/queryDetailById")
    @Operation(name = "visit")
    public ResultModel queryProblemDetail(@ApiParam @RequestParam long id) {
        return new ResultModel(200, problemService.queryDetailById(id));
    }
}
