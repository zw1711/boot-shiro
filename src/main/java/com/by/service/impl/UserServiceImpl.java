package com.by.service.impl;

import com.by.mapper.UserMapper;
import com.by.model.User;
import com.by.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ${zw} on 2019/7/4.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserMapper userMapper;

    @Override
    public User findUserByUserName(String username) {

        return userMapper.findUserByUserName(username);
    }
}
