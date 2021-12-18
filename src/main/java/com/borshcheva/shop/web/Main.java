package com.borshcheva.shop.web;

import com.borshcheva.shop.dao.JdbcProductDao;
import com.borshcheva.shop.dao.JdbcUserDao;
import com.borshcheva.shop.service.ProductService;
import com.borshcheva.shop.service.UserAuthService;
import com.borshcheva.shop.service.UserService;
import com.borshcheva.shop.web.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        JdbcUserDao jdbcUserDao = new JdbcUserDao();

        ProductService productService = new ProductService(jdbcProductDao);
        UserService userService = new UserService(jdbcUserDao);
        UserAuthService userAuthService = new UserAuthService(jdbcUserDao);
        List<String> userTokens = new ArrayList<>();

        AllProductsRequestsServlet allProductsRequestsServlet = new AllProductsRequestsServlet(productService);
        EditRequestsServlet editRequestsServlet = new EditRequestsServlet(productService);
        DeleteRequestsServlet deleteRequestsServlet = new DeleteRequestsServlet(productService);
        CreateRequestsServlet createRequestsServlet = new CreateRequestsServlet(productService);
        RegistrationRequestsServlet registrationRequestsServlet = new RegistrationRequestsServlet(userService, userAuthService);
        LoginRequestsServlet loginRequestsServlet = new LoginRequestsServlet(userAuthService, userTokens);
        LogoutRequestsServlet logoutRequestsServlet = new LogoutRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(editRequestsServlet), "/products/edit");
        context.addServlet(new ServletHolder(deleteRequestsServlet), "/products/delete");
        context.addServlet(new ServletHolder(createRequestsServlet), "/products/create");
        context.addServlet(new ServletHolder(allProductsRequestsServlet), "/products");
        context.addServlet(new ServletHolder(registrationRequestsServlet), "/registration");
        context.addServlet(new ServletHolder(loginRequestsServlet), "/login");
        context.addServlet(new ServletHolder(logoutRequestsServlet), "/logout");
        context.addFilter(new FilterHolder(new SecurityFilter(userTokens, userAuthService)), "/*", (EnumSet.of(DispatcherType.REQUEST)));

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
