package com.study.liyq.ap.dao;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.study.liyq.ap.model.Product;
import com.study.liyq.ap.model.ProductCreateRequest;
import com.study.liyq.ap.model.ProductUpdateRequest;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryCacheProductDao implements IProductDao{

    private Cache<String,Product> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    private AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public Product getProduct(int productId) {
        return cache.getIfPresent(String.valueOf(productId));
    }

    @Override
    public Product createProduct(ProductCreateRequest createRequest) {
        Objects.requireNonNull(createRequest);

        Product product = Optional.of(createRequest)
                .map(request -> new Product()
                        .productId(idGenerator.incrementAndGet())
                        .productName(request.getProductName())
                        .partner(request.getPartner())
                        .price(request.getPrice())
                        .sellable(request.isSellable())
                        .cancellationPolicies(request.getCancellationPolicies()))
                .get();
        cache.put(String.valueOf(product.getProductId()),product);

        return product;
    }

    @Override
    public Product updateProduct(ProductUpdateRequest updateRequest) {
        Product product = Optional.of(updateRequest)
                .map(request -> new Product()
                        .productId(updateRequest.getProductId())
                        .productName(request.getProductName())
                        .partner(request.getPartner())
                        .price(request.getPrice())
                        .sellable(request.isSellable())
                        .cancellationPolicies(request.getCancellationPolicies()))
                .get();
        cache.put(String.valueOf(product.getProductId()),product);

        return product;
    }

    @Override
    public boolean deleteProduct(int productId) {
        Product product = cache.getIfPresent(String.valueOf(productId));
        cache.invalidate(String.valueOf(productId));

        return product != null;
    }
}
