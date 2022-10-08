package com.hxt.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxt.test.entity.User;
import com.hxt.test.service.UserService;
import com.hxt.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【student】的数据库操作Service实现
 * @createDate 2022-07-08 13:30:24
 */
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Cacheable
    public List<User> getUser(LinkedHashMap<String, String> map) {

        return userMapper.getUser(map);
    }
}
