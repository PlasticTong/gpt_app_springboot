package com.gpt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gpt.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
