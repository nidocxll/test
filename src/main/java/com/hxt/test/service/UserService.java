package com.hxt.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxt.test.entity.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【student】的数据库操作Service
* @createDate 2022-07-08 13:30:24
*/
public interface UserService extends IService<User> {

    List<User> getUser( LinkedHashMap<String,String> map);
}
