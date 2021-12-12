package com.borshcheva.shop.web.servlets;

import com.borshcheva.shop.service.ProductService;
import com.borshcheva.shop.service.UserAuthService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRequestsServlet extends HttpServlet {
    private final ProductService productService;
    private final UserAuthService userAuthService;

    public DeleteRequestsServlet(ProductService productService, UserAuthService userAuthService) {
        this.productService = productService;
        this.userAuthService = userAuthService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (userAuthService.isAuth(request)) {
            int id = Integer.parseInt(request.getParameter("id"));
            this.productService.deleteProduct(id);
            response.sendRedirect("/products");
        } else {
            response.sendRedirect("/login");
        }
    }

}
