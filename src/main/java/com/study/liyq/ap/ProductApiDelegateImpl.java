package com.study.liyq.ap;

import com.google.common.collect.Maps;
import com.study.liyq.ap.api.ProductApiDelegate;
import com.study.liyq.ap.model.Product;
import com.study.liyq.ap.model.ProductRequest;
import com.study.liyq.ap.model.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductApiDelegateImpl implements ProductApiDelegate {
    Logger logger = LoggerFactory.getLogger(ProductApiDelegateImpl.class);

    private Map<String, Product> products = Maps.newHashMap();



    @Override
    public ResponseEntity<ProductResponse> getProduct(Integer productId) {
        logger.info("getProduct, Input parameter:{}", productId);

        Objects.requireNonNull(productId);

        Product product = products.get(String.valueOf(productId));

        ProductResponse response = new ProductResponse()
                .success(product != null)
                .data(product)
                ;
        logger.info("getProduct, output parameter:{}", product);


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest) {
        logger.info("createProduct, Input parameter:{}", productRequest);

        Objects.requireNonNull(productRequest);

        Product product = Optional.of(productRequest).map(request -> new Product()
                .productId(request.getProductId())
                .productName(request.getProductName())
                .partner(request.getPartner())
                .price(request.getPrice())
                .sellable(request.isSellable())
                .cancellationPolicies(request.getCancellationPolicies())
                )
                .get();

        products.putIfAbsent(String.valueOf(productRequest.getProductId()),product);

        ProductResponse response = new ProductResponse()
                .success(true)
                .data(product)
                ;

        logger.info("createProduct, output parameter:{}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
