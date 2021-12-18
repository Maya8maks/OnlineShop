package com.borshcheva.shop.service;

import com.borshcheva.shop.dao.UserDao;
import com.borshcheva.shop.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;


public class UserAuthService {
    private UserDao userDao;

    public UserAuthService(UserDao userDao) {
        this.userDao = userDao;
    }


   public User findUser(String email, String password) {
       return userDao.findUser(email, password);
    }


    public boolean isAuth(Cookie[] cookies, List<String> userTokens) {

        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("user-token")){
                    if(userTokens.contains(cookie.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public String getUserToken() {
        return UUID.randomUUID().toString();
    }

}
