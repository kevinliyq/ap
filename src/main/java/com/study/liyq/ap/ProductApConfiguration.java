package com.study.liyq.ap;

import com.study.liyq.ap.cache.RedisConfig;
import com.study.liyq.ap.dao.DecoratedProductDao;
import com.study.liyq.ap.dao.IProductDao;
import com.study.liyq.ap.dao.MemoryCacheProductDao;
import com.study.liyq.ap.dao.mybatis.DbProductDao;
import com.study.liyq.ap.interceptor.CounterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RedisConfig.class)
public class ProductApConfiguration {

    @Autowired
    private CounterInterceptor counterInterceptor;

    @Bean
    @Qualifier("productDBDao")
    public IProductDao productDBDao(){
        return new DbProductDao();
    }

    @Bean
    @Qualifier("productDao")
    public IProductDao getProductDao(){
        //return new MemoryCacheProductDao();
        return new DecoratedProductDao(productDBDao());

    }


}
