package com.snack.mapper;

import com.snack.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CartMapper {
    List<Cart> findByUserId(Integer userId);
    Cart findByUserIdAndSnackId(Integer userId, Integer snackId);
    Cart findById(Integer id);
    void insert(Cart cart);
    void update(Cart cart);
    void delete(Integer id);
    void deleteByUserId(Integer userId);
}
