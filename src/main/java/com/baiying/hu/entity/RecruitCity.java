package com.baiying.hu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "城市实体类")
public class RecruitCity {
    @ApiModelProperty(value = "城市id")
    private Long id;
    @ApiModelProperty(value = "城市名称")
    private String cityName;
    @ApiModelProperty(value = "热门城市")
    private Integer hotTop;
    @JSONField(serialize = false)
    private Date createdAt;
    @JSONField(serialize = false)
    private Date updatedAt;
    @ApiModelProperty(value = "城市code")
    private Long cityCode;

    public RecruitCity() {
    }

    public RecruitCity(String cityName) {
        this.cityName = cityName;
    }
}