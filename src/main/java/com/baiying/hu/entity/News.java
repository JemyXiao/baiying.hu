package com.baiying.hu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "消息实体类")
public class News {
    private Long id;
    @ApiModelProperty(value = "问题ID")
    private Long problemId;
    @ApiModelProperty(value = "消息内容")
    private String newsContent;
    @ApiModelProperty(value = "回复人id")
    private Long replyId;
    @ApiModelProperty(value = "回复人姓名")
    private String replyName;
    @ApiModelProperty(value = "顾问ID")
    private Long consultantId;
    @ApiModelProperty(value = "顾问姓名")
    private String consultantName;
    @ApiModelProperty(value = "消息類型 0:用戶,1:顧問")
    private Byte newsType;

    private Date createdAt;

    private Date updatedAt;
    @ApiModelProperty(value = "状态值 0:有效，1:无效")
    private Byte status;
}