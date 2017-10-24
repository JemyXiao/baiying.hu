package com.baiying.hu.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@ApiModel(value = "顾问响应实体类")
public class ConsultantResult {
    @ApiModelProperty(value = "顾问id")
    private Long id;
    @ApiModelProperty(value = "顾问联系方式")
    private String phone;
    @ApiModelProperty(value = "顾问归属城市")
    private String cityId;
    @ApiModelProperty(value = "顾问真实姓名")
    private String actualName;
    @ApiModelProperty(value = "顾问身份证号")
    private String idCard;
    @ApiModelProperty(value = "顾问身份证照片")
    private String idCardPhoto;
    @ApiModelProperty(value = "顾问自我介绍")
    private String selfIntroduction;
    @ApiModelProperty(value = "顾问评分")
    private String score;
    @ApiModelProperty(value = "服务类型")
    private String businessId;
    private List<String> businessName;
    private byte status;
    private String role;
    @ApiModelProperty(value = "头像照片")
    private String headPhoto;
}