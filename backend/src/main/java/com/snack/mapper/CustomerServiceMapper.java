package com.snack.mapper;

import com.snack.entity.CustomerService;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CustomerServiceMapper {
    List<CustomerService> findByUserId(Integer userId);
    List<CustomerService> findAll();
    CustomerService findById(Integer id);
    void insert(CustomerService customerService);
    void update(CustomerService customerService);
    void delete(Integer id);
}
