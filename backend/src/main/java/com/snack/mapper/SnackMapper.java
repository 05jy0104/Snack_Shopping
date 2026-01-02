package com.snack.mapper;

import com.snack.entity.Snack;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface SnackMapper {
    List<Snack> findAll();
    List<Snack> findByCategoryId(Integer categoryId);
    List<Snack> search(String keyword);
    Snack findById(Integer id);
    Map<String, Object> findSnackById(Integer id);
    void insert(Snack snack);
    void update(Snack snack);
    void delete(Integer id);
    void updateStock(Integer id, Integer quantity);
}
