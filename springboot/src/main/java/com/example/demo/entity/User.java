package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class User {
    @TableId(value = "id")
    private Integer id;
    private String password;
    private String username;
    private String name;
    private Integer phone;
    private String jurisdiction;
    private String tip;
}

