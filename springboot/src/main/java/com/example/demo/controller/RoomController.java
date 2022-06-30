package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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

//数据交互
@RestController
@RequestMapping("/room")
public class RoomController {

    @Resource
    RoomMapper roomMapper;
    @Resource
    ExampleMapper exampleMapper;

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
        roomMapper.insert(room);
        return Result.success();
    }

    // 删除房间信息
    @DeleteMapping("/{roomID}")
    public Result<?> delete(@PathVariable Long roomID){
        roomMapper.deleteById(roomID);
        return Result.success();
    }

    // 按roomId or name or typeName查询房间页信息
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){

        String mysql = "DROP TABLE IF EXISTS example;\n" +
                "CREATE TABLE example AS SELECT * FROM roomstandard rs LEFT JOIN room r ON r.roomType = rs.type LEFT JOIN `user` u ON r.userId = u.id;\n" +
                "ALTER TABLE example DROP COLUMN roomType, DROP COLUMN userId;\n" +
                "ALTER TABLE example ADD PRIMARY KEY(roomId);";
        exampleMapper.dynamicSql(mysql);

        LambdaQueryWrapper<Example> wrapper= Wrappers.lambdaQuery();
        if(StringUtils.isNotBlank(search)) {
            try {
                Integer roomId = Integer.valueOf(search);
                wrapper.eq(Example::getRoomid, roomId);
            } catch (StringIndexOutOfBoundsException e) {
                LambdaQueryWrapper<User> wrapperUser = Wrappers.lambdaQuery();
                LambdaQueryWrapper<RoomStandard> wrapperStandard = Wrappers.lambdaQuery();
                if (wrapperUser.eq(User::getName, search) != null) {
                    Integer userid = wrapperUser.eq(User::getName, search).getEntity().getId();
                    wrapper.eq(Example::getId, userid);
                }else if (wrapperStandard.eq(RoomStandard::getTypename, search) != null) {
                    Integer type = wrapperStandard.eq(RoomStandard::getTypename, search).getEntity().getType();
                    wrapper.eq(Example::getType, type);
                }
            }
        }

        Page<Example> userPage= exampleMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);
    }


}
