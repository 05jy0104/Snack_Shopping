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

    @Autowired
    private com.snack.mapper.CartMapper cartMapper;

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Map<String, Object> params, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        System.out.println("=== Cart Add Debug ===");
        System.out.println("Params: " + params);
        System.out.println("Session userId: " + session.getAttribute("userId"));
        
        Integer userId = params.get("userId") != null ? (Integer) params.get("userId") : (Integer) session.getAttribute("userId");
        System.out.println("Final userId: " + userId);
        
        if (userId == null) {
            result.put("success", false);
            result.put("message", "用户ID不存在");
            return result;
        }

        Object snackIdObj = params.get("snackId");
        Integer snackId = null;
        if (snackIdObj != null) {
            if (snackIdObj instanceof Integer) {
                snackId = (Integer) snackIdObj;
            } else if (snackIdObj instanceof String) {
                try {
                    snackId = Integer.parseInt((String) snackIdObj);
                } catch (NumberFormatException e) {
                    result.put("success", false);
                    result.put("message", "商品ID格式错误");
                    return result;
                }
            }
        }
        
        if (snackId == null) {
            result.put("success", false);
            result.put("message", "商品ID不能为空");
            return result;
        }

        Object quantityObj = params.get("quantity");
        Integer quantity = 1;
        if (quantityObj != null) {
            if (quantityObj instanceof Integer) {
                quantity = (Integer) quantityObj;
            } else if (quantityObj instanceof String) {
                try {
                    quantity = Integer.parseInt((String) quantityObj);
                } catch (NumberFormatException e) {
                    quantity = 1;
                }
            }
        }

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
    public Map<String, Object> list(@RequestParam(required = false) Integer userId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (userId == null) {
            userId = (Integer) session.getAttribute("userId");
        }
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
    public Map<String, Object> clear(@RequestParam(required = false) Integer userId, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (userId == null) {
            userId = (Integer) session.getAttribute("userId");
        }
        cartService.clear(userId);
        result.put("success", true);
        result.put("message", "清空成功");
        return result;
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestBody Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        
        Object idObj = params.get("id");
        if (idObj == null) {
            result.put("success", false);
            result.put("message", "购物车ID不能为空");
            return result;
        }
        
        Integer id = null;
        if (idObj instanceof Integer) {
            id = (Integer) idObj;
        } else if (idObj instanceof String) {
            try {
                id = Integer.parseInt((String) idObj);
            } catch (NumberFormatException e) {
                result.put("success", false);
                result.put("message", "购物车ID格式错误");
                return result;
            }
        }
        
        Object quantityObj = params.get("quantity");
        Integer quantity = null;
        if (quantityObj != null) {
            if (quantityObj instanceof Integer) {
                quantity = (Integer) quantityObj;
            } else if (quantityObj instanceof String) {
                try {
                    quantity = Integer.parseInt((String) quantityObj);
                } catch (NumberFormatException e) {
                    result.put("success", false);
                    result.put("message", "数量格式错误");
                    return result;
                }
            }
        }
        
        if (quantity == null || quantity < 1) {
            result.put("success", false);
            result.put("message", "数量必须大于0");
            return result;
        }
        
        Cart cart = cartMapper.findById(id);
        if (cart == null) {
            result.put("success", false);
            result.put("message", "购物车项不存在");
            return result;
        }
        
        cart.setQuantity(quantity);
        cartService.update(cart);
        result.put("success", true);
        result.put("message", "更新成功");
        return result;
    }
}
