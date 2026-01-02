package com.snack.mapper;

import com.snack.entity.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    User findById(Integer id);
    List<User> findAll();
    void insert(User user);
    void update(User user);
    void delete(Integer id);
}
