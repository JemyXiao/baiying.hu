package com.baiying.hu.web;

import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.baiying.hu.service.AlipayService;
import com.baiying.hu.util.AlipayUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * Created by longwu on 17/11/4.
 */
@RestController
@RequestMapping("/alipay")
@Api(value = "支付宝支付api", description = "支付宝支付api")
public class AlipayController {

    @Autowired
    private AlipayService alipayService;


    /**
     * 支付接口
     */
    @RequestMapping(value = "/trade/page/pay")
    public void tradePagePay(HttpServletResponse httpResponse) throws IOException {
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(UUID.randomUUID().toString().replaceAll("-", ""));
        model.setTotalAmount("10.00");
        model.setSubject("支付沙箱测试");
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        String form = alipayService.tradePagePay(model);
        httpResponse.setContentType("text/html;charset=utf-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();

    }

    /**
     * 退款接口
     */
    @RequestMapping(value = "/trade/refund")
    public void tradeRefund(HttpServletResponse httpResponse) throws IOException {
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo("46dc819b1c1b4d3da9e628e73bf6a99d");
        model.setRefundAmount("1.00");
        model.setRefundReason("退款接口测试V2");
        model.setOutRequestNo(UUID.randomUUID().toString().replaceAll("-", ""));
        boolean result = alipayService.tradeRefund(model);
        httpResponse.getWriter().write(String.valueOf(result));
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    /**
     * 转账接口
     */
    @RequestMapping(value = "/trade/transfer")
    public void fundTransToaccountTransfer(HttpServletResponse httpResponse) throws IOException {
        AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
        model.setOutBizNo(UUID.randomUUID().toString().replaceAll("-", ""));
        model.setPayeeType("ALIPAY_LOGONID");
        model.setPayeeAccount("uqelfq8694@sandbox.com");
        model.setAmount("100");
        model.setPayeeRealName("沙箱环境");
        model.setPayerShowName("呼百应");

        boolean result = alipayService.FundTransToaccountTransfer(model);
        httpResponse.getWriter().write(!result ? "转账失败" : "转账成功!");
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }




    /**
     * 支付成功后支付宝同步回调的接口
     */
    @RequestMapping(value = "/callback/return-url")
    public void alipayReturn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> map = AlipayUtils.toMap(request);
        String result = alipayService.alipayReturn(map);
        response.getWriter().write(result);
        response.getWriter().flush();
        response.getWriter().close();
    }

    /**
     * 支付成功后支付宝异步回调的接口
     * 以异步接口为准
     */
    @RequestMapping(value = "/callback/notify-url")
    public void alipayNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> map = AlipayUtils.toMap(request);
        String result = alipayService.alipayNotify(map);
        response.getWriter().write(result);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
