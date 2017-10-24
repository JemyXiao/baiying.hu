package com.baiying.hu.web;

import com.baiying.hu.common.validate.ValidateService;
import com.baiying.hu.config.Constant;
import com.baiying.hu.entity.*;
import com.baiying.hu.service.ProjectService;
import com.baiying.hu.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jmx
 * 2017/9/28
 */
@Slf4j
@RestController
@RequestMapping("/project")
@Api(value = "Project Api", description = "項目相关api")
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    /**
     * 发布需求
     */
    @PostMapping("/createProject")
    @ApiOperation(value = "createProject", notes = "发布项目需求", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel createProject(@RequestBody Project project, HttpServletRequest request) throws Exception {
        User user = userService.selectUserInfo(request);
        project.setPublisherId(user.getId());
        ValidateService.valid(project);
        projectService.insertProject(project);
        return new ResultModel(200, Constant.OPERATION_OK);
    }

    /**
     * 呼案例数据列表
     */
    @GetMapping("/getSuccessfulCase")
    @ApiOperation(value = "成功案例列表", notes = "成功案例列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Project.class
    )
    public ResultModel getSuccessfulCase(@ApiParam @RequestParam(required = false) String businessType) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(businessType)) {
            criteria.andBusinessIdEqualTo(businessType);
        }
        List<Project> resultList = projectService.getProjectListBySelect(example);
        return new ResultModel(200, resultList);
    }

    @GetMapping("/getProjectDetail")
    @ApiOperation(value = "项目详情", notes = "项目详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Project.class
    )
    public ResultModel getProjectDetail(@ApiParam @RequestParam long id) {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andIdEqualTo(id);
        List<Project> resultList = projectService.getProjectListBySelect(example);
        return new ResultModel(200, resultList.get(0));
    }

}
