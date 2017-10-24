package com.baiying.hu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "服务实体类")
public class Business implements Serializable {
    @ApiModelProperty(value = "服务id")
    private Long id;
    @ApiModelProperty(value = "服务名称", required = true)
    private String businessName;
    @ApiModelProperty(value = "服务code", required = true)
    private Long businessCode;
    @ApiModelProperty(value = "服务详情图片")
    private String businessImage;
    @ApiModelProperty(value = "服务详情")
    private String businessDetail;
    @ApiModelProperty(value = "服务title")
    private String businessTitle;
    @ApiModelProperty(value = "热点服务")
    private Integer businessHot;
    @ApiModelProperty(value = "父级id")
    private Long parentId = 0L;
    @JSONField(serialize = false)
    private Date createdAt;
    @JSONField(serialize = false)
    private Date updatedAt;
    @ApiModelProperty(value = "状态")
    private Byte status;
    @ApiModelProperty(value = "子集数组")
    private List<Business> children = Lists.newArrayList();

}