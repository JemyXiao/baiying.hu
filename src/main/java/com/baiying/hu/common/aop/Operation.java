package com.baiying.hu.common.aop;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * Created by jmx on 17/6/20.
 * 操作注解设置
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface Operation {
    String name() default "other";
}
