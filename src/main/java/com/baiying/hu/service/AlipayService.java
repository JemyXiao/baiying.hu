package com.baiying.hu.service;

import com.alipay.api.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by longwu on 17/11/4.
 * 支付宝支付接口
 */
public interface AlipayService {

    /**
     * 统一收单下单并支付页面接口(电脑网站支付)
     * @param model
     * @return form表单
     */
    String tradePagePay(AlipayTradePagePayModel model);

    /**
     * 支付宝同步回调接口
     * @param map
     * @return
     */
    String alipayReturn(Map<String, String> map);

    /**
     * 支付宝异步回调接口
     * @param request
     * @return
     */
    String alipayNotify(Map<String, String> map);

    /**
     * 统一收单交易退款接口
     * @param model
     * @return
     */
    boolean tradeRefund(AlipayTradeRefundModel model);

    /**
     * 统一收单交易退款查询接口
     * @param model
     * @return
     */
    boolean tradeFastpayRefundQuery(AlipayTradeFastpayRefundQueryModel model);

    /**
     * 统一收单线下交易查询接口
     * @param model
     * @return
     */
    boolean tradeQuery(AlipayTradeQueryModel model);

    /**
     * 单笔转账到支付宝账户接口
     * @param model
     * @return
     */
    boolean FundTransToaccountTransfer(AlipayFundTransToaccountTransferModel model);

    /**
     * 查询转账订单接口
     * @param model
     * @return
     */
    boolean FundTransOrderQuery(AlipayFundTransOrderQueryModel model);

}
