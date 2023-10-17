package com.gpt.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cuitong
 * @code description TODO
 * @code date 2023/10/17 17:23
 */
@RestController
@RequestMapping("/redisTest")
public class RedisTestController {
    @Resource
    private RedisTemplate redisTemplate;

    //编写第一个测试方法
    //演示设置数据和获取数据
    @GetMapping("/t1")
    public String t1() {

        //设置值到redis
        redisTemplate.opsForValue().set("book", "天龙八部~");
        //从redis获取值
        String book = (String) redisTemplate.opsForValue().get("book");
        return book;
    }

    //编写方法,演示如何操作list,hash,set,zset
    @GetMapping("/t2")
    public String t2() {
        //list-存
        redisTemplate.opsForList().leftPush("books", "笑傲江湖");
        redisTemplate.opsForList().leftPush("books", "hello,java");

        //list-取出
        List books = redisTemplate.opsForList().range("books", 0, -1);
        StringBuilder booksList = new StringBuilder();
        assert books != null;
        for (Object book : books) {
            System.out.println("book-->" + book.toString());
            booksList.append(book.toString()).append(" ");
        }
        //hash
        //redisTemplate.opsForHash()
        //set
        //redisTemplate.opsForSet()
        //zset
        //redisTemplate.opsForZSet()
        return booksList.toString();
    }
}
