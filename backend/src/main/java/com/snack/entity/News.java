package com.snack.entity;

import lombok.Data;
import java.util.Date;

@Data
public class News {
    private Integer id;
    private String title;
    private String content;
    private String image;
    private Date createTime;
}
