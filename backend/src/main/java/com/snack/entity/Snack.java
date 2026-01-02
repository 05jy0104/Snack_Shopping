package com.snack.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Snack {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String categoryName;
    private Double price;
    private String image;
    private String description;
    private Integer stock;
    private Integer sales;
    private Date createTime;
}
