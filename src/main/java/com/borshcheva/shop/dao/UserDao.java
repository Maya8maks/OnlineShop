package com.borshcheva.shop.dao;

import com.borshcheva.shop.entity.User;

public interface UserDao {
    User findUser(String email, String password);

    void create(User user);

    User findUserByToken(String value);

    void updateUserToken(int id, String userToken);
}
