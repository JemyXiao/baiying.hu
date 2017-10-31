package com.baiying.hu.service.impl;

import com.baiying.hu.entity.Business;
import com.baiying.hu.entity.BusinessExample;
import com.baiying.hu.mapper.BusinessMapper;
import com.baiying.hu.service.BusinessService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

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
    public List<Business> queryAllBusiness(String name) {
        BusinessExample example = new BusinessExample();
        if (!StringUtils.isEmpty(name)) {
            example.createCriteria().andBusinessNameLike("%" + name + "%");
        }
        List<Business> dataList = businessMapper.selectByExample(example);
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
        //查询第三级列表
        List<Business> businessList = businessMapper.queryBusinessByName(name);
        List<Business> parent1 = resultBusiness(businessList);
        List<Business> newBusiness = resultBusiness(parent1);
        return newBusiness;

    }

    private List<Business> resultBusiness(List<Business> businessList) {
        Map<Long, List<Business>> map = new HashMap<>();
        List<Business> businesses;
        List<Business> result = Lists.newArrayList();
        for (Business business : businessList) {
            Business parent = businessMapper.selectByPrimaryKey(business.getParentId());
            if (map.get(parent.getId()) == null) {
                businesses = Lists.newArrayList();
                businesses.add(business);
                map.put(parent.getId(), businesses);
            } else {
                map.get(parent.getId()).add(business);
            }

        }
        for (Map.Entry<Long, List<Business>> entry : map.entrySet()) {
            Business business = businessMapper.selectByPrimaryKey(entry.getKey());
            business.setChildren(entry.getValue());
            result.add(business);
        }
        return result;
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
