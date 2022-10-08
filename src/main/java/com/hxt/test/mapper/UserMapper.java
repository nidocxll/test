package com.hxt.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxt.test.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【student】的数据库操作Mapper
 * @createDate 2022-07-08 13:30:24
 * @Entity com.hxt.test.entity.Student
 */

public interface UserMapper extends BaseMapper<User> {

    List<User> getUser(@Param("map") LinkedHashMap<String, String> map);

}
