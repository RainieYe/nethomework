package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        if(res!=null){
            return Result.error("-3","用户名已存在");
        }
        userMapper.insert(user);
        return Result.success();
    }

    // 增加用户
    @PostMapping
    public Result<?> save(@RequestBody User user){
        if(user.getPassword()==null){
            user.setPassword("123456");
        }
        userMapper.insert(user);
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
    public Result<?> delete(@PathVariable Long id){
        userMapper.deleteById(id);
        return Result.success();
    }

    // 按id or username or name查询用户信息
    @GetMapping
    public Result<?> findByUserId(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            try {
                Integer Id = Integer.valueOf(search);
                wrapper.eq(User::getId, Id);
            } catch (StringIndexOutOfBoundsException e) {
                LambdaQueryWrapper<User> wrapperUser = Wrappers.lambdaQuery();
                if (wrapperUser.eq(User::getUsername, search) != null) {
                    Integer userid = wrapperUser.eq(User::getUsername, search).getEntity().getId();
                    wrapper.eq(User::getId, userid);
                }
                if (wrapperUser.eq(User::getName, search) != null) {
                    Integer userid = wrapperUser.eq(User::getName, search).getEntity().getId();
                    wrapper.eq(User::getId, userid);
                }
            }
        }

        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }
}
