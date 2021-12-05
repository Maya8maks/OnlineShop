package com.borshcheva.shop.web.servlets;

import com.borshcheva.shop.dao.JdbcProductDao;
import com.borshcheva.shop.service.ProductService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {

        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        ProductService productService = new ProductService(jdbcProductDao);

        AllRequestsServlet allRequestsServlet = new AllRequestsServlet(productService);
        EditRequestsServlet editRequestsServlet = new EditRequestsServlet(productService);
        DeleteRequestsServlet deleteRequestsServlet = new DeleteRequestsServlet(productService);
        CreateRequestsServlet createRequestsServlet = new CreateRequestsServlet(productService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(editRequestsServlet), "/products/edit");
        context.addServlet(new ServletHolder(deleteRequestsServlet), "/products/delete");
        context.addServlet(new ServletHolder(createRequestsServlet), "/products/create");
        context.addServlet(new ServletHolder(allRequestsServlet), "/products");

        context.addServlet(new ServletHolder(allRequestsServlet), "/");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
