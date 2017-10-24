package com.baiying.hu.entity.vo;

import com.baiying.hu.entity.News;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by jmx
 * 2017/10/2.
 */
@Data
@ApiModel(value = "问题詳情")
public class ProblemResult {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "服务类型")
    private String businessId;
    @ApiModelProperty(value = "问题类型")
    private String problemType;
    @ApiModelProperty(value = "问题标题")
    private String problemTitle;
    @ApiModelProperty(value = "问题详情")
    private String problemDetail;
    @ApiModelProperty(value = "创建人id")
    private Long createId;
    @ApiModelProperty(value = "创建人昵称")
    private String createName;
    @ApiModelProperty(value = "顾问id")
    private Long consultantId;
    @ApiModelProperty(value = "顾问姓名")
    private String consultantName;

    private Date createdAt;

    private Date updatedAt;

    private Byte status;
    private long visitCount;
    private long newsCount;
    @ApiModelProperty(value = "消息列表")
    private List<News> newsList;
}
