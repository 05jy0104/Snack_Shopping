package com.snack.mapper;

import com.snack.entity.News;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface NewsMapper {
    List<News> findAll();
    News findById(Integer id);
    void insert(News news);
    void update(News news);
    void delete(Integer id);
}
