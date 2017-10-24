package com.baiying.hu.common.guava;

import com.baiying.hu.entity.ProblemOperator;
import com.google.common.eventbus.AsyncEventBus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

/**
 * Created by jmx on 16/6/14.
 * guava异步处理
 */
@Component
public class AsyncEvtBusHelper implements InitializingBean {
    private int poolSize =3;
    @Autowired
    private EventListener eventListener;
    AsyncEventBus eventBus;

    public void post(ProblemOperator o){
        eventBus.post(o);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        eventBus = new AsyncEventBus(Executors.newFixedThreadPool(poolSize));
        eventBus.register(eventListener);

    }
}
