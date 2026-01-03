package com.snack.controller;

import com.snack.entity.Admin;
import com.snack.entity.User;
import com.snack.mapper.AdminMapper;
import com.snack.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

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

    @GetMapping("/user/list")
    public Map<String, Object> listUsers() {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> allUsers = new ArrayList<>();
        
        List<User> users = userMapper.findAll();
        for (User user : users) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", user.getId());
            userMap.put("username", user.getUsername());
            userMap.put("realName", user.getRealName());
            userMap.put("phone", user.getPhone());
            userMap.put("email", user.getEmail());
            userMap.put("type", "user");
            userMap.put("createTime", user.getCreateTime());
            allUsers.add(userMap);
        }
        
        List<Admin> admins = adminMapper.findAll();
        for (Admin admin : admins) {
            Map<String, Object> adminMap = new HashMap<>();
            adminMap.put("id", admin.getId());
            adminMap.put("username", admin.getUsername());
            adminMap.put("realName", admin.getRealName());
            adminMap.put("phone", "-");
            adminMap.put("email", "-");
            adminMap.put("type", "admin");
            adminMap.put("createTime", admin.getCreateTime());
            allUsers.add(adminMap);
        }
        
        result.put("success", true);
        result.put("users", allUsers);
        return result;
    }

    @PostMapping("/user/add")
    public Map<String, Object> addUser(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        String username = params.get("username");
        String password = params.get("password");
        String realName = params.get("realName");
        String phone = params.get("phone");
        String email = params.get("email");
        
        if (username == null || username.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "用户名不能为空");
            return result;
        }
        
        if (password == null || password.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "密码不能为空");
            return result;
        }
        
        User existingUser = userMapper.findByUsername(username);
        if (existingUser != null) {
            result.put("success", false);
            result.put("message", "用户名已存在");
            return result;
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRealName(realName);
        user.setPhone(phone);
        user.setEmail(email);
        
        try {
            userMapper.insert(user);
            result.put("success", true);
            result.put("message", "添加成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "添加失败");
        }
        
        return result;
    }

    @GetMapping("/user/detail/{id}")
    public Map<String, Object> getUserDetail(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        User user = userMapper.findById(id);
        
        if (user != null) {
            result.put("success", true);
            result.put("user", user);
        } else {
            result.put("success", false);
            result.put("message", "用户不存在");
        }
        
        return result;
    }

    @PostMapping("/user/edit")
    public Map<String, Object> editUser(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        Integer id = Integer.parseInt(params.get("id"));
        String realName = params.get("realName");
        String phone = params.get("phone");
        String email = params.get("email");
        
        User user = userMapper.findById(id);
        if (user == null) {
            result.put("success", false);
            result.put("message", "用户不存在");
            return result;
        }
        
        user.setRealName(realName);
        user.setPhone(phone);
        user.setEmail(email);
        
        try {
            userMapper.update(user);
            result.put("success", true);
            result.put("message", "更新成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败");
        }
        
        return result;
    }

    @PostMapping("/user/resetPassword")
    public Map<String, Object> resetPassword(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        Integer id = Integer.parseInt(params.get("id"));
        String newPassword = params.get("newPassword");
        
        if (newPassword == null || newPassword.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "新密码不能为空");
            return result;
        }
        
        User user = userMapper.findById(id);
        if (user == null) {
            result.put("success", false);
            result.put("message", "用户不存在");
            return result;
        }
        
        user.setPassword(newPassword);
        
        try {
            userMapper.update(user);
            result.put("success", true);
            result.put("message", "密码重置成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "密码重置失败");
        }
        
        return result;
    }

    @GetMapping("/admin/list")
    public Map<String, Object> listAdmins() {
        Map<String, Object> result = new HashMap<>();
        List<Admin> admins = adminMapper.findAll();
        result.put("success", true);
        result.put("admins", admins);
        return result;
    }

    @GetMapping("/admin/detail/{id}")
    public Map<String, Object> getAdminDetail(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        Admin admin = adminMapper.findById(id);
        
        if (admin != null) {
            result.put("success", true);
            result.put("admin", admin);
        } else {
            result.put("success", false);
            result.put("message", "管理员不存在");
        }
        
        return result;
    }

    @PostMapping("/admin/add")
    public Map<String, Object> addAdmin(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        String username = params.get("username");
        String password = params.get("password");
        String realName = params.get("realName");
        
        if (username == null || username.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "用户名不能为空");
            return result;
        }
        
        if (password == null || password.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "密码不能为空");
            return result;
        }
        
        Admin existingAdmin = adminMapper.findByUsername(username);
        if (existingAdmin != null) {
            result.put("success", false);
            result.put("message", "用户名已存在");
            return result;
        }
        
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setRealName(realName);
        admin.setCreateTime(new java.util.Date());
        
        try {
            adminMapper.insert(admin);
            result.put("success", true);
            result.put("message", "添加成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "添加失败");
        }
        
        return result;
    }

    @PostMapping("/admin/edit")
    public Map<String, Object> editAdmin(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        Integer id = Integer.parseInt(params.get("id"));
        String realName = params.get("realName");
        
        Admin admin = adminMapper.findById(id);
        if (admin == null) {
            result.put("success", false);
            result.put("message", "管理员不存在");
            return result;
        }
        
        admin.setRealName(realName);
        
        try {
            adminMapper.update(admin);
            result.put("success", true);
            result.put("message", "更新成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败");
        }
        
        return result;
    }

    @PostMapping("/admin/delete")
    public Map<String, Object> deleteAdmin(@RequestBody Map<String, String> params) {
        Map<String, Object> result = new HashMap<>();
        Integer id = Integer.parseInt(params.get("id"));
        
        Admin admin = adminMapper.findById(id);
        if (admin == null) {
            result.put("success", false);
            result.put("message", "管理员不存在");
            return result;
        }
        
        try {
            adminMapper.delete(id);
            result.put("success", true);
            result.put("message", "删除成功");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败");
        }
        
        return result;
    }
}
