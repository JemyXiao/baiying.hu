package com.baiying.hu.mapper;

import com.baiying.hu.entity.Consultant;
import com.baiying.hu.entity.ConsultantExample;
import com.baiying.hu.entity.vo.ConsultantResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ConsultantMapper {
    int countByExample(ConsultantExample example);

    int deleteByExample(ConsultantExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Consultant record);

    int insertSelective(Consultant record);

    List<ConsultantResult> selectByExample(ConsultantExample example);

    ConsultantResult selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Consultant record, @Param("example") ConsultantExample example);

    int updateByExample(@Param("record") Consultant record, @Param("example") ConsultantExample example);

    int updateByPrimaryKeySelective(Consultant record);

    int updateByPrimaryKey(Consultant record);

    List<Map> getConsultantList();

    Map<String,String> getConsultantName(long id);
}