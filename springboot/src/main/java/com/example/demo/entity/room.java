package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("room")
@Data
public class room {
    @TableId(value = "roomId", type = IdType.AUTO)
    private Integer roomId;
    private Integer type;
    private Integer identifier;
    private Integer day;
}
