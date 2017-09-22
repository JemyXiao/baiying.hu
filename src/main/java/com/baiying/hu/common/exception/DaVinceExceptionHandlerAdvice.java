package com.baiying.hu.common.exception;

import com.alibaba.fastjson.JSON;

import com.baiying.hu.entity.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by jmx on 17/6/20.
 * 全局异常控制类
 */
@RestControllerAdvice
public class DaVinceExceptionHandlerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(DaVinceExceptionHandlerAdvice.class);

    //    @Autowired
//    private MailSenderHelper mailSenderHelper;
    @ExceptionHandler(value = {Exception.class})
    public ResultModel exception(Exception ex) {
        logger.error("error happens", ex);
        ResultModel result = new ResultModel(400, ex.getMessage());
//        MailContract mailContract = new MailContract();
//        mailContract.setTo(new String[]{"jinming.xiao@ele.me"});
//        mailSenderHelper.sendMail(mailContract,"erro");
        return result;
    }
}
