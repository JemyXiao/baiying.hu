package com.baiying.hu.service;

import com.baiying.hu.entity.Problem;
import com.baiying.hu.entity.ProblemExample;
import com.baiying.hu.entity.dto.ProblemDto;
import com.baiying.hu.entity.vo.ProblemResult;

import java.util.List;

/**
 * Created by jmx
 * 2017/9/28.
 */
public interface ProblemService {
    List<Problem> queryAllProblem(ProblemExample example);

    ProblemResult queryDetailById(long id);

    int createProblem(ProblemDto dto);

    int resolvedProblem(long id);
}
