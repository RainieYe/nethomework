package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Example;
import com.example.demo.mapper.ExampleMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//数据交互
@RestController
@RequestMapping("/example")
public class ExampleController {

    @Resource
    ExampleMapper exampleMapper;


    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){

        String mysql = "DROP TABLE IF EXISTS example;\n" +
                "CREATE TABLE example AS SELECT * FROM roomstandard rs LEFT JOIN room r ON r.roomtype = rs.type LEFT JOIN `user` u ON r.userid = u.id;\n" +
                "ALTER TABLE example DROP COLUMN roomtype, DROP COLUMN userid;\n" +
                "ALTER TABLE example ADD PRIMARY KEY(roomid);";

        exampleMapper.dynamicSql(mysql);
        LambdaQueryWrapper<Example> wrapper = Wrappers.lambdaQuery();

        if(StringUtils.isNotBlank(search)) {
            if(search.equals("空房") == false){
                try {
                    Integer roomId = Integer.valueOf(search);
                    wrapper.like(Example::getId,roomId);//.or().like(Example::getRoomid, roomId);
                } catch (Exception exc) {
                    wrapper.like(Example::getName, search).or().like(Example::getTypename, search);
                }
            }else{
                wrapper.isNull(Example::getId);
            }

        }
        Page<Example> userPage= exampleMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);
    }


}
