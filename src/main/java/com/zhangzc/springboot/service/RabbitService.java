package com.zhangzc.springboot.service;

import com.zhangzc.springboot.entities.Department;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {
    @RabbitListener(queues = "myqueue.news")
    public void recieve(Department dept) {
        System.out.println(dept);
    }
}
