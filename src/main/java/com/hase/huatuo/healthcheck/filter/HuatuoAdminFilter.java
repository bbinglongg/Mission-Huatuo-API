package com.hase.huatuo.healthcheck.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
public class HuatuoAdminFilter implements Filter {

    private final String TOKEN = "admin-token";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            String token = req.getHeader("X-Token");
            String requestURI = req.getRequestURI();
            if (requestURI.contains("/user/login") || "OPTIONS".equals(req.getMethod())
                   || (!"".equals(token) && TOKEN.equals(token))) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                log.error("Invalid token");
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setStatus(401);
//                response.sendRedirect("/huatuo-admin/api/user/logout");
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
