package com.snack.service;

import com.snack.entity.Favorite;
import java.util.List;

public interface FavoriteService {
    List<Favorite> findByUserId(Integer userId);
    void add(Favorite favorite);
    void delete(Integer id);
    void deleteByUserIdAndSnackId(Integer userId, Integer snackId);
}
