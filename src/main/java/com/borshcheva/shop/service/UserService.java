package com.borshcheva.shop.service;


import com.borshcheva.shop.dao.UserDao;
import com.borshcheva.shop.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(String email, String password) {
        String sole = UUID.randomUUID().toString();
        String passwordHash = (DigestUtils.md5Hex(sole + password));
        User user = User.builder().
                email(email).
                password(passwordHash).
                sole(sole).
                build();
        userDao.create(user);
    }
}
