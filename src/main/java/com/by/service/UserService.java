package com.by.service;

import com.by.model.User;

/**
 * Created by ${zw} on 2019/7/4.
 */
public interface UserService {

    User findUserByUserName(String username);
}
