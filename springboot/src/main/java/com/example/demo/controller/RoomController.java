package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Example;
import com.example.demo.mapper.ExampleMapper;
import org.springframework.web.bind.annotation.*;
import com.example.demo.common.Result;

import com.example.demo.entity.Room;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.entity.RoomStandard;
import com.example.demo.entity.User;

import javax.annotation.Resource;
import java.sql.Statement;


//数据交互
@RestController
@RequestMapping("/room")
public class RoomController {

    @Resource
    RoomMapper roomMapper;

    // 增加房间
    @PostMapping
    public Result<?> save(@RequestBody Room room){
        if(room.getRoomid()==null){
            return Result.error("-1","添加房间失败，请添加房间ID");
        }
        roomMapper.insert(room);
        return Result.success();
    }

    // 更新房间信息
    @PutMapping
    public Result<?> update(@RequestBody Room room){
        roomMapper.updateById(room);
        return Result.success();
    }
    @GetMapping("/croom/{roomID}")
    public Result<?> update2(@PathVariable Integer roomID){
        LambdaUpdateWrapper<Room> wrapper= Wrappers.lambdaUpdate();
        wrapper.eq(Room::getRoomid, roomID).set(Room::getDay, null).set(Room::getUserid, null);
        roomMapper.update(null, wrapper);
        return Result.success();
    }

    // 删除房间信息
    @DeleteMapping("/{roomID}")
    public Result<?> delete(@PathVariable Integer roomID){
        roomMapper.deleteById(roomID);
        return Result.success();
    }

    // 按roomId or name or typeName查询房间页信息
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){

        LambdaQueryWrapper<Room> wrapper= Wrappers.lambdaQuery();
        if(StringUtils.isNotBlank(search)) {
            Integer roomId = Integer.parseInt(search);
            wrapper.like(Room::getRoomid, roomId);
        }

        Page<Room> userPage= roomMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);
    }


}
