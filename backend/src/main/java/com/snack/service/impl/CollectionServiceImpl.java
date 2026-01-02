package com.snack.service.impl;

import com.snack.entity.UserCollection;
import com.snack.mapper.CollectionMapper;
import com.snack.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public List<UserCollection> findByUserId(Integer userId) {
        return collectionMapper.findByUserId(userId);
    }

    @Override
    public void add(UserCollection collection) {
        UserCollection existCollection = collectionMapper.findByUserIdAndSnackId(collection.getUserId(), collection.getSnackId());
        if (existCollection == null) {
            collection.setCreateTime(new Date());
            collectionMapper.insert(collection);
        }
    }

    @Override
    public void delete(Integer id) {
        collectionMapper.delete(id);
    }

    @Override
    public void deleteByUserIdAndSnackId(Integer userId, Integer snackId) {
        collectionMapper.deleteByUserIdAndSnackId(userId, snackId);
    }
}
