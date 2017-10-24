package com.baiying.hu.web;

import com.baiying.hu.config.Constant;
import com.baiying.hu.entity.Consultant;
import com.baiying.hu.entity.ConsultantExample;
import com.baiying.hu.entity.ResultModel;
import com.baiying.hu.entity.vo.ConsultantResult;
import com.baiying.hu.service.ConsultantService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jmx
 * 2017/9/28.
 */
@Slf4j
@RestController
@RequestMapping("/consultant")
@Api(value = "顾问相关api", description = "顾问相关api")
public class ConsultantController {
    @Autowired
    private ConsultantService consultantService;

    /**
     * 获取顾问下拉框列表
     *
     * @return
     */
    @GetMapping("/getConsultantList")
    @ApiOperation(value = "getConsultantList", notes = "顾问列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultModel getConsultantList() {
        return new ResultModel(200, consultantService.getConsultantList());
    }

    /**
     * 获取顾问展示列表
     */
    @ApiOperation(value = "呼顾问列表", notes = "呼顾问列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = ConsultantResult.class
    )
    @GetMapping("/getConsultantShowList")
    public ResultModel getConsultantShowList(@ApiParam @RequestParam(required = false) String businessType, String actualName, int pageSize, int pageNumber) {
        ConsultantExample example = new ConsultantExample();
        ConsultantExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo((byte) 1);
        criteria.andRoleEqualTo(Constant.CONS);
        if (!StringUtils.isEmpty(businessType)) {
            criteria.andBusinessIdEqualTo(businessType);
        }
        if (!StringUtils.isEmpty(actualName)) {
            criteria.andActualNameEqualTo(actualName);
        }
        PageInfo pageInfo = PageHelper.startPage(pageNumber, pageSize).doSelectPageInfo(() -> consultantService.getConstantBySelect(example));
        Map<String, Object> mapResult = new LinkedHashMap<>();
        mapResult.put("rows", pageInfo.getList());
        mapResult.put("total", pageInfo.getTotal());
        return new ResultModel(200, mapResult);
    }

    /**
     * 顾问详情
     */
    @ApiOperation(value = "顾问详情", notes = "顾问详情", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Consultant.class
    )
    @GetMapping("/getConsultantDetail")
    public ResultModel getConsultantDetail(@ApiParam @RequestParam long id) {
        return new ResultModel(200, consultantService.getConsultantDetail(id));
    }
}
