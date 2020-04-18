package com.zhangzc.springboot;

import com.zhangzc.springboot.dao.DepartmentMapper;
import com.zhangzc.springboot.entities.Department;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
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
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RestClient restClient;
    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    void testRedis01() {
        String foo = stringRedisTemplate.opsForValue().get("dept");
        System.out.println(foo);
    }

    @Test
    void testRedis02() {
        redisTemplate.opsForValue().set("depts", departmentMapper.selectAll());
    }

    @Test
    void testRabbitMq() {
        rabbitTemplate.convertAndSend("exchange.topic", "hello.news", new Department(3, "1部门"));
    }

    @Test
    void testElasticsearch() throws IOException {
        Response response = restClient.performRequest(new Request("get","/mytest/employee/1"));
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test
    void testMail01() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Spring Boot Test Mail");
        message.setText("Hello World");
        message.setTo("zhangzcwy@163.com");
        message.setFrom("1843868984@qq.com");
        javaMailSender.send(message);
    }
    @Test
    void testMail02() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("Spring Boot Test Mail");
        helper.setText("<h1 style='color:red'>Hello World</h1>",true);
        helper.setTo("zhangzcwy@163.com");
        helper.setFrom("1843868984@qq.com");
        helper.addAttachment("1.jpg",new File("D:\\文档\\yande.re 325530 sample kadowaki_miku kanbara_akihito kuriyama_mirai kyoukai_no_kanata megane nase_hiroomi nase_mitsuki pantyhose seifuku.jpg"));
        javaMailSender.send(mimeMessage);
    }
}
