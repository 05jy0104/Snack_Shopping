package com.snack.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Collection {
    private Integer id;
    private Integer userId;
    private Integer snackId;
    private String snackName;
    private String image;
    private Date createTime;
}
