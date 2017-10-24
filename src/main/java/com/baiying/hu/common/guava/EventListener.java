package com.baiying.hu.common.guava;

import com.baiying.hu.entity.ProblemOperator;
import com.baiying.hu.service.ProblemOptService;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * Created by jmx on 16/6/14.
 * 监听处理
 */
@Component
public class EventListener {
    @Autowired
    private ProblemOptService service;

    @Subscribe
    public void listen(ProblemOperator o) {
        ProblemOperator problemOperator = service.selectByPrimaryKey(o.getProblemId());
        if (Objects.isNull(problemOperator)) {
            service.insert(o);
        } else {
            service.update(o);
        }

    }
}
