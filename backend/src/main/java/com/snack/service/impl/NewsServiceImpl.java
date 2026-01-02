package com.snack.service.impl;

import com.snack.entity.News;
import com.snack.mapper.NewsMapper;
import com.snack.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> findAll() {
        return newsMapper.findAll();
    }

    @Override
    public News findById(Integer id) {
        return newsMapper.findById(id);
    }

    @Override
    public void add(News news) {
        news.setCreateTime(new Date());
        newsMapper.insert(news);
    }

    @Override
    public void update(News news) {
        newsMapper.update(news);
    }

    @Override
    public void delete(Integer id) {
        newsMapper.delete(id);
    }
}
