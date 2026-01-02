package com.snack.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Cart {
    private Integer id;
    private Integer userId;
    private Integer snackId;
    private String snackName;
    private Double price;
    private String image;
    private Integer quantity;
    private Date createTime;
}
