package com.baiying.hu.entity.dto;

import com.baiying.hu.common.validate.DV;
import com.baiying.hu.enums.AliPayActionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by longwu on 17/11/6.
 */
@Data
@ApiModel(value = "退款实体")
public class AliTradeRefundDto {

    @DV(description = "支付宝分配给开发者的应用Id")
    @ApiModelProperty(value = "支付宝分配给开发者的应用Id", required = true)
    private String appId;

    @DV(description = "接口名称")
    @ApiModelProperty(value = "接口名称")
    private String method;

    @DV(description = "商户订单号")
    @ApiModelProperty(value = "商户订单号", required = true)
    private String outTradeNo;

    @DV(description = "支付宝交易号")
    @ApiModelProperty(value = "支付宝交易号")
    private String tradeNo;

    @DV(description = "总退款金额")
    @ApiModelProperty(value = "总退款金额")
    private String refundFee;

    @DV(description = "该笔退款金额")
    @ApiModelProperty(value = "该笔退款金额")
    private String refundAmount;

    @DV(description = "该笔退款所对应的交易的订单金额")
    @ApiModelProperty(value = "该笔退款所对应的交易的订单金额")
    private String totalAmount;

    @DV(description = "交易创建时间")
    @ApiModelProperty(value = "交易创建时间")
    private Date gmtCreate;

    @DV(description = "交易付款时间")
    @ApiModelProperty(value = "交易付款时间")
    private String gmtPayment;

    @DV(description = "交易退款时间")
    @ApiModelProperty(value = "交易退款时间")
    private String gmtRefund;

    @DV(description = "交易结束时间")
    @ApiModelProperty(value = "交易结束时间")
    private String gmtClose;

    @DV(description = "用户的登录id")
    @ApiModelProperty(value = "用户的登录id")
    private String buyerLogonId;

    @DV(description = "买家在支付宝的用户id")
    @ApiModelProperty(value = "买家在支付宝的用户id")
    private String buyerUserId;

    @DV(description = "本次退款是否发生了资金变化")
    @ApiModelProperty(value = "本次退款是否发生了资金变化")
    private String fundChange;

    @DV(description = "本笔退款对应的退款请求号")
    @ApiModelProperty(value = "本笔退款对应的退款请求号")
    private String outRequestNo;

    @DV(description = "退款的原因说明")
    @ApiModelProperty(value = "退款的原因说明")
    private String refundReason;

    @DV(description = "支付宝返回code")
    @ApiModelProperty(value = "支付宝返回code")
    private String code;

    @DV(description = "创建时间")
    @ApiModelProperty(value = "创建时间", required = true)
    private String createdAt;

    @DV(description = "更新时间")
    @ApiModelProperty(value = "更新时间", required = true)
    private String updatedAt;

    @DV(description = "动作")
    @ApiModelProperty(value = "动作")
    private AliPayActionEnum action;
}
