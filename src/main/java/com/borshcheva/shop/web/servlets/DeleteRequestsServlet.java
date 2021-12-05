package com.borshcheva.shop.web.servlets;

import com.borshcheva.shop.service.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRequestsServlet extends HttpServlet {
    private ProductService productService;

    public DeleteRequestsServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.productService.deleteProduct(id);
        response.sendRedirect("/products");
    }

}
