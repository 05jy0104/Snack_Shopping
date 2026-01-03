package com.snack.controller;

import com.snack.entity.Order;
import com.snack.entity.OrderItem;
import com.snack.entity.Cart;
import com.snack.entity.User;
import com.snack.service.OrderService;
import com.snack.service.CartService;
import com.snack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/order")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(required = false) Integer userId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        userId = userId != null ? userId : (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }

        List<Order> orders = orderService.findByUserId(userId);
        result.put("success", true);
        result.put("orders", orders);
        return result;
    }

    @GetMapping("/detail/{id}")
    public Map<String, Object> detail(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        Order order = orderService.findByIdWithItems(id);
        result.put("success", true);
        result.put("order", order);
        return result;
    }

    @PostMapping("/return")
    public Map<String, Object> returnOrder(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        Integer orderId = Integer.parseInt(params.get("orderId"));
        String reason = params.get("reason");

        orderService.updateStatus(orderId, "退货中");
        result.put("success", true);
        result.put("message", "退货申请已提交");
        return result;
    }

    @PostMapping("/exchange")
    public Map<String, Object> exchangeOrder(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        Integer orderId = Integer.parseInt(params.get("orderId"));
        String reason = params.get("reason");

        orderService.updateStatus(orderId, "换货中");
        result.put("success", true);
        result.put("message", "换货申请已提交");
        return result;
    }

    @PostMapping("/cancel")
    public Map<String, Object> cancelOrder(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        Integer orderId = Integer.parseInt(params.get("orderId"));

        orderService.updateStatus(orderId, "已取消");
        result.put("success", true);
        result.put("message", "订单已取消");
        return result;
    }

    @PostMapping("/create")
    public Map<String, Object> create(@RequestBody Map<String, String> params, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = params.get("userId") != null ? Integer.parseInt(params.get("userId")) : (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }

        String address = params.get("address");
        String phone = params.get("phone");

        Order order = orderService.createOrder(userId, address, phone);
        if (order != null) {
            result.put("success", true);
            result.put("message", "订单创建成功");
            result.put("order", order);
        } else {
            result.put("success", false);
            result.put("message", "购物车为空");
        }
        return result;
    }
}
