package com.baiying.hu.service;


import com.baiying.hu.entity.RecruitCity;

import java.util.List;

/**
 * Created by jmx
 * 2017/6/24.
 */
public interface CityService {

    List<RecruitCity> getAllCity();

    int insert(RecruitCity record);

    int fail(Long id);

    int update(RecruitCity record);
}
