package com.baiying.hu.entity.dto;

import com.baiying.hu.common.validate.DV;
import com.baiying.hu.common.validate.RegexType;
import lombok.Data;

/**
 * Created by jmx
 * 2017/9/22.
 */
@Data
public class UserLoginDto {
    @DV(description = "用户名", regexType = RegexType.PHONENUMBER)
    private long phone;
    @DV(description = "密码")
    private String passWord;
    private String type;
}
