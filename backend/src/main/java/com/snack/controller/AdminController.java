package com.snack.controller;

import com.snack.entity.Admin;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> params, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        String username = params.get("username");
        String password = params.get("password");
        if ("admin".equals(username) && "admin123".equals(password)) {
            Admin admin = new Admin();
            
            session.setAttribute("admin", admin);
            result.put("success", true);
            result.put("message", "登录成功");
            result.put("admin", admin);
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
        }
        return result;
    }

    @GetMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        session.removeAttribute("admin");
        result.put("success", true);
        result.put("message", "退出成功");
        return result;
    }

    @GetMapping("/info")
    public Map<String, Object> info(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Object admin = session.getAttribute("admin");
        if (admin != null) {
            result.put("success", true);
            result.put("admin", admin);
        } else {
            result.put("success", false);
            result.put("message", "未登录");
        }
        return result;
    }
}
