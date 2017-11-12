package com.baiying.hu.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.baiying.hu.entity.dto.AliFundTransferDto;
import com.baiying.hu.entity.dto.AliTradeRefundDto;
import com.baiying.hu.entity.dto.AlipayDto;
import com.baiying.hu.enums.AliPayActionEnum;
import com.baiying.hu.mapper.AlipayMapper;
import com.baiying.hu.service.AlipayService;
import com.baiying.hu.util.AlipayUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by longwu on 17/11/4.
 * 阿里支付实现类
 */
@Service
public class AlipayServiceImpl implements AlipayService{
    private final static Logger log = LoggerFactory.getLogger(AlipayServiceImpl.class);

    // 签名方式
    private static final String SIGN_TYPE = "RSA2";

    // 字符编码格式
    private static final String CHARSET = "utf-8";

    private static final String FORMAT = "json";

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    @Value("${alipay.appid}")
    private String appId;

    // 商户私钥，您的PKCS8格式RSA2私钥
    @Value("${alipay.merchant.private.key}")
    private String privateKey;

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    @Value("${alipay.public.key}")
    private String publicKey;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    @Value("${alipay.notify.url}")
    private String notifyUrl;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    @Value("${alipay.return.url}")
    private String returnUrl;

    // 支付宝网关
    @Value("${alipay.gateway.url}")
    private String gatewayUrl;

    @Autowired
    private AlipayMapper alipayMapper;


    private AlipayClient alipayClient;

    private Executor executor;


    /**
     * 初始化AlipayClient
     * alipayClient只需要初始化一次，后续调用不同的API都可以使用同一个alipayClient对象。
     */
    @PostConstruct
    public void initMethod() {
        alipayClient = new DefaultAlipayClient(gatewayUrl, appId, privateKey,
                FORMAT, CHARSET, publicKey, SIGN_TYPE);
        executor = Executors.newFixedThreadPool(2, new ThreadFactory() {
            private AtomicInteger counter = new AtomicInteger();
            @Override
            public Thread newThread(Runnable r) {
                int i = counter.incrementAndGet();
                return new Thread(r, "Query-ali-trade-" + i);
            }
        });
    }

    /**
     * https://docs.open.alipay.com/270/alipay.trade.page.pay
     * 电脑网站支付(PC支付)
     * @param model 支付宝交易支付参数
     * @return form表单
     */
    @Override
    public String tradePagePay(AlipayTradePagePayModel model) {
        AlipayUtils.validateParams(model);
        log.info("Invoking ali page pay, params: {}", ReflectionToStringBuilder.toString(model, ToStringStyle.SHORT_PREFIX_STYLE));

        //设置请求参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(notifyUrl);
        request.setReturnUrl(returnUrl);
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); //调用SDK生成表单
            // 持久化支付信息
            AlipayDto alipayDto = new AlipayDto();
            alipayDto.setAppId(appId);
            alipayDto.setMethod(request.getApiMethodName());
            alipayDto.setOutTradeNo(model.getOutTradeNo());
            alipayDto.setProductCode(model.getProductCode());
            alipayDto.setTotalAmount(model.getTotalAmount());
            alipayDto.setSubject(model.getSubject());
            alipayDto.setAction(AliPayActionEnum.SAVE);
            alipayMapper.saveAliPay(alipayDto);
            log.info("Save success: {}", alipayDto);

        } catch (AlipayApiException e) {
            log.error("Failed to call ali page pay. Cause: {}", e);
        }
        return form;
    }

    @Override
    public String alipayReturn(Map<String, String> map) {
        log.info("Receiving return url, map: {}", map);
        boolean verifyResult;
        try {
            verifyResult = AlipaySignature.rsaCheckV1(map, publicKey, CHARSET, SIGN_TYPE);
            if (verifyResult) {
                // 验证成功
                log.info("Return url verify success.");
                String appid = map.get("app_id");
                // appid校验
                if (!appId.equals(appid)) {
                    log.warn("app id invalid, origin: {}, return: {}", appId, appid);
                    return "failure";
                }
                String outTradeNo = map.get("out_trade_no");
                // TODO 查询订单号 交易total_amount是否与订单系统一致
                // Order order = orderservice.getOrderByNo(outTradeNo);
                // if(order.getTotalAmout != totalAmout) return
                String tradeStatus = map.get("trade_status");
                if ("TRADE_SUCCESS".equals(tradeStatus)) {
                    log.info("Trade success.");
                    // TODO 支付成功,修改订单状态
                } else {
                    log.info("Trade failure, status: {}", tradeStatus);
                    return "failure";
                }


                return "success";

            } else {
                log.info("Return url verify failure.");
                return "failure";
            }
        } catch (AlipayApiException e) {
            log.error("Failed to receive return url. Cause: {}", e);
            return "failure";
        }

    }

    /**
     * https://docs.open.alipay.com/270/105902/
     * 支付成功异步回调接口
     * @param map
     * @return
     */
    @Override
    public String alipayNotify(Map<String, String> map) {
        log.info("Receiving notify url, map: {}", map);
        try {
            boolean signVerified = AlipaySignature.rsaCheckV1(map, publicKey, CHARSET, SIGN_TYPE); //调用SDK验证签名
            if (signVerified) {
                // 验证成功
                log.info("Notify url verify success.");
                String appid = map.get("app_id");
                // appid校验
                if (!appId.equals(appid)) {
                    log.warn("app id invalid, origin: {}, return: {}", appId, appid);
                    return "failure";
                }

                // 持久化支付信息
                AlipayDto alipayDto = new AlipayDto();
                alipayDto.setOutTradeNo(map.get("out_trade_no"));
                alipayDto.setTradeNo(map.get("trade_no"));
                alipayDto.setBuyerId(map.get("buyer_id"));
                alipayDto.setSellerId(map.get("seller_id"));
                alipayDto.setTradeStatus(map.get("trade_status"));
                alipayDto.setAction(AliPayActionEnum.CALLBACK);
                alipayMapper.updateAlipayByOutTradeNo(alipayDto);

                String outTradeNo = map.get("out_trade_no");
                // TODO 查询订单号 交易total_amount是否与订单系统一致
                // Order order = orderservice.getOrderByNo(outTradeNo);
                // if(order.getTotalAmout != totalAmout) return
                String tradeStatus = map.get("trade_status");
                if ("TRADE_SUCCESS".equals(tradeStatus) ||
                        "TRADE_FINISHED".equals(tradeStatus)) {
                    log.info("Trade success.");
                    // TODO 支付成功,修改订单状态
                    return "success";
                } else {
                    log.info("Trade failure, status: {}", tradeStatus);
                    return "failure";
                }


            } else {
                log.info("Notify url verify failure.");
                return "failure";
            }
        } catch (AlipayApiException e) {
            log.error("Failed to receive notify url. Cause: {}", e);
            return "failure";
        }

    }

    /**
     * https://docs.open.alipay.com/api_1/alipay.trade.refund
     * @param model
     * out_trade_no:订单支付时传入的商户订单号,不能和 trade_no同时为空。
     * trade_no:支付宝交易号，和商户订单号不能同时为空
     * refund_amount:需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
     * refund_reason:退款的原因说明
     * out_request_no:标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
     * @return
     */
    @Override
    public boolean tradeRefund(AlipayTradeRefundModel model) {
        log.info("Invoking trade refund, params: {}",ReflectionToStringBuilder.toString(model, ToStringStyle.SHORT_PREFIX_STYLE));
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizModel(model);
        AlipayTradeRefundResponse response;
        try {
            response = alipayClient.execute(request);

            if (response.isSuccess()) {
                // 持久化支付信息
                AliTradeRefundDto tradeRefund = new AliTradeRefundDto();
                tradeRefund.setAppId(appId);
                tradeRefund.setMethod(request.getApiMethodName());
                tradeRefund.setOutTradeNo(response.getOutTradeNo());
                tradeRefund.setOutRequestNo(model.getOutRequestNo());
                tradeRefund.setTradeNo(response.getTradeNo());
                tradeRefund.setRefundFee(response.getRefundFee());
                tradeRefund.setFundChange(response.getFundChange());
                tradeRefund.setGmtCreate(response.getGmtRefundPay());
                tradeRefund.setRefundReason(model.getRefundReason());
                tradeRefund.setBuyerUserId(response.getBuyerUserId());
                tradeRefund.setBuyerLogonId(response.getBuyerLogonId());
                tradeRefund.setAction(AliPayActionEnum.SAVE);
                alipayMapper.saveAliTradeRefund(tradeRefund);
                // 异步调用支付宝查询接口
                AlipayTradeFastpayRefundQueryModel query = new AlipayTradeFastpayRefundQueryModel();
                query.setTradeNo(response.getTradeNo());
                query.setOutTradeNo(model.getOutTradeNo());
                query.setOutRequestNo(model.getOutRequestNo());
                executor.execute(() -> tradeFastpayRefundQuery(query));
            }

        } catch (AlipayApiException e) {
            log.error("Failed to trade refund. Cause: {}",e);
            return false;
        }
        log.info("Succeed to trade refund, response: {}",ReflectionToStringBuilder.toString(response, ToStringStyle.SHORT_PREFIX_STYLE));
        return response.isSuccess();
    }

    /**
     * https://docs.open.alipay.com/api_1/alipay.trade.fastpay.refund.query
     * 统一收单交易退款查询
     * trade_no:支付宝交易号，和商户订单号不能同时为空
     * out_trade_no:订单支付时传入的商户订单号,和支付宝交易号不能同时为空。
     * out_request_no:请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
     * @param model
     * @return
     */
    @Override
    public boolean tradeFastpayRefundQuery(AlipayTradeFastpayRefundQueryModel model) {
        log.info("Invoking trade fast pay refund query, params: {}", ReflectionToStringBuilder.toString(model, ToStringStyle.SHORT_PREFIX_STYLE));
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizModel(model);
        AlipayTradeFastpayRefundQueryResponse response = null;
        int i = 0;
        while (i < 3) {
            try {
                response = alipayClient.execute(request);

                // 保存请求支付宝记录
                AliTradeRefundDto tradeRefund = new AliTradeRefundDto();
                tradeRefund.setOutRequestNo(model.getOutRequestNo());
                tradeRefund.setRefundAmount(response.getRefundAmount());
                tradeRefund.setTotalAmount(response.getTotalAmount());
                tradeRefund.setCode(response.getCode());
                tradeRefund.setAction(AliPayActionEnum.QUERY);
                alipayMapper.updateAliTradeRefundByOutRequestNo(tradeRefund);


            } catch (AlipayApiException e) {
                log.error("Failed to trade refund. Cause: {}", e);
                i++;
                try {
                    TimeUnit.SECONDS.sleep(i);
                } catch (InterruptedException e1) {
                    log.error("Thread sleep failure. Cause: {}", e);
                }
            }
            i = 3;
        }
        log.info("Succeed to trade fast pay refund query, response: {}",ReflectionToStringBuilder.toString(response, ToStringStyle.SHORT_PREFIX_STYLE));
        return response.isSuccess();
    }

    /**
     * https://docs.open.alipay.com/api_1/alipay.trade.query
     * 统一收单线下交易查询
     * trade_no:支付宝交易号，和商户订单号不能同时为空
     * out_trade_no:订单支付时传入的商户订单号,和支付宝交易号不能同时为空。
     * @param model
     * @return
     */
    @Override
    public boolean tradeQuery(AlipayTradeQueryModel model) {
        log.info("Invoking trade query, params: {}", ReflectionToStringBuilder.toString(model, ToStringStyle.SHORT_PREFIX_STYLE));
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(model);
        AlipayTradeQueryResponse response;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                AlipayDto alipay = new AlipayDto();
                alipay.setOutTradeNo(response.getOutTradeNo());
                alipay.setBuyerLogonId(response.getBuyerLogonId());
                alipay.setTradeStatus(response.getTradeStatus());
                alipay.setTotalAmount(response.getTotalAmount());
                alipay.setBuyerId(response.getBuyerUserId());
                alipay.setAction(AliPayActionEnum.QUERY);
                alipayMapper.updateAlipayByOutTradeNo(alipay);

            }

        } catch (AlipayApiException e) {
            log.error("Failed to trade refund. Cause: {}",e);
            return false;
        }
        log.info("Succeed to trade query, response: {}",ReflectionToStringBuilder.toString(response, ToStringStyle.SHORT_PREFIX_STYLE));
        return response.isSuccess();
    }

    /**
     * https://docs.open.alipay.com/api_28/alipay.fund.trans.toaccount.transfer/
     * 单笔转账到支付宝账户接口
     * out_biz_no;商户转账唯一订单号。发起转账来源方定义的转账单据ID，用于将转账回执通知给来源方
     * payee_type:收款方账户类型。可取值：
                  1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
                  2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。
     * payee_account:收款方账户。与payee_type配合使用。付款方和收款方不能是同一个账户
     * amount:转账金额，单位：元
     * payee_real_name:收款方真实姓名,如果本参数不为空，则会校验该账户在支付宝登记的实名是否与收款方真实姓名一致
     * @param model
     * @return
     */
    @Override
    public boolean FundTransToaccountTransfer(AlipayFundTransToaccountTransferModel model) {
        log.info("Invoking fund trans to account transfer, params: {}" , ReflectionToStringBuilder.toString(model, ToStringStyle.SHORT_PREFIX_STYLE));
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizModel(model);
        AlipayFundTransToaccountTransferResponse response;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                // 保存请求支付宝记录
                AliFundTransferDto fundtransfer = new AliFundTransferDto();
                fundtransfer.setAppId(appId);
                fundtransfer.setMethod(request.getApiMethodName());
                fundtransfer.setAmount(request.getApiMethodName());
                fundtransfer.setOutBizNo(model.getOutBizNo());
                fundtransfer.setPayeeType(model.getPayeeType());
                fundtransfer.setPayeeAccount(model.getPayeeAccount());
                fundtransfer.setAmount(model.getAmount());
                fundtransfer.setPayerShowName(model.getPayerShowName());
                fundtransfer.setPayeeRealName(model.getPayeeRealName());
                fundtransfer.setRemark(model.getRemark());
                fundtransfer.setOrderId(response.getOrderId());
                fundtransfer.setPayDate(response.getPayDate());
                fundtransfer.setAction(AliPayActionEnum.SAVE);
                alipayMapper.saveAliFundTransfer(fundtransfer);

                // 异步调用支付宝查询接口
                AlipayFundTransOrderQueryModel query = new AlipayFundTransOrderQueryModel();
                query.setOutBizNo(model.getOutBizNo());
                query.setOrderId(response.getOrderId());
                executor.execute(() -> FundTransOrderQuery(query));
            }

        } catch (AlipayApiException e) {
            log.error("Failed to trade refund. Cause: {}", e);
            return false;
        }
        log.info("Succeed to fund trans to account transfer, response: {}",ReflectionToStringBuilder.toString(response, ToStringStyle.SHORT_PREFIX_STYLE));
        return response.isSuccess();
    }

    /**
     * https://docs.open.alipay.com/api_28/alipay.fund.trans.order.query/
     * out_biz_no:商户转账唯一订单号
     * order_id:支付宝转账单据号：和商户转账唯一订单号不能同时为空
     * @param model
     * @return
     */
    @Override
    public boolean FundTransOrderQuery(AlipayFundTransOrderQueryModel model) {
        log.info("Invoking fund transfer query, params: {}", ReflectionToStringBuilder.toString(model, ToStringStyle.SHORT_PREFIX_STYLE));
        AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
        request.setBizModel(model);
        AlipayFundTransOrderQueryResponse response = null;
        int i = 0;
        while (i < 3) {
            try {
                response = alipayClient.execute(request);
                // 保存请求支付宝记录
                AliFundTransferDto fundtransfer = new AliFundTransferDto();
                fundtransfer.setOrderId(response.getOrderId());
                fundtransfer.setStatus(response.getStatus());
                fundtransfer.setArrivalTimeEnd(response.getArrivalTimeEnd());
                fundtransfer.setOrderFee(response.getOrderFee());
                fundtransfer.setFailReason(response.getFailReason());
                fundtransfer.setErrorCode(response.getErrorCode());
                fundtransfer.setAction(AliPayActionEnum.QUERY);
                alipayMapper.updateAliFundTransferByOrderId(fundtransfer);

            } catch (AlipayApiException e) {
                log.error("Failed to trade refund. Cause: {}", e);
                i++;
                try {
                    TimeUnit.SECONDS.sleep(i);
                } catch (InterruptedException e1) {
                    log.error("Thread sleep failure. Cause: {}", e);
                }
            }
            i = 3;
        }
        log.info("Succeed to fund transfer query, response: {}",ReflectionToStringBuilder.toString(response, ToStringStyle.SHORT_PREFIX_STYLE));
        return response.isSuccess();
    }


}
