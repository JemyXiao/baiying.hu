package com.baiying.hu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "问题实体类")
public class Problem {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "服务类型")
    private String businessId;
    @ApiModelProperty(value = "问题标题")
    private String problemTitle;
    @ApiModelProperty(value = "问题详情")
    private String problemDetail;
    @ApiModelProperty(value = "创建人id")
    private Long createId;
    @ApiModelProperty(value = "创建人名字，自动获取")
    private String createName;
    @ApiModelProperty(value = "顾问id")
    private Long consultantId;

    private Date createdAt;

    private Date updatedAt;

    private Byte status;

    private long visitCount;

    private long newsCount;

    private List<News> newsList;
}