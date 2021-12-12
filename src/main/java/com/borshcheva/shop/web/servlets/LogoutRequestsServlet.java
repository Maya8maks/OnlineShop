package com.borshcheva.shop.web.servlets;

import com.borshcheva.shop.entity.User;
import com.borshcheva.shop.service.UserAuthService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LogoutRequestsServlet extends HttpServlet {
    private final UserAuthService userAuthService;

    public LogoutRequestsServlet(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    User user = userAuthService.findUserByToken(cookie.getValue());
                    if (user.getId() != null) {
                        cookie.setValue("");
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        userAuthService.updateUserToken(user.getId(), cookie.getValue());
                    }
                }
            }
        }
        response.sendRedirect("/");
    }
}
