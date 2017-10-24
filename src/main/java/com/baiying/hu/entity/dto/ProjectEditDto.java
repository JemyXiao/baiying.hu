package com.baiying.hu.entity.dto;

import com.baiying.hu.entity.Project;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by jmx
 * 2017/10/18.
 */
@Data
@ApiModel(value = "编辑项目dto")
public class ProjectEditDto extends Project {
    @ApiModelProperty(value = "服务商id")
    private Long serviceId;
    @ApiModelProperty(value = "编辑人id,可不穿")
    private Long consultantId;
}
