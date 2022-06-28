package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("roomstandard")
@Data
public class roomStandard {
    @TableId(value = "type", type = IdType.AUTO)
    private Integer type;
    private String name;
    private Integer area;
    private Integer bedNum;
    private Integer priceToday;
}
