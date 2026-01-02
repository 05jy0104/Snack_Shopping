package com.snack.controller;

import com.snack.entity.Snack;
import com.snack.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/snack")
@CrossOrigin(origins = "*")
public class AdminSnackController {

    @Autowired
    private SnackService snackService;

    @GetMapping("/list")
    public Map<String, Object> list(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (session.getAttribute("admin") == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }
        List<Snack> snacks = snackService.findAll();
        result.put("success", true);
        result.put("snacks", snacks);
        return result;
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Snack snack) {
        Map<String, Object> result = new HashMap<>();
        snackService.add(snack);
        result.put("success", true);
        result.put("message", "添加成功");
        return result;
    }

    @GetMapping("/edit/{id}")
    public Map<String, Object> edit(@PathVariable Integer id, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (session.getAttribute("admin") == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }
        Snack snack = snackService.findById(id);
        result.put("success", true);
        result.put("snack", snack);
        return result;
    }

    @PostMapping("/edit")
    public Map<String, Object> edit(@RequestBody Snack snack) {
        Map<String, Object> result = new HashMap<>();
        snackService.update(snack);
        result.put("success", true);
        result.put("message", "更新成功");
        return result;
    }

    @GetMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        snackService.delete(id);
        result.put("success", true);
        result.put("message", "删除成功");
        return result;
    }
}
