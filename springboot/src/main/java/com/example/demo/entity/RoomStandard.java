package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("roomstandard")
@Data
public class RoomStandard {
    @TableId(value = "type")
    private Integer type;
    private String typename;
    private Integer area;
    private Integer bedNum;
    private Integer priceToday;
}
