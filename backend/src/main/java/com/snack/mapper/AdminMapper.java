package com.snack.mapper;

import com.snack.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    Admin findByUsername(String username);
    Admin findById(Integer id);
    List<Admin> findAll();
    void insert(Admin admin);
    void update(Admin admin);
    void delete(Integer id);
}
