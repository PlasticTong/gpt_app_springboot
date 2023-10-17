package com.gpt.controller;

import com.baomidou.mybatisplus.core.assist.ISqlRunner;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gpt.bean.User;
import com.gpt.mapper.UserMapper;
import com.gpt.service.UserService;
import com.gpt.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author cuitong
 * @code description TODO
 * @code date 2023/10/17 15:13
 */
@RestController
@Slf4j
public class UserController {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        log.info("user={}", user);
        userService.save(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result Login(@RequestBody HashMap<String, Object> map) {
        log.info("map={}", map);
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        String letters = (String) map.get("letters");
        User userInfo = userService.getByUsername(username);
//        验证码
        String code = (String) redisTemplate.opsForValue().get(username);
        assert code != null;
        if (code.equals(letters)) {
            if (userInfo == null) {
                return Result.error("1001", "登录失败，用户不存在！");
            }
            String pass = String.valueOf(userInfo.getPassword());
            if (pass.equals(password)) {
                return Result.success("登录成功！");
            } else {
                return Result.error("1001", "登录失败，密码错误！");
            }

        } else {
            return Result.error("1001", "验证码错误！");
        }
    }


}
