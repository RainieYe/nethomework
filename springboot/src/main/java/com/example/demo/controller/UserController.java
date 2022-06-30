package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;

//数据交互
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserMapper userMapper;

    // 登录
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername())
                                                        .eq(User::getPassword,user.getPassword()));
        user.setJurisdiction("2");
        if(res==null){
            return Result.error("-3","用户名或密码错误");
        }else{
            if(user.getPassword()==null){
                user.setPassword("123456");
            }
        // userMapper.insert(user);
            return Result.success(res);
        }
    }

    // 注册
    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){
//        查看用户名是否存在
        User res=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        User res1=userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getPhone,user.getPhone()));
        if(res!=null){
            return Result.error("-3","用户名已存在");
        }
        if(res1==null){
            user.setPhone(null);
        }
        user.setJurisdiction("2");
        userMapper.insert(user);
        return Result.success();
    }

    // 增加用户
    @PostMapping
    public Result<?> save(@RequestBody User user){
        if(user.getPassword()==null){
            user.setPassword("123456");
            user.setJurisdiction("2");
        }
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getId,user.getId()));
        User res1 = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,user.getUsername()));
        // System.out.println(res);
        // System.out.println(res1);

        if(res != null){
            //System.out.println("0000000000");
            LambdaUpdateWrapper<User> wrapper= Wrappers.lambdaUpdate();

            if(res1.equals(res)) {
                //System.out.println("0000000011");
                wrapper.eq(User::getId,user.getId()).set(User::getUsername,user.getUsername()).set(User::getName, user.getName()).set(User::getPhone, user.getPhone()).set(User::getTip, user.getTip());
                userMapper.update(null, wrapper);
            }else{
                //System.out.println("0000000022");
                return Result.error("-3","id,用户名已存在");
            }

        }else{
            if(res1 == null){
                // System.out.println("0000001111");
                userMapper.insert(user);
            }else{
                // System.out.println("0000001122");
                return Result.error("-3","用户名已存在");
            }

        }
        return Result.success();

    }

    // 更新用户信息
    @PutMapping
    public Result<?> update(@RequestBody User user){
        userMapper.updateById(user);
        return Result.success();
    }

    // 删除用户信息
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Integer id){
        userMapper.deleteById(id);
        return Result.success();
    }

    // 按id or username or name查询用户信息
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            try {
                Integer Id = Integer.valueOf(search);
                wrapper.eq(User::getId, Id);
            } catch (Exception e) {
                wrapper.like(User::getUsername,search).or().like(User::getName,search);
            }
        }

        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }
}
