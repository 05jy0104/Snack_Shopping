package com.snack.service;

import com.snack.entity.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(Integer userId, String address, String phone);
    Order createOrderWithCartItems(Integer userId, String address, String phone, List<Integer> cartItemIds);
    List<Order> findByUserId(Integer userId);
    List<Order> findAll();
    Order findById(Integer id);
    Order findByIdWithItems(Integer id);
    void updateStatus(Integer id, String status);
    void delete(Integer id);
}
