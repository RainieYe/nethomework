package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("room")
@Data
public class Room {
    @TableId(value = "roomId")
    private Integer roomType;
    private Integer roomId;
    private Integer day;
    private Integer userId;
}
