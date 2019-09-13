package com.study.liyq.ap.cache;

import com.study.liyq.ap.model.Partner;
import com.study.liyq.ap.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {
    private Logger logger = LoggerFactory.getLogger(RedisConfigTest.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String,Product> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Resource
    private RedisService redisService;

    @Test
    public void testRedisTemplate() throws Exception{
        Product product = new Product();
        product.setProductId(1);
        product.setPartner(Partner.CTRIP);
        product.setPrice(100.0);
        product.setProductName("Hello");
        product.setCancellationPolicy("cp1");
        product.setSellable(true);

        String key = product.getProductId() + "-" + product.getProductName();

        try {
            ValueOperations operations = redisTemplate.opsForValue();
            operations.set(key, product, 1, TimeUnit.MINUTES);

            Assert.assertNotNull(valueOperations.get(key));

            logger.info("{}", operations.get(key));
        }finally {
            redisTemplate.delete(key);
            redisService.existsKey(key);
        }

    }

    @Test
    public void testValueOperation() throws Exception{
        Product product = new Product();
        product.setProductId(2);
        product.setPartner(Partner.ELONG);
        product.setPrice(100.0);
        product.setProductName("Hello");
        product.setCancellationPolicy("cp2");
        product.setSellable(true);

        String key = product.getProductId() + "-" + product.getProductName();
        valueOperations.set(key, product, 1, TimeUnit.MINUTES);

        Assert.assertNotNull(valueOperations.get(key));
        Assert.assertEquals(product,valueOperations.get(key));

        logger.info("{}", valueOperations.get(key));

        redisTemplate.delete(key);
    }
}