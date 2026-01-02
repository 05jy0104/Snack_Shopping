package com.snack.mapper;

import com.snack.entity.UserCollection;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CollectionMapper {
    List<UserCollection> findByUserId(Integer userId);
    UserCollection findByUserIdAndSnackId(Integer userId, Integer snackId);
    void insert(UserCollection collection);
    void delete(Integer id);
    void deleteByUserIdAndSnackId(Integer userId, Integer snackId);
}
