package com.borshcheva.shop.web.servlets;


import com.borshcheva.shop.service.ProductService;
import com.borshcheva.shop.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


public class CreateRequestsServlet extends HttpServlet {
    private final ProductService productService;

    public CreateRequestsServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("create-product.html", Collections.emptyMap());
        response.getWriter().write(page);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        productService.create(name, price);

        response.sendRedirect("/products");


    }

}
