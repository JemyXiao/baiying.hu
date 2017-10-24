package com.baiying.hu.service.impl;

import com.baiying.hu.entity.ProblemOperator;
import com.baiying.hu.mapper.ProblemOperatorMapper;
import com.baiying.hu.service.ProblemOptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jmx
 * 2017/10/23.
 */
@Service
public class ProblemOptServiceImpl implements ProblemOptService {

    @Autowired
    private ProblemOperatorMapper mapper;

    @Override
    public int insert(ProblemOperator operator) {
        return mapper.insert(operator);
    }

    @Override
    public int update(ProblemOperator operator) {
        return mapper.updateByPrimaryKey(operator);
    }

    @Override
    public ProblemOperator selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }
}
