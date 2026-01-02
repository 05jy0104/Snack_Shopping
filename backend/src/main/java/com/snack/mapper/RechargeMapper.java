package com.snack.mapper;

import com.snack.entity.Recharge;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface RechargeMapper {
    List<Recharge> findByUserId(Integer userId);
    List<Recharge> findAll();
    void insert(Recharge recharge);
}
