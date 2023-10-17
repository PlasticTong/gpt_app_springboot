package com.gpt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpt.bean.User;
import com.gpt.mapper.UserMapper;
import com.gpt.service.UserService;
import com.gpt.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author cuitong
 * @code description TODO
 * @code date 2023/10/17 15:05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result sendCode(String username) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(username, stringBuilder, 20, TimeUnit.SECONDS);
        return Result.success(stringBuilder);
    }

    @Override
    public User getByUsername(String username){
        QueryWrapper<User> queryWrapper = Wrappers.query();
        if(StringUtils.hasText(username)){
            queryWrapper.eq("username", username);
        }
        return userMapper.selectOne(queryWrapper);
    }

}
