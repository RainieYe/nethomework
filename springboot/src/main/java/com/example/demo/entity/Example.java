package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("example")
@Data
public class Example {
    private String typename;
    private Integer area;
    private Integer bednum;
    private Integer pricetoday;
    private Integer type;
    @TableId(value = "roomid")
    private Integer roomid;
    private Integer day;
    private Integer id;
    private String password;
    private String username;
    private String name;
    private Integer phone;
    private String jurisdiction;
    private String tip;
}
