package com.baiying.hu.entity.dto;

import com.baiying.hu.common.validate.DV;
import com.baiying.hu.common.validate.RegexType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by jmx
 * 2017/10/9.
 */
@Data
@ApiModel(value = "完善顾问信息")
public class ConsultantDto {
    @DV(description = "联系方式", regexType = RegexType.PHONENUMBER)
    @ApiModelProperty(value = "顾问联系方式")
    private String phone;
    @DV(description = "归属城市")
    @ApiModelProperty(value = "顾问归属城市")
    private String cityId;
    @DV(description = "真实姓名")
    @ApiModelProperty(value = "顾问真实姓名")
    private String actualName;
    @DV(description = "头像照片")
    @ApiModelProperty(value = "头像照片")
    private String headPhoto;
    @DV(description = "身份证号")
    @ApiModelProperty(value = "顾问身份证号")
    private String idCard;
    @DV(description = "身份证照片")
    @ApiModelProperty(value = "顾问身份证照片")
    private String idCardPhoto;
    @DV(description = "自我介绍")
    @ApiModelProperty(value = "顾问自我介绍")
    private String selfIntroduction;
    @DV(description = "服务类型")
    @ApiModelProperty(value = "服务类型")
    private String businessId;
    private String token;
}
