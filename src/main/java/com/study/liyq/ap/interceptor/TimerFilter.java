package com.study.liyq.ap.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * There are two ways to register filter:
 * 1. annotated with @Component
 * 2. use FilterRegistrationBean to control what uri is filter
 */

@Component
public class TimerFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(TimerFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();

        chain.doFilter(request,response);

        long endTime = System.currentTimeMillis();

        logger.info(getUrl(request) + " elapsed time:" + (endTime - startTime) + " ms");

    }

    @Override
    public void destroy() {

    }

    private String getUrl(ServletRequest servletRequest){
        if (servletRequest instanceof HttpServletRequest){
            return ((HttpServletRequest) servletRequest).getRequestURI();
        }

        return "";
    }
}
