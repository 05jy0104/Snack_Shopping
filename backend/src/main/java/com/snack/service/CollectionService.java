package com.snack.service;

import com.snack.entity.Collection;
import java.util.List;

public interface CollectionService {
    List<Collection> findByUserId(Integer userId);
    void add(Collection collection);
    void delete(Integer id);
    void deleteByUserIdAndSnackId(Integer userId, Integer snackId);
}
