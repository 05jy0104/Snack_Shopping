package com.snack.controller;

import com.snack.entity.Cart;
import com.snack.entity.Snack;
import com.snack.service.CartService;
import com.snack.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private SnackService snackService;

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Map<String, Object> params, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object userObj = session.getAttribute("user");
        if (userObj == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("success", false);
            result.put("message", "用户ID不存在");
            return result;
        }

        Integer snackId = (Integer) params.get("snackId");
        if (snackId == null) {
            result.put("success", false);
            result.put("message", "商品ID不能为空");
            return result;
        }

        Integer quantity = params.get("quantity") != null ? (Integer) params.get("quantity") : 1;

        Snack snack = snackService.findById(snackId);
        if (snack == null) {
            result.put("success", false);
            result.put("message", "商品不存在");
            return result;
        }

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setSnackId(snackId);
        cart.setSnackName(snack.getName());
        cart.setPrice(snack.getPrice());
        cart.setImage(snack.getImage());
        cart.setQuantity(quantity);

        cartService.add(cart);
        result.put("success", true);
        result.put("message", "添加成功");
        return result;
    }

    @GetMapping("/list")
    public Map<String, Object> list(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }

        List<Cart> cartList = cartService.findByUserId(userId);
        result.put("success", true);
        result.put("cartList", cartList);
        return result;
    }

    @GetMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        cartService.delete(id);
        result.put("success", true);
        result.put("message", "删除成功");
        return result;
    }

    @GetMapping("/clear")
    public Map<String, Object> clear(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        cartService.clear(userId);
        result.put("success", true);
        result.put("message", "清空成功");
        return result;
    }
}
