package com.gpt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import com.gpt.bean.User;
import com.gpt.mapper.UserMapper;
import com.gpt.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cuitong
 * @code description TODO
 * @code date 2023/10/16 17:05
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){
        System.out.println("userMapper---"+userMapper.getClass());
        User user = userMapper.selectById("1");
        System.out.println("user---"+user);
    }

    @Resource
    private UserService userService;
    @Test
    public void testUserService(){
        List<User> users = userService.list();
        //iter
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Resource
    private RedisTemplate redisTemplate;
    @Test
    public void testSendCode(){
        System.out.println(userService.sendCode("ct").getData());
    }

    @Test
    public void testGetCode(){
        String book = (String) redisTemplate.opsForValue().get("code");
        log.info("验证码为：{}",book);
    }




}
