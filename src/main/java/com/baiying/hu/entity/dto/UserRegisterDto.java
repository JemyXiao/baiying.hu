package com.baiying.hu.entity.dto;

import com.baiying.hu.common.validate.DV;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by jmx
 * 2017/10/18.
 */
@Data
@ApiModel(value = "企业认证申请dto")
public class UserRegisterDto {
    private Long id;
    @DV(description = "真实姓名")
    @ApiModelProperty(value = "真实姓名", required = true)
    private String realName;
    @ApiModelProperty(value = "公司名称", required = true)
    private String companyName;
    @DV(description = "服务类型")
    @ApiModelProperty(value = "服务类型", required = true)
    private String businessId;
    @DV(description = "身份证照片")
    @ApiModelProperty(value = "身份证照片", required = true)
    private String idCardPhoto;
    @DV(description = "身份证号码")
    @ApiModelProperty(value = "身份证号码", required = true)
    private String idCard;
    @ApiModelProperty(value = "营业执照照片", required = true)
    private String businessLicensePhoto;
    private String status;
    private Integer serviceProvider;
    @ApiModelProperty(value = "是否申请企业认证")
    private Integer companyAuthenticate;

    //添加公司名称，服务类型，真实姓名，身份证号，身份证照片字段
}
