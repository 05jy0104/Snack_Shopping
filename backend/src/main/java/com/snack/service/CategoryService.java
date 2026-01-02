package com.snack.service;

import com.snack.entity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Integer id);
    void add(Category category);
    void update(Category category);
    void delete(Integer id);
}
