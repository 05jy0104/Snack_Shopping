package com.snack.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Category {
    private Integer id;
    private String name;
    private String description;
    private Date createTime;
}
