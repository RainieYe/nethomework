package com.example.demo.controller;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Example;
import com.example.demo.mapper.ExampleMapper;
import com.example.demo.mapper.RoomStandardMapper;
import org.springframework.web.bind.annotation.*;
import com.example.demo.common.Result;

import com.example.demo.entity.Room;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.entity.RoomStandard;
import com.example.demo.entity.User;

import javax.annotation.Resource;
import java.sql.Statement;
import java.util.List;

//数据交互
@RestController
@RequestMapping("/show")
public class showController {
    @Resource
    RoomMapper roomMapper;
    // 按roomId or name or typeName查询房间页信息
    @GetMapping
    public Result<?> showNum(){

        LambdaQueryWrapper<Room> wrapper= Wrappers.lambdaQuery();
        int[] countList = new int[7];

        List<Room> count= roomMapper.selectList(wrapper);
        for(Room i : count){
            switch (i.getRoomtype())
            {
                case 1:countList[0]++;break;
                case 2:countList[1]++;break;
                case 3:countList[2]++;break;
                case 4:countList[3]++;break;
                case 5:countList[4]++;break;
                case 6:countList[5]++;break;
                case 7:countList[6]++;break;
            }
        }
        return Result.success(countList);
    }
    @Resource
    RoomStandardMapper roomStandardMapper;
    @GetMapping("/showall")
    public Result<?> findPage(){
        LambdaQueryWrapper<RoomStandard> wrapper = Wrappers.lambdaQuery();
        int[] countList = new int[7];
        List<RoomStandard> count= roomStandardMapper.selectList(wrapper);
        for(RoomStandard i : count){
            switch (i.getType())
            {
                case 1:countList[0]=i.getBednum();break;
                case 2:countList[1]=i.getBednum();break;
                case 3:countList[2]=i.getBednum();break;
                case 4:countList[3]=i.getBednum();break;
                case 5:countList[4]=i.getBednum();break;
                case 6:countList[5]=i.getBednum();break;
                case 7:countList[6]=i.getBednum();break;
            }
        }
        return Result.success(countList);
    }
}
