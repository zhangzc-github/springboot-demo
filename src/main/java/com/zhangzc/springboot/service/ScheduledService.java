package com.zhangzc.springboot.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScheduledService {
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduledSout() {
        System.out.println("time:"+new Date());
    }
}
