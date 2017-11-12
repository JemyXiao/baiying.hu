CREATE TABLE `ali_trade_page_pay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `app_id` varchar(32) NOT NULL DEFAULT '' COMMENT 'appid',
  `method` varchar(128) NOT NULL DEFAULT 0 COMMENT '请求方法名称',
  `out_trade_no` varchar(64) NOT NULL DEFAULT 0 COMMENT '商户订单号',
  `trade_no` varchar(64) NOT NULL DEFAULT 0 COMMENT '支付宝交易号',
  `product_code` varchar(32) NOT NULL DEFAULT 0 COMMENT '销售产品码',
  `total_amount` varchar(11) NOT NULL DEFAULT 0 COMMENT '订单总金额',
  `subject` varchar(256) NOT NULL DEFAULT 0 COMMENT '订单标题',
  `buyer_id` varchar(28) NOT NULL DEFAULT '' COMMENT '买家支付宝用户号',
  `seller_id` varchar(28) NOT NULL DEFAULT '' COMMENT '卖家支付宝用户号',
  `trade_status` varchar(10) NOT NULL DEFAULT '' COMMENT '交易状态',
  `pay_action` varchar(10) NOT NULL DEFAULT '' COMMENT '支付动作',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '电脑端支付详情';


CREATE TABLE `ali_trade_refund` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `app_id` varchar(32) NOT NULL DEFAULT '' COMMENT 'appid',
  `method` varchar(128) NOT NULL DEFAULT 0 COMMENT '请求方法名称',
  `out_trade_no` varchar(64) NOT NULL DEFAULT 0 COMMENT '商户订单号',
  `out_request_no` varchar(32) NOT NULL DEFAULT 0 COMMENT '本笔退款对应的退款请求号',
  `trade_no` varchar(64) NOT NULL DEFAULT 0 COMMENT '支付宝交易号',
  `refund_fee` varchar(11) NOT NULL DEFAULT 0 COMMENT '总退款金额',
  `total_amount` varchar(11) NOT NULL DEFAULT 0 COMMENT '该笔退款所对应的交易的订单金额',
  `refund_reason` varchar(256) NOT NULL DEFAULT 0 COMMENT '退款的原因说明',
  `fund_change` varchar(1) NOT NULL DEFAULT '' COMMENT '本次退款是否发生了资金变化',
  `gmt_create` varchar(32) NOT NULL DEFAULT '' COMMENT '交易创建时间',
  `buyer_user_id` varchar(28) NOT NULL DEFAULT '' COMMENT '买家在支付宝的用户id',
  `buyer_logon_id` varchar(28) NOT NULL DEFAULT '' COMMENT '用户的登录id',
  `pay_action` varchar(10) NOT NULL DEFAULT '' COMMENT '支付动作',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '退款详情';


CREATE TABLE `ali_trade_transfer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `app_id` varchar(32) NOT NULL DEFAULT '' COMMENT 'appid',
  `method` varchar(128) NOT NULL DEFAULT 0 COMMENT '请求方法名称',
  `out_biz_no` varchar(64) NOT NULL DEFAULT 0 COMMENT '商户转账唯一订单号',
  `payee_type` varchar(32) NOT NULL DEFAULT 0 COMMENT '收款方账户类型',
  `payee_account` varchar(64) NOT NULL DEFAULT 0 COMMENT '收款方账户',
  `amount` varchar(11) NOT NULL DEFAULT 0 COMMENT '转账金额',
  `payer_show_name` varchar(11) NOT NULL DEFAULT 0 COMMENT '付款方姓名',
  `payee_real_name` varchar(256) NOT NULL DEFAULT 0 COMMENT '收款方真实姓名',
  `remark` varchar(100) NOT NULL DEFAULT '' COMMENT '转账备注',
  `order_id` varchar(32) NOT NULL DEFAULT '' COMMENT '支付宝转账单据号',
  `pay_date` varchar(20) NOT NULL DEFAULT '' COMMENT '支付时间',
  `status` varchar(10) NOT NULL DEFAULT '' COMMENT '转账单据状态',
  `arrival_time_end` varchar(20) NOT NULL DEFAULT '' COMMENT '预计到账时间',
  `order_fee` varchar(20) NOT NULL DEFAULT '' COMMENT '预计收费金额',
  `fail_reason` varchar(100) NOT NULL DEFAULT '' COMMENT '失败具体的原因',
  `error_code` varchar(100) NOT NULL DEFAULT '' COMMENT '错误代码',
  `pay_action` varchar(10) NOT NULL DEFAULT '' COMMENT '支付动作',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ix_created_at` (`created_at`),
  KEY `ix_updated_at` (`updated_at`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT '支付宝转账详情';