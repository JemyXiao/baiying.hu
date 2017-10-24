package com.baiying.hu.web;
import com.baiying.hu.entity.Business;
import com.baiying.hu.entity.RecruitCity;
import com.baiying.hu.entity.ResultModel;
import com.baiying.hu.service.CityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jmx on 17/6/18.
 *
 */
@RequestMapping("/city")
@RestController
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    @RequestMapping(value = "/getAllCity", method = RequestMethod.GET)
    @ApiOperation(value = "getAllCity", notes = "城市列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = RecruitCity.class
    )
    public ResultModel getAllCity() {
        List<RecruitCity> recruitCities = cityService.getAllCity();
        return new ResultModel(200, recruitCities);
    }
}
