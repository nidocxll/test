package com.hxt.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hxt.test.entity.User;
import com.hxt.test.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/get")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/user")
    public List<User> importCourse(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        map.put("1", "2");
        map.put("2", "2");
        List<User> list = userService.getUser(map);

        redisTemplate.opsForValue().set("hot",String.valueOf(list));
        System.out.println(redisTemplate.opsForValue().get("hot"));
        //213689947

        //test
        //jdkalkkfdjlafjdalk
        return  list;
    }

}
