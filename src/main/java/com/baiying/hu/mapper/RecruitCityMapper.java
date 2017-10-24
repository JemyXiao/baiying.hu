package com.baiying.hu.mapper;


import com.baiying.hu.entity.RecruitCity;
import com.baiying.hu.entity.RecruitCityExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RecruitCityMapper {
    int countByExample(RecruitCityExample example);

    int deleteByExample(RecruitCityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RecruitCity record);

    int insertSelective(RecruitCity record);

    List<RecruitCity> selectByExample(RecruitCityExample example);

    List<RecruitCity> selectAllCity();

    RecruitCity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RecruitCity record, @Param("example") RecruitCityExample example);

    int updateByExample(@Param("record") RecruitCity record, @Param("example") RecruitCityExample example);

    int updateByPrimaryKeySelective(RecruitCity record);

    int updateByPrimaryKey(RecruitCity record);

    RecruitCity selectCityBySelect(RecruitCity record);

    Long maxCityCode();
}