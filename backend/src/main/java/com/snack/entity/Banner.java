package com.snack.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Banner {
    private Integer id;
    private String title;
    private String image;
    private String link;
    private Integer sort;
    private Date createTime;
}
