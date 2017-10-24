package com.baiying.hu.service.impl;

import com.baiying.hu.entity.Consultant;
import com.baiying.hu.entity.News;
import com.baiying.hu.entity.Problem;
import com.baiying.hu.entity.User;
import com.baiying.hu.entity.dto.NewsDto;
import com.baiying.hu.entity.vo.ConsultantResult;
import com.baiying.hu.mapper.ConsultantMapper;
import com.baiying.hu.mapper.NewsMapper;
import com.baiying.hu.mapper.UserMapper;
import com.baiying.hu.service.NewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by jmx
 * 2017/9/28.
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private ConsultantMapper consultantMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public int insertNewsByUser(NewsDto newsDto) {
        //获取用户信息
        User user = userMapper.selectByPrimaryKey(newsDto.getReplyId());
        News record = new News();
        BeanUtils.copyProperties(newsDto, record);
        record.setReplyName(user.getNickName());
        if (!Objects.isNull(newsDto.getConsultantId())) {
            ConsultantResult result = consultantMapper.selectByPrimaryKey(newsDto.getConsultantId());
            record.setConsultantName(result.getActualName());
        }
        //消息類型用戶
        record.setNewsType((byte) 0);
        return newsMapper.insert(record);
    }

    @Override
    public int insertNewsByConsultant(NewsDto newsDto) {
        News record = new News();
        BeanUtils.copyProperties(newsDto, record);
        ConsultantResult result = consultantMapper.selectByPrimaryKey(newsDto.getReplyId());
        record.setReplyName(result.getActualName());
        record.setNewsType((byte) 1);
        return newsMapper.insert(record);
    }
}
