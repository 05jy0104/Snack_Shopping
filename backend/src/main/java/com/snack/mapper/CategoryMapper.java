package com.snack.mapper;

import com.snack.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> findAll();
    Category findById(Integer id);
    void insert(Category category);
    void update(Category category);
    void delete(Integer id);
}
