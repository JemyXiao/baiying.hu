package com.baiying.hu.service.impl;

import com.baiying.hu.entity.Business;
import com.baiying.hu.entity.BusinessExample;
import com.baiying.hu.mapper.BusinessMapper;
import com.baiying.hu.service.BusinessService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * Created by jmx
 * 2017/9/28
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    private final BusinessMapper businessMapper;

    @Autowired
    public BusinessServiceImpl(BusinessMapper businessMapper) {
        this.businessMapper = businessMapper;
    }

    @Override
    public List<Business> queryAllBusiness() {
        List<Business> dataList = businessMapper.selectByExample(new BusinessExample());
        return getChildren(dataList, 0);
    }

    @Override
    public Business getBusinessById(long id) {
        return businessMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Business> queryHotBusiness() {
        BusinessExample example = new BusinessExample();
        example.setOrderByClause("business_hot desc");
        example.createCriteria().andBusinessHotGreaterThan(0);
        List<Business> businessList = businessMapper.selectByExample(example);
        return getChildren(businessList, 0);
    }

    @Override
    public int insertBusiness(Business business) {

        if (Objects.isNull(business.getId())) {
            businessMapper.insert(business);
        } else {
            businessMapper.updateByPrimaryKeySelective(business);
        }
        return 0;
    }

    @Override
    public int deleteBusiness(long id) {
        return businessMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Business> queryBusinessByName(String name) {
        return businessMapper.queryBusinessByName(name);
    }

    // 递归获取字节点
    private static List<Business> getChildren(List<Business> dataList, long menuId) {
        List<Business> children = Lists.newArrayList();
        for (Business m : dataList) {
            if (m.getParentId() == menuId) {
                m.setChildren(getChildren(dataList, m.getId()));
                children.add(m);
            }
        }
        return children;
    }
}
