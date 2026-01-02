package com.snack.service;

import com.snack.entity.User;
import java.util.List;

public interface UserService {
    User login(String username, String password);
    User register(User user);
    User findById(Integer id);
    List<User> findAll();
    void update(User user);
    void delete(Integer id);
    void recharge(Integer userId, Double amount);
}
