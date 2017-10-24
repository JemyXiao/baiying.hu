package com.baiying.hu.service.impl;

import com.baiying.hu.common.exception.DaVinceValidationException;
import com.baiying.hu.entity.RecruitCity;
import com.baiying.hu.mapper.RecruitCityMapper;
import com.baiying.hu.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Created by jmx on 2017/6/24.
 */
@Service
@Transactional
public class CityServiceImpl implements CityService {
    @Autowired
    private RecruitCityMapper cityMapper;

    @Override
    public List<RecruitCity> getAllCity() {
        return cityMapper.selectAllCity();
    }

    @Override
    public int insert(RecruitCity record) {

        RecruitCity city = cityMapper.selectCityBySelect(new RecruitCity(record.getCityName()));

        if (!Objects.isNull(city)) {
            throw new DaVinceValidationException("城市已经存在，请修改");
        }
        long cityCode = cityMapper.maxCityCode();

        record.setCityCode(cityCode);

        return cityMapper.insert(record);
    }

    @Override
    public int fail(Long id) {
        return cityMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(RecruitCity record) {
        return cityMapper.updateByPrimaryKeySelective(record);
    }

}
