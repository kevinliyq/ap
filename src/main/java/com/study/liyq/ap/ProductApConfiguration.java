package com.study.liyq.ap;

import com.study.liyq.ap.cache.RedisConfig;
import com.study.liyq.ap.dao.IProductDao;
import com.study.liyq.ap.dao.MemoryCacheProductDao;
import com.study.liyq.ap.dao.mybatis.DbProductDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RedisConfig.class)
public class ProductApConfiguration {

    @Bean
    public IProductDao getProductDao(){
        //return new MemoryCacheProductDao();
        return new DbProductDao();

    }
}
