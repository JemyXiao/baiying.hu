package com.baiying.hu.mapper;

import com.baiying.hu.entity.ProblemOperator;
import com.baiying.hu.entity.ProblemOperatorExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemOperatorMapper {
    int countByExample(ProblemOperatorExample example);

    int deleteByExample(ProblemOperatorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProblemOperator record);

    int insertSelective(ProblemOperator record);

    List<ProblemOperator> selectByExample(ProblemOperatorExample example);

    ProblemOperator selectByPrimaryKey(@Param("problemId") Long id);

    int updateByExampleSelective(@Param("record") ProblemOperator record, @Param("example") ProblemOperatorExample example);

    int updateByExample(@Param("record") ProblemOperator record, @Param("example") ProblemOperatorExample example);

    int updateByPrimaryKeySelective(ProblemOperator record);

    int updateByPrimaryKey(ProblemOperator record);
}