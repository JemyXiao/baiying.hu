package com.baiying.hu.mapper;

import com.baiying.hu.entity.CheckPhoneCode;
import com.baiying.hu.entity.CheckPhoneCodeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
@Repository
public interface CheckPhoneCodeMapper {

    int updateIsUse(long phone);
    int countByExample(CheckPhoneCodeExample example);

    int deleteByExample(CheckPhoneCodeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CheckPhoneCode record);

    int insertSelective(CheckPhoneCode record);

    List<CheckPhoneCode> selectByExample(CheckPhoneCodeExample example);

    CheckPhoneCode selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CheckPhoneCode record, @Param("example") CheckPhoneCodeExample example);

    int updateByExample(@Param("record") CheckPhoneCode record, @Param("example") CheckPhoneCodeExample example);

    int updateByPrimaryKeySelective(CheckPhoneCode record);

    int updateByPrimaryKey(CheckPhoneCode record);
}