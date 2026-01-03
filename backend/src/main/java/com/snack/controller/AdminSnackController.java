package com.snack.controller;

import com.snack.entity.Snack;
import com.snack.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/snack")
@CrossOrigin(origins = "*")
public class AdminSnackController {

    @Autowired
    private SnackService snackService;

    @Value("${file.upload.path}")
    private String uploadPath;

    @Value("${file.access.path}")
    private String accessPath;

    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> result = new HashMap<>();
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
    public Map<String, Object> edit(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
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

    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            if (file.isEmpty()) {
                result.put("success", false);
                result.put("message", "文件不能为空");
                return result;
            }

            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = UUID.randomUUID().toString() + extension;

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File destFile = new File(uploadDir, newFilename);
            file.transferTo(destFile);

            String imageUrl = accessPath + newFilename;
            
            result.put("success", true);
            result.put("message", "上传成功");
            result.put("imageUrl", imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "上传失败: " + e.getMessage());
        }
        
        return result;
    }
}
