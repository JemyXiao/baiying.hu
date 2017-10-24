package com.baiying.hu.service.impl;

import com.baiying.hu.entity.Consultant;
import com.baiying.hu.entity.ConsultantExample;
import com.baiying.hu.entity.vo.ConsultantResult;
import com.baiying.hu.mapper.ConsultantMapper;
import com.baiying.hu.service.ConsultantService;
import com.baiying.hu.util.CookieUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by jmx
 * 2017/9/28.
 */
@Service
public class ConsultantServiceImpl implements ConsultantService {
    @Autowired
    private ConsultantMapper consultantMapper;

    @Override
    public List<Map> getConsultantList() {
        return consultantMapper.getConsultantList();
    }

    @Override
    public List<ConsultantResult> getConstantBySelect(ConsultantExample example) {
        List<ConsultantResult> consultantList = consultantMapper.selectByExample(example);
        for (ConsultantResult consultant : consultantList) {
            consultant.setBusinessName(turnConsultant(consultant.getBusinessId()));
        }
        return consultantList;
    }

    private List<String> turnConsultant(String businessId) {
        List<String> businessName = Lists.newArrayList();
        if (!StringUtils.isEmpty(businessId)) {
            String[] businessArray = businessId.split("/");
            for (String string : businessArray) {
                String test = string.substring(string.lastIndexOf(",") + 1, string.length());
                Map<String, String> map = consultantMapper.getConsultantName(Long.valueOf(test));
                StringBuilder sb = new StringBuilder();
                for (String in : map.keySet()) {
                    String str = map.get(in);//得到每个key多对用value的值
                    if (!StringUtils.isEmpty(str)) {
                        sb.append(str).append("/");
                    }
                }
                businessName.add(sb.toString().substring(0, sb.toString().lastIndexOf("/")));
            }
        }
        return businessName;
    }

    @Override
    public int updateConsultantById(Consultant consultant) {
        return consultantMapper.updateByPrimaryKeySelective(consultant);
    }

    @Override
    public int insert(Consultant consultant) {
        return consultantMapper.insert(consultant);
    }

    @Override
    public ConsultantResult getConsultantDetail(long id) {
        ConsultantResult consultantResult = consultantMapper.selectByPrimaryKey(id);
        consultantResult.setBusinessName(turnConsultant(consultantResult.getBusinessId()));
        return consultantResult;
    }

    @Override
    public int consultantCount(ConsultantExample example) {
        return consultantMapper.countByExample(example);
    }

    @Override
    public ConsultantResult getConsultantInfo(HttpServletRequest request) {
        String token = CookieUtils.getAdminCookieValue(request, "ADMIN_TOKEN");
        ConsultantExample example = new ConsultantExample();
        example.createCriteria().andTokenEqualTo(token);
        return getConstantBySelect(example).get(0);
    }

    @Override
    public int delConsultantById(Long id) {
        return consultantMapper.deleteByPrimaryKey(id);
    }
}
