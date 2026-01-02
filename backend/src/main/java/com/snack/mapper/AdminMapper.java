package com.snack.mapper;

import com.snack.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    Admin findByUsername(String username);
    Admin findById(Integer id);
    void update(Admin admin);
}
