package com.baiying.hu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProblemOperator {
    private Long id;

    private Long problemId;

    private Long visitCount;

    private Long newsCount;

    private Date createdAt;

    private Date updatedAt;
}