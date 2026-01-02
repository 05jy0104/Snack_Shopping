package com.snack.controller;

import com.snack.entity.Order;
import com.snack.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/order")
@CrossOrigin(origins = "*")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public Map<String, Object> list(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (session.getAttribute("admin") == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }
        List<Order> orders = orderService.findAll();
        result.put("success", true);
        result.put("orders", orders);
        return result;
    }

    @PostMapping("/updateStatus")
    public Map<String, Object> updateStatus(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        Integer id = (Integer) params.get("id");
        String status = (String) params.get("status");
        orderService.updateStatus(id, status);
        result.put("success", true);
        result.put("message", "更新成功");
        return result;
    }
}
