package com.baiying.hu;

import com.baiying.hu.common.exception.DaVinceValidationException;
import com.baiying.hu.common.validate.ValidateService;
import com.baiying.hu.entity.User;
import com.baiying.hu.entity.dto.UserRegisterDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by jmx
 * 2017/10/24
 */
@Component
public class VerificationUser {
    public void variateUser(User user) throws Exception {
        UserRegisterDto dto = new UserRegisterDto();
        BeanUtils.copyProperties(user, dto);
        //如果申请服务商
        if (user.getServiceProvider() == 1) {
            ValidateService.valid(dto);
        }
        if (user.getCompanyAuthenticate() == 1) {
            if (StringUtils.isEmpty(dto.getCompanyName()) || StringUtils.isEmpty(dto.getBusinessLicensePhoto())) {
                throw new DaVinceValidationException("企业名称或营业执照不可为空");
            }
        }
    }
}
