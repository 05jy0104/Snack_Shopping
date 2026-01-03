package com.snack.controller;

import com.snack.entity.Snack;
import com.snack.entity.Category;
import com.snack.service.SnackService;
import com.snack.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/snack")
@CrossOrigin(origins = "*")
public class UserSnackController {

    @Autowired
    private SnackService snackService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public Map<String, Object> list(@RequestParam(required = false) Integer categoryId, 
                       @RequestParam(required = false) String keyword,
                       @RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "9") Integer pageSize) {
        Map<String, Object> result = new HashMap<>();
        List<Category> categories = categoryService.findAll();
        result.put("categories", categories);

        Map<String, Object> pageResult = snackService.findPage(page, pageSize, categoryId, keyword);
        result.putAll(pageResult);
        result.put("success", true);
        result.put("categoryId", categoryId);
        result.put("keyword", keyword);
        return result;
    }

    @GetMapping("/detail/{id}")
    public Map<String, Object> detail(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        Snack snack = snackService.findById(id);
        result.put("success", true);
        result.put("snack", snack);
        return result;
    }
}
