package com.snack;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.snack.mapper")
public class SnackShoppingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnackShoppingApplication.class, args);
        System.out.println("========================================");
        System.out.println("零食商店管理系统启动成功！");
        System.out.println("访问地址: http://localhost:8080");
        System.out.println("========================================");
    }
}
