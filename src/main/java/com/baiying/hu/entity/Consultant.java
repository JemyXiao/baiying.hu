package com.baiying.hu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "顾问实体类")
public class Consultant {
    @ApiModelProperty(value = "顾问id")
    private Long id;
    @ApiModelProperty(value = "顾问登录密码")
    private String password;
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
    @ApiModelProperty(value = "状态值")
    private Byte status;
    @ApiModelProperty(value = "头像照片")
    private String headPhoto;

    private Date createdAt;

    private Date updatedAt;
    @ApiModelProperty(value = "服务类型")
    private String businessId;
    @ApiModelProperty(value = "排序")
    private Integer projectIndex;
    private String token;
    @ApiModelProperty(value = "角色 admin/cons")
    private String role;
}