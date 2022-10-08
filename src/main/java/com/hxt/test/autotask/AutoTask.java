package com.hxt.test.autotask;

import com.hxt.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@ComponentScan
public class AutoTask {

    @Autowired
    private UserService userService;

    @Scheduled(cron = "")

    private void unpack(){
        
    }
}
