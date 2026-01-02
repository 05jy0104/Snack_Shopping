package com.snack.controller;

import com.snack.entity.User;
import com.snack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/user")
@CrossOrigin(origins = "*")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Map<String, Object> list(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (session.getAttribute("admin") == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }
        List<User> users = userService.findAll();
        result.put("success", true);
        result.put("users", users);
        return result;
    }

    @GetMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        userService.delete(id);
        result.put("success", true);
        result.put("message", "删除成功");
        return result;
    }
}
