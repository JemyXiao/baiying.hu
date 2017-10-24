package com.baiying.hu.entity.dto;

import com.baiying.hu.common.validate.DV;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by jmx
 * 2017/9/30.
 */
@Data
@ApiModel(value = "提问题dto")
public class ProblemDto {
    @DV(description = "服务类型")
    @ApiModelProperty(value = "服务类型", required = true)
    private String businessId;
    @DV(description = "问题标题", maxLength = 20)
    @ApiModelProperty(value = "问题标题", required = true)
    private String problemTitle;
    @DV(description = "问题详情", maxLength = 100)
    @ApiModelProperty(value = "问题详情", required = true)
    private String problemDetail;
    @ApiModelProperty(value = "創建人ID，自动获取")
    private long createId;
    @ApiModelProperty(value = "创建人名字，自动获取")
    private String createName;
    //    @ApiModelProperty(value = "问题类型", required = true)
//    @DV(description = "问题类型")
//    private String problemType;
    @ApiModelProperty(value = "顾问id")
    private long consultantId;
}
