package com.baiying.hu.service;

import com.baiying.hu.entity.Business;

import java.util.List;

/**
 * Created by jmx
 * 2017/9/28.
 */
public interface BusinessService {

    List<Business> queryAllBusiness(String name);

    Business getBusinessById(long id);

    List<Business> queryHotBusiness();

    int insertBusiness(Business business);

    int deleteBusiness(long id);

    List<Business> queryBusinessByName(String name);

}
