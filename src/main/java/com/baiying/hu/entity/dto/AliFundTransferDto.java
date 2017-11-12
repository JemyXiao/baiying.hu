package com.baiying.hu.entity.dto;

import com.baiying.hu.common.validate.DV;
import com.baiying.hu.enums.AliPayActionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by longwu on 17/11/5.
 * 转账持久化对象
 */
@Data
@ApiModel(value = "支付请求实体")
public class AliFundTransferDto {

    @DV(description = "支付宝分配给开发者的应用Id")
    @ApiModelProperty(value = "支付宝分配给开发者的应用Id", required = true)
    private String appId;

    @DV(description = "接口名称")
    @ApiModelProperty(value = "接口名称")
    private String method;

    @DV(description = "商户转账唯一订单号")
    @ApiModelProperty(value = "商户转账唯一订单号")
    private String outBizNo;

    // 1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。
    // 2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。
    @DV(description = "收款方账户类型")
    @ApiModelProperty(value = "收款方账户类型")
    private String payeeType;

    @DV(description = "收款方账户")
    @ApiModelProperty(value = "收款方账户")
    private String payeeAccount;

    @DV(description = "转账金额")
    @ApiModelProperty(value = "转账金额")
    private String amount;

    @DV(description = "付款方姓名")
    @ApiModelProperty(value = "付款方姓名")
    private String payerShowName;

    @DV(description = "收款方真实姓名")
    @ApiModelProperty(value = "收款方真实姓名")
    private String payeeRealName;

    @DV(description = "转账备注")
    @ApiModelProperty(value = "转账备注")
    private String remark;

    @DV(description = "支付宝转账单据号")
    @ApiModelProperty(value = "支付宝转账单据号")
    private String orderId;

    @DV(description = "支付时间")
    @ApiModelProperty(value = "支付时间")
    private String payDate;

    // SUCCESS：成功（配合"单笔转账到银行账户接口"产品使用时, 同一笔单据多次查询有可能从成功变成退票状态）；
    // FAIL：失败（具体失败原因请参见error_code以及fail_reason返回值）；
    // INIT：等待处理；
    // DEALING：处理中；
    // REFUND：退票（仅配合"单笔转账到银行账户接口"产品使用时会涉及, 具体退票原因请参见fail_reason返回值）；
    // UNKNOWN：状态未知。
    @DV(description = "转账单据状态")
    @ApiModelProperty(value = "转账单据状态")
    private String status;

    @DV(description = "预计到账时间")
    @ApiModelProperty(value = "预计到账时间")
    private String arrivalTimeEnd;

    @DV(description = "预计收费金额（元）")
    @ApiModelProperty(value = "预计收费金额（元）")
    private String orderFee;

    @DV(description = "失败具体的原因")
    @ApiModelProperty(value = "失败具体的原因")
    private String failReason;

    @DV(description = "错误代码")
    @ApiModelProperty(value = "错误代码")
    private String errorCode;

    @DV(description = "动作")
    @ApiModelProperty(value = "动作")
    private AliPayActionEnum action;


}
