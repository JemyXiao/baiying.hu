package com.baiying.hu.mapper;

import com.baiying.hu.entity.dto.AliFundTransferDto;
import com.baiying.hu.entity.dto.AliTradeRefundDto;
import com.baiying.hu.entity.dto.AlipayDto;
import org.springframework.stereotype.Repository;

@Repository
public interface AlipayMapper {


    /**
     * 持久化电脑端付款记录
     * @param alipayDto
     */
    void saveAliPay(AlipayDto alipayDto);

    /**
     * 根据商户订单号更新支付记录
     * @param alipayDto
     */
    void updateAlipayByOutTradeNo(AlipayDto alipayDto);

    /**
     * 持久化退款记录
     * @param tradeRefund
     */
    void saveAliTradeRefund(AliTradeRefundDto tradeRefund);

    /**
     * 根据商户订单号跟新推荐记录
     * @param tradeRefund
     */
    void updateAliTradeRefundByOutRequestNo(AliTradeRefundDto tradeRefund);

    /**
     * 持久化转账记录
     * @param fundtransfer
     */
    void saveAliFundTransfer(AliFundTransferDto fundtransfer);

    /**
     * 根据支付宝转账单据号跟新转账记录
     * @param fundtransfer
     */
    void updateAliFundTransferByOrderId(AliFundTransferDto fundtransfer);
}