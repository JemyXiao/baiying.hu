package com.baiying.hu.service.impl;

import com.baiying.hu.enums.UserStatusEnum;
import com.baiying.hu.service.AdminService;
import com.baiying.hu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jmx
 * 2017/10/23.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserService userService;

    @Override
    public int authorServiceProvider(long id, int status,int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("status", status == 2 ? UserStatusEnum.AUDIT_PASSED.getStatusType() : UserStatusEnum.AUDIT_NOT_PASSED.getStatusType());
        if (type==0) {
            map.put("serviceProvider", status);
        }else {
            map.put("authorCompany", status);
        }
        return userService.authorServiceProvider(map);
    }
}
