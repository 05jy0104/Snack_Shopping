package com.snack.mapper;

import com.snack.entity.Favorite;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface FavoriteMapper {
    List<Favorite> findByUserId(Integer userId);
    Favorite findByUserIdAndSnackId(Integer userId, Integer snackId);
    void insert(Favorite favorite);
    void delete(Integer id);
    void deleteByUserIdAndSnackId(Integer userId, Integer snackId);
}
