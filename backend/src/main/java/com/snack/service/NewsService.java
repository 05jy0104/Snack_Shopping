package com.snack.service;

import com.snack.entity.News;
import java.util.List;

public interface NewsService {
    List<News> findAll();
    News findById(Integer id);
    void add(News news);
    void update(News news);
    void delete(Integer id);
}
