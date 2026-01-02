package com.snack.entity;

import lombok.Data;
import java.util.Date;

@Data
public class CustomerService {
    private Integer id;
    private Integer userId;
    private String username;
    private String content;
    private String reply;
    private Date createTime;
    private Date replyTime;
}
