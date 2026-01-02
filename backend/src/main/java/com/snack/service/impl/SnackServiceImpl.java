package com.snack.service.impl;

import com.snack.entity.Snack;
import com.snack.mapper.SnackMapper;
import com.snack.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
}
