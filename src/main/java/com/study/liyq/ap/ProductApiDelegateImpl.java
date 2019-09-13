package com.study.liyq.ap;

import com.google.common.collect.Maps;
import com.study.liyq.ap.api.ProductApiDelegate;
import com.study.liyq.ap.model.*;
import com.study.liyq.ap.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class ProductApiDelegateImpl implements ProductApiDelegate {
    Logger logger = LoggerFactory.getLogger(ProductApiDelegateImpl.class);

    private Map<String, Product> products = Maps.newHashMap();

    @Autowired
    private IProductService productService;

    @Override
    public ResponseEntity<ProductResponse> getProduct(Integer productId) {
        logger.info("getProduct, Input parameter:{}", productId);

        Objects.requireNonNull(productId);

        Product product = productService.getProduct(productId);

        ProductResponse response = new ProductResponse()
                .success(product != null)
                .data(product);
        logger.info("getProduct, output parameter:{}", product);


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductCreateRequest productRequest) {
        logger.info("createProduct, Input parameter:{}", productRequest);

        Objects.requireNonNull(productRequest);

        Product product = productService.createProduct(productRequest);

        ProductResponse response = new ProductResponse()
                .success(true)
                .data(product);

        logger.info("createProduct, output response:{}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(ProductUpdateRequest updateProductRequest) {
        logger.info("createProduct, Input parameter:{}", updateProductRequest);
        Objects.requireNonNull(updateProductRequest);

        Product product = productService.updateProduct(updateProductRequest);

        ProductResponse response = new ProductResponse()
                .success(true)
                .data(product);

        logger.info("updateProduct, output response:{}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDeleteResponse> deleteProduct(Integer productId) {
        logger.info("deleteProduct, Input parameter:{}", productId);
        Objects.requireNonNull(productId);

        //Product product = products.remove(String.valueOf(productId));
        boolean isDeleted = productService.deleteProduct(productId);


        ProductDeleteResponse response = null;
        if (isDeleted) {
            response = new ProductDeleteResponse()
                    .success(true)
                    .errorCode("")
                    .errorMessage("");
        } else {
            response = new ProductDeleteResponse()
                    .success(false)
                    .errorCode("1")
                    .errorMessage("product is not found");
        }

        logger.info("deleteProduct, output response:{}", response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
