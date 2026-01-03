package com.snack.service.impl;

import com.snack.entity.Cart;
import com.snack.entity.Order;
import com.snack.entity.OrderItem;
import com.snack.entity.Snack;
import com.snack.entity.User;
import com.snack.mapper.CartMapper;
import com.snack.mapper.OrderItemMapper;
import com.snack.mapper.OrderMapper;
import com.snack.mapper.SnackMapper;
import com.snack.service.OrderService;
import com.snack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private SnackMapper snackMapper;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Order createOrder(Integer userId, String address, String phone) {
        List<Cart> cartList = cartMapper.findByUserId(userId);
        if (cartList == null || cartList.isEmpty()) {
            return null;
        }

        double totalAmount = 0;
        for (Cart cart : cartList) {
            totalAmount += cart.getPrice() * cart.getQuantity();
        }

        User user = userService.findById(userId);
        String username = user != null ? user.getUsername() : "";

        Order order = new Order();
        order.setUserId(userId);
        order.setUsername(username);
        order.setOrderNo(generateOrderNo());
        order.setTotalAmount(totalAmount);
        order.setStatus("待发货");
        order.setAddress(address);
        order.setPhone(phone);
        order.setCreateTime(new Date());
        orderMapper.insert(order);

        for (Cart cart : cartList) {
            OrderItem item = new OrderItem();
            item.setOrderId(order.getId());
            item.setSnackId(cart.getSnackId());
            item.setSnackName(cart.getSnackName());
            item.setPrice(cart.getPrice());
            item.setQuantity(cart.getQuantity());
            item.setSubtotal(cart.getPrice() * cart.getQuantity());
            orderItemMapper.insert(item);

            snackMapper.updateStock(cart.getSnackId(), cart.getQuantity());
        }

        cartMapper.deleteByUserId(userId);

        return order;
    }

    @Override
    @Transactional
    public Order createOrderWithCartItems(Integer userId, String address, String phone, List<Integer> cartItemIds) {
        if (cartItemIds == null || cartItemIds.isEmpty()) {
            return null;
        }

        List<Cart> selectedCartList = cartItemIds.stream()
            .map(id -> cartMapper.findById(id))
            .filter(cart -> cart != null && cart.getUserId().equals(userId))
            .toList();

        if (selectedCartList.isEmpty()) {
            return null;
        }

        double totalAmount = 0;
        for (Cart cart : selectedCartList) {
            totalAmount += cart.getPrice() * cart.getQuantity();
        }

        User user = userService.findById(userId);
        String username = user != null ? user.getUsername() : "";

        Order order = new Order();
        order.setUserId(userId);
        order.setUsername(username);
        order.setOrderNo(generateOrderNo());
        order.setTotalAmount(totalAmount);
        order.setStatus("待发货");
        order.setAddress(address);
        order.setPhone(phone);
        order.setCreateTime(new Date());
        orderMapper.insert(order);

        for (Cart cart : selectedCartList) {
            OrderItem item = new OrderItem();
            item.setOrderId(order.getId());
            item.setSnackId(cart.getSnackId());
            item.setSnackName(cart.getSnackName());
            item.setPrice(cart.getPrice());
            item.setQuantity(cart.getQuantity());
            item.setSubtotal(cart.getPrice() * cart.getQuantity());
            orderItemMapper.insert(item);

            snackMapper.updateStock(cart.getSnackId(), cart.getQuantity());
            cartMapper.delete(cart.getId());
        }

        return order;
    }

    @Override
    public List<Order> findByUserId(Integer userId) {
        return orderMapper.findByUserId(userId);
    }

    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public Order findById(Integer id) {
        return orderMapper.findById(id);
    }

    @Override
    public Order findByIdWithItems(Integer id) {
        return orderMapper.findByIdWithItems(id);
    }

    @Override
    public void updateStatus(Integer id, String status) {
        Order order = orderMapper.findById(id);
        if (order != null) {
            order.setStatus(status);
            orderMapper.update(order);
        }
    }

    @Override
    public void delete(Integer id) {
        orderMapper.delete(id);
    }

    private String generateOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(new Date());
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        return "ORD" + dateStr + uuid;
    }
}
