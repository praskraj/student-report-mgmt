//package com.api.annualreportmgmt.common;
//
//import org.springframework.stereotype.Component;
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class CorsFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) res;
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        chain.doFilter(req, res);
//    }
//}
