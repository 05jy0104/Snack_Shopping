package com.snack.controller;

import com.snack.entity.Category;
import com.snack.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/category")
@CrossOrigin(origins = "*")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public Map<String, Object> list(HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        if (session.getAttribute("admin") == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }
        List<Category> categories = categoryService.findAll();
        result.put("success", true);
        result.put("categories", categories);
        return result;
    }

    @PostMapping("/add")
    public Map<String, Object> add(@RequestBody Category category) {
        Map<String, Object> result = new HashMap<>();
        categoryService.add(category);
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
        Category category = categoryService.findById(id);
        result.put("success", true);
        result.put("category", category);
        return result;
    }

    @PostMapping("/edit")
    public Map<String, Object> edit(@RequestBody Category category) {
        Map<String, Object> result = new HashMap<>();
        categoryService.update(category);
        result.put("success", true);
        result.put("message", "更新成功");
        return result;
    }

    @GetMapping("/delete/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        categoryService.delete(id);
        result.put("success", true);
        result.put("message", "删除成功");
        return result;
    }
}
