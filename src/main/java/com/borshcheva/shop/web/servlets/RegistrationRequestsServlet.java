package com.borshcheva.shop.web.servlets;


import com.borshcheva.shop.entity.User;
import com.borshcheva.shop.service.UserAuthService;
import com.borshcheva.shop.service.UserService;
import com.borshcheva.shop.web.util.PageGenerator;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class RegistrationRequestsServlet extends HttpServlet {
    private final UserService userService;
    private final UserAuthService userAuthService;

    public RegistrationRequestsServlet(UserService userService, UserAuthService userAuthService) {
        this.userService = userService;
        this.userAuthService = userAuthService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("registration.html", Collections.emptyMap());
        response.getWriter().write(page);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = userAuthService.findUser(email, password);
        if (user != null) {
            PageGenerator pageGenerator = PageGenerator.instance();
            Map<String, Object> parametrs = new HashMap<>();
            String message = "User already exists";
            parametrs.put("message", message);
            String page = pageGenerator.getPage("registration.html", parametrs);

            response.getWriter().write(page);

        } else {
            String userToken = userAuthService.getUserToken();
            Cookie cookie = new Cookie("user-token", userToken);
            userService.createUser(email, password, userToken);
            response.addCookie(cookie);
            response.sendRedirect("/products");
        }


    }

}
