package com.study.liyq.ap.dao.mybatis;

import com.study.liyq.ap.dao.IProductDao;
import com.study.liyq.ap.model.Product;
import com.study.liyq.ap.model.ProductCreateRequest;
import com.study.liyq.ap.model.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DbProductDao implements IProductDao {

    @Autowired
    private ProductMapper mapper;

    @Override
    public Product getProduct(int productId) {
        return mapper.selectProductById(productId);
    }

    @Override
    public Product createProduct(ProductCreateRequest createRequest) {
        Product product = Optional.of(createRequest)
                .map(request -> new Product()
                        .productName(request.getProductName())
                        .partner(request.getPartner())
                        .price(request.getPrice())
                        .sellable(request.isSellable())
                        .cancellationPolicy(request.getCancellationPolicy()))
                .get();

        mapper.insert(product);
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
                        .cancellationPolicy(request.getCancellationPolicy()))
                .get();

        mapper.update(product);
        return product;
    }

    @Override
    public boolean deleteProduct(int productId) {

        mapper.delete(productId);
        return true;
    }
}
