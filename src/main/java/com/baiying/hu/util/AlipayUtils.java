package com.baiying.hu.util;

import com.alipay.api.domain.AlipayTradePagePayModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by longwu on 17/11/5.
 */
public class AlipayUtils {

    private final static Logger log = LoggerFactory.getLogger(AlipayUtils.class);

    /**
     * 转化阿里支付回调参数为map
     * @param request
     * @return map
     */
    public static Map<String, String> toMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        return params;
    }


    /**
     * 支付请求参数校验
     * @param model 支付请求参数实体
     */
    public static void validateParams(AlipayTradePagePayModel model) {

        Objects.requireNonNull(model.getOutTradeNo(), "订单号必须不为空");
        Objects.requireNonNull(model.getTotalAmount(), "订单总金额必须不为空");
        Objects.requireNonNull(model.getSubject(), "订单标题必须不为空");
        if (model.getOutTradeNo().length() > 64) {
            throw new IllegalArgumentException("商户订单号必须64个字符以内");
        }
        if (!"FAST_INSTANT_TRADE_PAY".equals(model.getProductCode())) {
            throw new IllegalArgumentException("销售产品码目前仅支持FAST_INSTANT_TRADE_PAY");
        }
        Double totalAmount = Double.parseDouble(model.getTotalAmount());
        if (totalAmount < 0.01 || totalAmount > 100000000) {
            throw new IllegalArgumentException("订单总金额取值范围[0.01,100000000]");
        }
        if (model.getOutTradeNo().length() > 256) {
            throw new IllegalArgumentException("订单标题必须256个字符以内");
        }
    }



}
