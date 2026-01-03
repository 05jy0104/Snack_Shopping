package com.snack.service;

import com.snack.entity.Snack;
import java.util.List;
import java.util.Map;

public interface SnackService {
    List<Snack> findAll();
    List<Snack> findByCategoryId(Integer categoryId);
    List<Snack> search(String keyword);
    Snack findById(Integer id);
    void add(Snack snack);
    void update(Snack snack);
    void delete(Integer id);
    Map<String, Object> findPage(Integer page, Integer pageSize, Integer categoryId, String keyword);
}
