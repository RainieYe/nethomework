package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String password;
    private String username;
    private Integer age;
    private String sex;
    private String address;
    private String department;
    private String post;
    private String excel;
    private String outpatienttime;
    private String professionaltitles;
    private String jurisdiction;
}
