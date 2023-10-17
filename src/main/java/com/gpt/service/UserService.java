package com.gpt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gpt.bean.User;
import com.gpt.util.Result;
import org.springframework.stereotype.Service;

/**
 * @author cuitong
 * @code description TODO
 * @code date 2023/10/17 14:56
 */

public interface UserService extends IService<User> {

    Result sendCode(String username);

    User getByUsername(String username);
}
