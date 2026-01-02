package com.snack.mapper;

import com.snack.entity.Collection;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CollectionMapper {
    List<Collection> findByUserId(Integer userId);
    Collection findByUserIdAndSnackId(Integer userId, Integer snackId);
    void insert(Collection collection);
    void delete(Integer id);
    void deleteByUserIdAndSnackId(Integer userId, Integer snackId);
}
