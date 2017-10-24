package com.baiying.hu.common.intercrptor;

import com.alibaba.fastjson.JSON;
import com.baiying.hu.entity.Consultant;
import com.baiying.hu.entity.ConsultantExample;
import com.baiying.hu.entity.User;
import com.baiying.hu.entity.UserExample;
import com.baiying.hu.entity.vo.ConsultantResult;
import com.baiying.hu.mapper.ConsultantMapper;
import com.baiying.hu.mapper.UserMapper;
import com.baiying.hu.util.CookieUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by jmx
 * 2017/7/24.
 */
@Slf4j
@Component
public class AdminHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ConsultantMapper consultantMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equalsIgnoreCase("OPTIONS") || request.getRequestURI().equals("/swagger-resources") || request.getRequestURI().equals("/v2/api-docs")) {
            return true;
        }
        String token = CookieUtils.getAdminCookieValue(request, "ADMIN_TOKEN");
        if (token == null || StringUtils.isEmpty(token)) {
            responseContent(response, "COOKIE_IS_NULL", "token为空");
            return false;
        }
        if (consultantMapper == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            consultantMapper = (ConsultantMapper) factory.getBean("consultantMapper");
        }
        ConsultantExample example = new ConsultantExample();
        example.createCriteria().andTokenEqualTo(token);
        List<ConsultantResult> consultantList = consultantMapper.selectByExample(example);

        if (Objects.isNull(consultantList) || consultantList.isEmpty()) {
            responseContent(response, "400", "用户不存在");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
    }

    private void responseContent(HttpServletResponse httpServletResponse, String code, String message) {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("content-type", "application/json;charset=UTF-8");
        ServletOutputStream outputStream;
        try {
            outputStream = httpServletResponse.getOutputStream();
            Map<String, String> map = new HashMap<>();
            map.put("code", code);
            map.put("message", message);
            outputStream.write(JSON.toJSONString(map).getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.info("outputStream exception.", e);
        }
    }
}
