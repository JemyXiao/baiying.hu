package com.baiying.hu.service.impl;

import com.baiying.hu.entity.*;
import com.baiying.hu.entity.dto.ProblemDto;
import com.baiying.hu.entity.vo.ProblemResult;
import com.baiying.hu.mapper.NewsMapper;
import com.baiying.hu.mapper.ProblemMapper;
import com.baiying.hu.service.ProblemOptService;
import com.baiying.hu.service.ProblemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by jmx
 * 2017/9/28.
 */
@Service
public class ProblemServiceImpl implements ProblemService {
    private final ProblemMapper problemMapper;
    private final ProblemOptService optService;
    private final NewsMapper newsMapper;

    @Autowired
    public ProblemServiceImpl(ProblemMapper problemMapper, ProblemOptService optService, NewsMapper newsMapper) {
        this.problemMapper = problemMapper;
        this.optService = optService;
        this.newsMapper = newsMapper;
    }

    @Override
    public List<Problem> queryAllProblem(ProblemExample example) {
        List<Problem> problemList = problemMapper.selectByExample(example);
        for (Problem problem : problemList) {
            ProblemOperator operator = optService.selectByPrimaryKey(problem.getId());
            if (!Objects.isNull(operator) && operator.getVisitCount() != null) {
                problem.setVisitCount(operator.getVisitCount());
            }
            NewsExample newsExample = new NewsExample();
            newsExample.createCriteria().andproblemIdEqualTo(problem.getId());
            int count = newsMapper.countByExample(newsExample);
            problem.setNewsCount(count);
        }
        return problemList;
    }

    @Override
    public ProblemResult queryDetailById(long id) {
        ProblemResult problemResult = problemMapper.selectProblemDetail(id);
        ProblemOperator operator = optService.selectByPrimaryKey(problemResult.getId());
        if (!Objects.isNull(operator) && operator.getVisitCount() != null) {
            problemResult.setVisitCount(operator.getVisitCount());
        }
        List<News> news = problemResult.getNewsList();
        if (news != null) {
            problemResult.setNewsCount(problemResult.getNewsList().size());
        }
        return problemResult;
    }

    @Override
    public int createProblem(ProblemDto dto) {
        Problem problem = new Problem();
        BeanUtils.copyProperties(dto, problem);
        return problemMapper.insert(problem);
    }

    @Override
    public int resolvedProblem(long id) {
        Problem problem = new Problem();
        problem.setId(id);
        problem.setStatus((byte) 1);
        return problemMapper.updateByPrimaryKeySelective(problem);
    }
}
