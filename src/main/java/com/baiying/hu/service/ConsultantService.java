package com.baiying.hu.service;

import com.baiying.hu.entity.Consultant;
import com.baiying.hu.entity.ConsultantExample;
import com.baiying.hu.entity.User;
import com.baiying.hu.entity.vo.ConsultantResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by jmx
 * 2017/9/28.
 */
public interface ConsultantService {
    List<Map> getConsultantList();

    List<ConsultantResult> getConstantBySelect(ConsultantExample example);

    int updateConsultantById(Consultant consultant);

    int insert(Consultant consultant);

    ConsultantResult getConsultantDetail(long id);

    int consultantCount(ConsultantExample example);

    ConsultantResult getConsultantInfo(HttpServletRequest request);

    int delConsultantById(Long id);
}
