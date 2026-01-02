package com.snack.mapper;

import com.snack.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface OrderItemMapper {
    List<OrderItem> findByOrderId(Integer orderId);
    void insert(OrderItem orderItem);
    void deleteByOrderId(Integer orderId);
}
