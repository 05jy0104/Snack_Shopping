package com.snack.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Comment {
    private Integer id;
    private Integer userId;
    private String username;
    private Integer snackId;
    private String snackName;
    private String content;
    private Date createTime;
}
