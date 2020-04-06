package com.zhangzc.springboot;

import com.zhangzc.springboot.dao.DepartmentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class StudySpringbootApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    DepartmentMapper departmentMapper;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
    @Test
    void testRedis01(){
        String foo = stringRedisTemplate.opsForValue().get("dept");
        System.out.println(foo);
    }
    @Test
    void testRedis02(){
        redisTemplate.opsForValue().set("depts",departmentMapper.selectAll());
    }

}
