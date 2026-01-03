package com.snack.service.impl;

import com.snack.entity.Snack;
import com.snack.mapper.SnackMapper;
import com.snack.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SnackServiceImpl implements SnackService {

    @Autowired
    private SnackMapper snackMapper;

    @Override
    public List<Snack> findAll() {
        return snackMapper.findAll();
    }

    @Override
    public List<Snack> findByCategoryId(Integer categoryId) {
        return snackMapper.findByCategoryId(categoryId);
    }

    @Override
    public List<Snack> search(String keyword) {
        return snackMapper.search(keyword);
    }

    @Override
    public Snack findById(Integer id) {
        return snackMapper.findById(id);
    }

    @Override
    public void add(Snack snack) {
        snackMapper.insert(snack);
    }

    @Override
    public void update(Snack snack) {
        snackMapper.update(snack);
    }

    @Override
    public void delete(Integer id) {
        snackMapper.delete(id);
    }

    @Override
    public Map<String, Object> findPage(Integer page, Integer pageSize, Integer categoryId, String keyword) {
        Map<String, Object> result = new HashMap<>();
        
        Map<String, Object> params = new HashMap<>();
        params.put("offset", (page - 1) * pageSize);
        params.put("limit", pageSize);
        params.put("categoryId", categoryId);
        params.put("keyword", keyword);
        
        List<Snack> snacks = snackMapper.findPage(params);
        int total = snackMapper.countByCondition(params);
        int totalPages = (int) Math.ceil((double) total / pageSize);
        
        result.put("snacks", snacks);
        result.put("total", total);
        result.put("totalPages", totalPages);
        result.put("currentPage", page);
        result.put("pageSize", pageSize);
        
        return result;
    }
}
