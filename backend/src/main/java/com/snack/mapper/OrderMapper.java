package com.snack.mapper;

import com.snack.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> findByUserId(Integer userId);
    List<Order> findAll();
    Order findById(Integer id);
    Order findByOrderNo(String orderNo);
    void insert(Order order);
    void update(Order order);
    void delete(Integer id);
}
