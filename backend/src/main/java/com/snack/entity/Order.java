package com.snack.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Order {
    private Integer id;
    private Integer userId;
    private String username;
    private String orderNo;
    private Double totalAmount;
    private String status;
    private String address;
    private String phone;
    private Date createTime;
}
