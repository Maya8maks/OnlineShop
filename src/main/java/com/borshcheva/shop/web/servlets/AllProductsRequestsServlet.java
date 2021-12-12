package com.borshcheva.shop.web.servlets;

import com.borshcheva.shop.entity.Product;
import com.borshcheva.shop.service.ProductService;
import com.borshcheva.shop.service.UserAuthService;
import com.borshcheva.shop.web.util.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class AllProductsRequestsServlet extends HttpServlet {
    private final ProductService productService;
    private final UserAuthService userAuthService;

    public AllProductsRequestsServlet(ProductService productService, UserAuthService userAuthService) {
        this.productService = productService;
        this.userAuthService = userAuthService;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        if (userAuthService.isAuth(request)) {
            List<Product> products = this.productService.getAllProducts();

            PageGenerator pageGenerator = PageGenerator.instance();
            HashMap<String, Object> parameters = new HashMap<>();

                parameters.put("products", products);

            String page = pageGenerator.getPage("product-list.html", parameters);
            response.getWriter().write(page);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

        } else {
            response.sendRedirect("/login");
        }
    }

}
