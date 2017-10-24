package com.baiying.hu.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jmx
 * 2017/10/20
 */
public interface SendSmsService {
    boolean sendSms(long phone, HttpServletRequest request);
}
