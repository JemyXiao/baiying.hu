package com.baiying.hu.service.impl;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baiying.hu.common.MessageSend;
import com.baiying.hu.entity.CheckPhoneCode;
import com.baiying.hu.entity.CheckPhoneCodeExample;
import com.baiying.hu.mapper.CheckPhoneCodeMapper;
import com.baiying.hu.service.SendSmsService;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by jmx
 * 2017/10/20
 */
@Transactional
@Service
@Log4j
public class SendSmsServiceImpl implements SendSmsService {
    @Autowired
    private CheckPhoneCodeMapper codeMapper;

    @Override
    public boolean sendSms(long phone, HttpServletRequest request) {
        //ip地址
        String ipAddress = request.getRemoteAddr();
        //当前时间
        LocalDateTime now = LocalDateTime.now().plusMinutes(5);
        Date data = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        CheckPhoneCodeExample example = new CheckPhoneCodeExample();
        example.createCriteria().andPhoneEqualTo(phone)
                .andIsUseEqualTo(0);
        List<CheckPhoneCode> list = codeMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            log.info(list);
            return false;
        } else {
            //调用发送短信接口
            String code = MessageSend.createRandomVcode();
            try {
                SendSmsResponse response = MessageSend.sendSms(String.valueOf(phone), code);
                if (response.getCode() != null && response.getCode().equals("OK")) {
                    log.info("短信发送成功");
                    CheckPhoneCode checkPhoneCode = new CheckPhoneCode();
                    checkPhoneCode.setExpireAt(data);
                    checkPhoneCode.setPhone(phone);
                    checkPhoneCode.setIsUse(0);
                    checkPhoneCode.setIp(ipAddress);
                    checkPhoneCode.setCheckCode(Integer.valueOf(code));
                    codeMapper.insert(checkPhoneCode);
                    return true;
                } else {
                    log.info(response.getCode());
                    return false;
                }

            } catch (ClientException e) {
                log.error("调用短信接口异常", e);
                return false;
            }
        }
    }
}
