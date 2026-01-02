package com.snack.entity;

import lombok.Data;
import java.util.Date;

@Data
public class OrderItem {
    private Integer id;
    private Integer orderId;
    private Integer snackId;
    private String snackName;
    private Double price;
    private Integer quantity;
    private Double subtotal;
}
