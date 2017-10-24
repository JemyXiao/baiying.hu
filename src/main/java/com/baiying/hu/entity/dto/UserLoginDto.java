package com.baiying.hu.entity.dto;

import com.baiying.hu.common.validate.DV;
import com.baiying.hu.common.validate.RegexType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by jmx
 * 2017/9/22.
 */
@Data
@ApiModel(value = "登錄实体类")
public class UserLoginDto {
    @DV(description = "用户手机", regexType = RegexType.PHONENUMBER)
    @ApiModelProperty(value = "用户手机", required = true)
    private long phone;
    @DV(description = "密码")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "登录類型")
    private String type = "account";
}
