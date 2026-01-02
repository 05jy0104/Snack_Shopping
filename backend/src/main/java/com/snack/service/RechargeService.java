package com.snack.service;

import com.snack.entity.Recharge;
import java.util.List;

public interface RechargeService {
    List<Recharge> findByUserId(Integer userId);
    List<Recharge> findAll();
    void add(Recharge recharge);
}
