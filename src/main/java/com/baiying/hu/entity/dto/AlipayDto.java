package com.baiying.hu.entity.dto;

import com.baiying.hu.common.validate.DV;
import com.baiying.hu.enums.AliPayActionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by longwu on 17/11/5.
 * PC支付持久化对象
 */
@Data
@ApiModel(value = "支付请求实体")
public class AlipayDto {

    @DV(description = "支付宝分配给开发者的应用Id")
    @ApiModelProperty(value = "支付宝分配给开发者的应用Id", required = true)
    private String appId;

    @DV(description = "接口名称")
    @ApiModelProperty(value = "接口名称")
    private String method;

    @DV(description = "商户订单号")
    @ApiModelProperty(value = "商户订单号", required = true)
    private String outTradeNo;

    @DV(description = "销售产品码")
    @ApiModelProperty(value = "销售产品码", required = true)
    private String productCode;

    @DV(description = "订单总金额")
    @ApiModelProperty(value = "订单总金额", required = true)
    private String totalAmount;

    @DV(description = "订单标题")
    @ApiModelProperty(value = "订单标题", required = true)
    private String subject;

    @DV(description = "支付宝交易号")
    @ApiModelProperty(value = "支付宝交易号")
    private String tradeNo;

    @DV(description = "买家支付宝用户号")
    @ApiModelProperty(value = "买家支付宝用户号")
    private String buyerId;

    @DV(description = "买家支付宝账号")
    @ApiModelProperty(value = "买家支付宝账号")
    private String buyerLogonId;

    @DV(description = "卖家支付宝用户号")
    @ApiModelProperty(value = "卖家支付宝用户号")
    private String sellerId;

    @DV(description = "交易状态")
    @ApiModelProperty(value = "交易状态")
    private String tradeStatus;

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
