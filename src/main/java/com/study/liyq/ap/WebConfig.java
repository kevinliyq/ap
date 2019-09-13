package com.study.liyq.ap;

import com.study.liyq.ap.interceptor.CounterInterceptor;
import com.study.liyq.ap.interceptor.TimerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Needs to implement addInterceptors method to define the order of interceptor
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private CounterInterceptor counterInterceptor;

    @Autowired
    private TimerFilter timeFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(counterInterceptor);
    }

    @Bean
    public FilterRegistrationBean customizeFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean() ;

        filterRegistrationBean.setFilter(timeFilter);
        List<String > urls = new ArrayList<>();

        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);

        return filterRegistrationBean;
    }
}
