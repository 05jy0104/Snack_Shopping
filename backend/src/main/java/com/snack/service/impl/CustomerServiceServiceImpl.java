package com.snack.service.impl;

import com.snack.entity.CustomerService;
import com.snack.mapper.CustomerServiceMapper;
import com.snack.service.CustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceServiceImpl implements CustomerServiceService {

    @Autowired
    private CustomerServiceMapper customerServiceMapper;

    @Override
    public List<CustomerService> findByUserId(Integer userId) {
        return customerServiceMapper.findByUserId(userId);
    }

    @Override
    public List<CustomerService> findAll() {
        return customerServiceMapper.findAll();
    }

    @Override
    public CustomerService findById(Integer id) {
        return customerServiceMapper.findById(id);
    }

    @Override
    public void add(CustomerService customerService) {
        customerService.setCreateTime(new Date());
        customerServiceMapper.insert(customerService);
    }

    @Override
    public void reply(Integer id, String reply) {
        CustomerService customerService = customerServiceMapper.findById(id);
        if (customerService != null) {
            customerService.setReply(reply);
            customerService.setReplyTime(new Date());
            customerServiceMapper.update(customerService);
        }
    }

    @Override
    public void delete(Integer id) {
        customerServiceMapper.delete(id);
    }
}
