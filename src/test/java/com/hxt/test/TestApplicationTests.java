package com.hxt.test;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hxt.test.entity.User;
import com.hxt.test.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.*;


@SpringBootTest
class TestApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
//        LinkedHashMap<String, String> map = new LinkedHashMap<>();
//
//        map.put("1", "2");
//        map.put("2", "2");
//        List<User> list = userService.getUser(map);
//        System.out.println(list);

        boolean b = userService.removeById(3);
        System.out.println(b);



    }
}
