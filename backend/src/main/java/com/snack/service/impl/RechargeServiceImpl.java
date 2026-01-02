package com.snack.service.impl;

import com.snack.entity.Recharge;
import com.snack.mapper.RechargeMapper;
import com.snack.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class RechargeServiceImpl implements RechargeService {

    @Autowired
    private RechargeMapper rechargeMapper;

    @Override
    public List<Recharge> findByUserId(Integer userId) {
        return rechargeMapper.findByUserId(userId);
    }

    @Override
    public List<Recharge> findAll() {
        return rechargeMapper.findAll();
    }

    @Override
    public void add(Recharge recharge) {
        recharge.setCreateTime(new Date());
        rechargeMapper.insert(recharge);
    }
}
