package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("room")
@Data
public class Room {
    private Integer roomtype;
    @TableId(value = "roomid")
    private Integer roomid;
    private Integer day;
    private Integer userid;
}
