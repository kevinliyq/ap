package com.study.liyq.ap.interceptor;

import com.study.liyq.ap.counter.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Would be implement to compute counter
 */
@Component
public class CounterInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(CounterInterceptor.class);

    @Autowired
    private CounterService counterService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle is called requestUri: {}", request.getRequestURI());

        counterService.incrAndGetTotalCounter(1);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle is called requestUri: {}", request.getRequestURI());
        counterService.incrAndGetSuccessCounter(1);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        logger.info("afterCompletion is called, totalCounter={}, successCounter={}, failCounter={}", counterService.getTotalCounter(), counterService.getSuccessCounter(), counterService.getFailCounter());
    }
}
