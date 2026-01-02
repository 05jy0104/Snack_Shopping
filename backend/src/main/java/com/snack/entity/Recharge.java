package com.snack.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Recharge {
    private Integer id;
    private Integer userId;
    private String username;
    private Double amount;
    private Date createTime;
}
