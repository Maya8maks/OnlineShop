package com.borshcheva.shop.service;

import com.borshcheva.shop.dao.UserDao;
import com.borshcheva.shop.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


public class UserAuthService {
    private UserDao userDao;

    public UserAuthService(UserDao userDao) {
        this.userDao = userDao;
    }


   public User findUser(String email, String password) {
       return userDao.findUser(email, password);
    }


    public boolean isAuth(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    User user = userDao.findUserByToken(cookie.getValue());
                    if (user != null)  {
                        return true;

                    }
                }
            }
        }
        return false;
    }
    public User findUserByToken(String token) {
        return userDao.findUserByToken(token);
    }


    public void updateUserToken(int id, String userToken) {
        userDao.updateUserToken(id, userToken);
    }

    public String getUserToken() {
        return UUID.randomUUID().toString();
    }
}
