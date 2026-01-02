package com.snack.controller;

import com.snack.entity.User;
import com.snack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> params, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        String username = params.get("username");
        String password = params.get("password");
        User user = userService.login(username, password);
        if (user != null) {
            User loginUser = userService.findByUsername(username);
            session.setAttribute("user", loginUser);
            session.setAttribute("userId", loginUser.getId());
            result.put("success", true);
            result.put("message", "登录成功");
            result.put("user", loginUser);
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
        }
        return result;
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        
        String username = params.get("username");
        String password = params.get("password");
        String phone = params.get("phone");
        
        if (username == null || username.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "昵称不能为空");
            return result;
        }
        
        if (password == null || password.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "密码不能为空");
            return result;
        }
        
        if (phone == null || phone.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "手机号不能为空");
            return result;
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setRealName(username);
        
        User existUser = userService.register(user);
        if (existUser != null) {
            result.put("success", true);
            result.put("message", "注册成功");
        } else {
            result.put("success", false);
            result.put("message", "昵称已存在");
        }
        return result;
    }

    @GetMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        session.removeAttribute("user");
        result.put("success", true);
        result.put("message", "退出成功");
        return result;
    }

    @GetMapping("/info")
    public Map<String, Object> getUserInfo(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            result.put("success", true);
            result.put("user", user);
        } else {
            result.put("success", false);
            result.put("message", "未登录");
        }
        return result;
    }

    @PostMapping("/profile")
    public Map<String, Object> updateProfile(@RequestBody User user, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }
        user.setId(userId);
        userService.update(user);
        User updatedUser = userService.findById(userId);
        session.setAttribute("user", updatedUser);
        result.put("success", true);
        result.put("message", "更新成功");
        result.put("user", updatedUser);
        return result;
    }
}
