package com.baiying.hu.entity;

import com.baiying.hu.common.validate.DV;
import com.baiying.hu.common.validate.RegexType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "项目实体类")
public class Project {
    @ApiModelProperty(value = "id")
    private Long id;
    @DV(description = "项目名称")
    @ApiModelProperty(value = "项目名称", required = true)
    private String projectName;
    @DV(description = "项目详情")
    @ApiModelProperty(value = "项目详情", required = true)
    private String projectDetail;
    @ApiModelProperty(value = "项目发布者id")
    private Long publisherId;
    @ApiModelProperty(value = "项目状态")
    private String status;
    @DV(description = "项目服务类型")
    @ApiModelProperty(value = "项目服务类型", required = true)
    private String businessId;

    private Date createdAt;

    private Date updatedAt;
    @DV(description = "联系方式", regexType = RegexType.PHONENUMBER)
    @ApiModelProperty(value = "联系方式", required = true)
    private String phone;
    @DV(description = "城市id")
    @ApiModelProperty(value = "城市id", required = true)
    private String cityId;
    @ApiModelProperty(value = "项目预算", required = true)
    private String budget;
    @ApiModelProperty(value = "项目价格", required = true)
    private String price;
    private Integer projectIndex;

    private Long consultantId;

    private String consultantName;

    private Long serviceId;
}