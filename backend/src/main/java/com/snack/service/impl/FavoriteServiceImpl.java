package com.snack.service.impl;

import com.snack.entity.Favorite;
import com.snack.mapper.FavoriteMapper;
import com.snack.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public List<Favorite> findByUserId(Integer userId) {
        return favoriteMapper.findByUserId(userId);
    }

    @Override
    public void add(Favorite favorite) {
        Favorite existFavorite = favoriteMapper.findByUserIdAndSnackId(favorite.getUserId(), favorite.getSnackId());
        if (existFavorite == null) {
            favorite.setCreateTime(new Date());
            favoriteMapper.insert(favorite);
        }
    }

    @Override
    public void delete(Integer id) {
        favoriteMapper.delete(id);
    }

    @Override
    public void deleteByUserIdAndSnackId(Integer userId, Integer snackId) {
        favoriteMapper.deleteByUserIdAndSnackId(userId, snackId);
    }
}
