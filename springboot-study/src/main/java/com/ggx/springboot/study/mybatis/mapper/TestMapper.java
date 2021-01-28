package com.ggx.springboot.study.mybatis.mapper;

import com.ggx.springboot.study.mybatis.bean.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TestMapper {

    @Select("select * from test where id = #{id}")
    Test getById(@Param(value = "id")int id);

    void updateTest(@Param(value = "test")Test test);
}
