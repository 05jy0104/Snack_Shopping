package com.snack.service;

import com.snack.entity.Cart;
import java.util.List;

public interface CartService {
    List<Cart> findByUserId(Integer userId);
    void add(Cart cart);
    void update(Cart cart);
    void delete(Integer id);
    void clear(Integer userId);
}
