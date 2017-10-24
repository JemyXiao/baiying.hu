package com.baiying.hu.service;

import com.baiying.hu.entity.dto.NewsDto;

/**
 * Created by jmx
 * 2017/9/28.
 */
public interface NewsService {

    int insertNewsByUser(NewsDto newsDto);

    int insertNewsByConsultant(NewsDto newsDto);
}
