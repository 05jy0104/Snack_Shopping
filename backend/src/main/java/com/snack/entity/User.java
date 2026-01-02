package com.snack.entity;

import lombok.Data;
import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String phone;
    private String email;
    private String address;
    private Double balance;
    private Date createTime;
}
