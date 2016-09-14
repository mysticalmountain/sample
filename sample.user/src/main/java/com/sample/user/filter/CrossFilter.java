//package com.sample.user.filter;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Created by andongxu on 16-6-8.
// */
//@Component
//public class CrossFilter extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
//            HttpServletResponse rps = (HttpServletResponse)response;
//            rps.addHeader("Access-Control-Allow-Origin", "*");
//            rps.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//            rps.addHeader("Access-Control-Allow-Headers", "Content-Type");
//            rps.addHeader("Access-Control-Max-Age", "1800");//30 min
////        }
//        filterChain.doFilter(request, response);
//    }
//}
