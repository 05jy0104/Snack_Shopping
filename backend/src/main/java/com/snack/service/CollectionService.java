package com.snack.service;

import com.snack.entity.UserCollection;
import java.util.List;

public interface CollectionService {
    List<UserCollection> findByUserId(Integer userId);
    void add(UserCollection collection);
    void delete(Integer id);
    void deleteByUserIdAndSnackId(Integer userId, Integer snackId);
}
