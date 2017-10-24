package com.baiying.hu.entity.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by jmx
 * 2017/10/17.
 */
@Data
@ApiModel(value = "消息dto")
public class NewsDto {
    @ApiModelProperty(value = "消息內容", required = true)
    private String newsContent;
    @ApiModelProperty(value = "回復人ID", required = true)
    private Long replyId;
    @ApiModelProperty(value = "顾问ID")
    private Long consultantId;
    @ApiModelProperty(value = "問題ID", required = true)
    private Long problemId;
}
