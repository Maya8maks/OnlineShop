package com.borshcheva.shop.web.servlets;


import com.borshcheva.shop.entity.Product;
import com.borshcheva.shop.service.ProductService;
import com.borshcheva.shop.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class EditRequestsServlet extends HttpServlet {
    private final ProductService productService;

    public EditRequestsServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productService.getProduct(id);

        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("product", product);
        String page = pageGenerator.getPage("update-product.html", parameters);
        response.getWriter().write(page);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int id = Integer.parseInt(request.getParameter("id"));
        productService.update(id, name, price);

        response.sendRedirect("/products");

    }
}
