package com.baiying.hu.service;

import com.baiying.hu.entity.ProblemOperator;

/**
 * Created by jmx
 * 2017/10/23
 */
public interface ProblemOptService {
    int insert(ProblemOperator operator);

    int update(ProblemOperator operator);

    ProblemOperator selectByPrimaryKey(Long id);
}
