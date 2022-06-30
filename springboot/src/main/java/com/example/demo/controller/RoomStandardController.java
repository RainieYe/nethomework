package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Example;
import com.example.demo.entity.Room;
import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.RoomStandard;
import com.example.demo.mapper.RoomStandardMapper;

import javax.annotation.Resource;

//数据交互
@RestController
@RequestMapping("/roomstandard")
public class RoomStandardController {

    @Resource
    RoomStandardMapper roomStandardMapper;

    // 增加房间种类
    @PostMapping
    public Result<?> save(@RequestBody RoomStandard roomstandard){
        if(roomstandard.getType()==null){
            return Result.error("-2","添加房间种类失败，请添加房间种类");
        }
        QueryWrapper<RoomStandard> Wrapper=new QueryWrapper<>();
        for(int i=0; i < roomStandardMapper.selectCount(Wrapper); i++) {
            if(roomstandard.getType() == i){
                return Result.error("-2","添加房间种类失败，此种类已存在");
            }
        }
        roomStandardMapper.insert(roomstandard);
        return Result.success();
    }

    // 更新房间种类
    @PutMapping
    public Result<?> update(@RequestBody RoomStandard roomstandard){
        if(roomstandard.getType()==null){
            return Result.error("-2","更新房间种类失败，请填写房间种类");
        }
        roomStandardMapper.updateById(roomstandard);
        return Result.success();
    }

    // 删除房间种类
    @DeleteMapping("/{type}")
    public Result<?> delete(@PathVariable Integer type){
        roomStandardMapper.deleteById(type);
        return Result.success();
    }

    // 按type or typeName查询房间类型
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){

        LambdaQueryWrapper<RoomStandard> wrapper= Wrappers.lambdaQuery();
        if(StringUtils.isNotBlank(search)) {
            try {
                Integer type = Integer.parseInt(search);
                wrapper.like(RoomStandard::getType, type);
            } catch (Exception exc) {

                wrapper.like(RoomStandard::getTypename, search);
            }
        }

        Page<RoomStandard> userPage=roomStandardMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);
    }


}
