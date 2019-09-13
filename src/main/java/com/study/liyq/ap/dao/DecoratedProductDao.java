package com.study.liyq.ap.dao;

import com.study.liyq.ap.model.Product;
import com.study.liyq.ap.model.ProductCreateRequest;
import com.study.liyq.ap.model.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DecoratedProductDao implements IProductDao{

    @Autowired
    private RedisTemplate<String,Product> redisTemplate;

    @Value("${ap.product.expiration.seconds}")
    private int expirationInSeconds;

    private IProductDao productDao;

    public DecoratedProductDao(IProductDao productDao)
    {
        this.productDao = productDao;
    }

    @Override
    public Product getProduct(int productId) {
        String key = getKey(productId);

        if (redisTemplate.hasKey(key))
        {
            return redisTemplate.opsForValue().get(key);
        }

        Product product = productDao.getProduct(productId);

        saveCache(key, product, expirationInSeconds);

        return product;
    }

    private void saveCache(String key, Product product, int expirationInSeconds) {
        if (!Objects.isNull(product)) {
            redisTemplate.opsForValue().set(key, product, expirationInSeconds, TimeUnit.SECONDS);
        }
    }

    private String getKey(int productId)
    {
        return "product" + ":" + productId;
    }

    @Override
    public Product createProduct(ProductCreateRequest request) {
        Product product = productDao.createProduct(request);

        saveCache(getKey(product.getProductId()), product, expirationInSeconds);

        return product;
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request) {

        Product product = productDao.updateProduct(request);

        saveCache(getKey(product.getProductId()), product, expirationInSeconds);

        return product;
    }

    @Override
    public boolean deleteProduct(int productId) {
        boolean result = productDao.deleteProduct(productId);

        redisTemplate.delete(getKey(productId));

        return result;
    }
}
