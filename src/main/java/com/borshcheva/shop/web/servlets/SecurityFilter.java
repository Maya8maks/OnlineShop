package com.borshcheva.shop.web.servlets;


import com.borshcheva.shop.service.UserAuthService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SecurityFilter implements Filter {

    private final List<String> userTokens;
    private final UserAuthService userAuthService;
    private final List<String> allowedPaths = List.of("/login", "/registration");

    public SecurityFilter(List<String> userTokens, UserAuthService userAuthService) {
        this.userTokens = userTokens;
        this.userAuthService = userAuthService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestUri = httpServletRequest.getRequestURI();

        for (String allowedPath : allowedPaths) {
            if (requestUri.startsWith(allowedPath) ) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
            else {
                if (userAuthService.isAuth(httpServletRequest.getCookies(), userTokens)) {
                    filterChain.doFilter(servletRequest, servletResponse);
                } else {
                    httpServletResponse.sendRedirect("/login");
                }
            }
        }

    }

    @Override
    public void destroy() {

    }
}
