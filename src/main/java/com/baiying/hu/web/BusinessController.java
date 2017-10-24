package com.baiying.hu.web;

import com.baiying.hu.entity.Business;
import com.baiying.hu.entity.ResultModel;
import com.baiying.hu.service.BusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jmx
 * 2017/9/28.
 */
@Slf4j
@RestController
@RequestMapping("/business")
@Api(value = "服务相关api", description = "服务相关api")
public class BusinessController {
    private final BusinessService businessService;

    @Autowired
    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @ApiOperation(value = "获取服务列表", notes = "获取服务列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Business.class
    )
    @ApiResponse(code = 200, message = "Invalid user supplied", response = Business.class)
    @GetMapping("/getAll")
    public ResultModel getAllBusiness() {
        return new ResultModel(200, businessService.queryAllBusiness());
    }

    @ApiOperation(value = "getBusinessById", notes = "获取服务列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Business.class
    )
    @GetMapping("/getDetailById")
    public ResultModel getBusinessDetail(@ApiParam @RequestParam("id") long id) {
        return new ResultModel(200, businessService.getBusinessById(id));
    }

    @GetMapping("/getHotBusiness")
    @ApiOperation(value = "热门服务列表", notes = "热门服务列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Business.class
    )
    public ResultModel getHotBusiness() {
        return new ResultModel(200, businessService.queryHotBusiness());
    }
    @ApiOperation(value = "服务搜索列表", notes = "服务搜索列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Business.class
    )
    @GetMapping("/searchBusinessByName")
    public ResultModel searchBusinessByName(@ApiParam @RequestParam(required = false) String name) {
        return new ResultModel(200, businessService.queryBusinessByName(name));
    }


}
