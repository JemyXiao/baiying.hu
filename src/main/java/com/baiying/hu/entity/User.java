package com.baiying.hu.entity;

import com.baiying.hu.common.validate.DV;
import com.baiying.hu.common.validate.RegexType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "用户实体类")
public class User {
    private Long id;
    @ApiModelProperty(value = "用户真实姓名")
    private String realName;
    @DV(description = "密码")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    private String token;

    private Date createdAt;

    private Date updatedAt;
    @DV(description = "手机号", regexType = RegexType.PHONENUMBER)
    @ApiModelProperty(value = "手机号", required = true)
    private Long phone;
    @ApiModelProperty(value = "用户昵称", required = true)
    private String nickName;
    @ApiModelProperty(value = "归属城市", required = true)
    private String cityId;
    private String status;
    @DV(description = "验证码", maxLength = 6, regexType = RegexType.NUMBER)
    @ApiModelProperty(value = "验证码", required = true)
    private int verificationCode;
    @ApiModelProperty(value = "邀请码")
    private String inviteCode;
    @ApiModelProperty(value = "是否申请服务商")
    private Integer serviceProvider;
    @ApiModelProperty(value = "是否申请企业认证")
    private Integer companyAuthenticate;
    @ApiModelProperty(value = "公司名称", required = true)
    private String companyName;
    @ApiModelProperty(value = "服务类型", required = true)
    private String businessId;
    @ApiModelProperty(value = "身份证照片", required = true)
    private String idCardPhoto;
    @ApiModelProperty(value = "身份证号码", required = true)
    private String idCard;
    @ApiModelProperty(value = "营业执照照片", required = true)
    private String businessLicensePhoto;

}