package com.snack.service;

import com.snack.entity.CustomerService;
import java.util.List;

public interface CustomerServiceService {
    List<CustomerService> findByUserId(Integer userId);
    List<CustomerService> findAll();
    CustomerService findById(Integer id);
    void add(CustomerService customerService);
    void reply(Integer id, String reply);
    void delete(Integer id);
}
