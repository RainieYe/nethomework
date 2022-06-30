package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Example;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ExampleMapper extends BaseMapper<Example> {
    @Select("${mysql}")
    List<Example> dynamicSql(@Param("mysql")String sql);

}