package com.borshcheva.shop.web.servlets;

import com.borshcheva.shop.entity.User;
import com.borshcheva.shop.service.UserAuthService;
import com.borshcheva.shop.web.util.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LoginRequestsServlet extends HttpServlet {

    private final UserAuthService userAuthService;
    private final List<String> userTokens;

    public LoginRequestsServlet(UserAuthService userAuthService, List<String> userTokens) {
        this.userAuthService = userAuthService;
        this.userTokens = userTokens;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("login.html", Collections.emptyMap());
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
        if (user != null && user.getId() != null) {
            String userToken = userAuthService.getUserToken();
            userTokens.add(userToken);
            Cookie cookie = new Cookie("user-token", userToken);
            response.addCookie(cookie);
            response.sendRedirect("/products");

        } else {
            PageGenerator pageGenerator = PageGenerator.instance();

            String message = "Email or password are incorrect";
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("message", message);
            String page = pageGenerator.getPage("login.html", parameters);

            response.getWriter().write(page);

        }
    }
}
