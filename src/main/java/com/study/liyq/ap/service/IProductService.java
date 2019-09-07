package com.study.liyq.ap.service;

import com.study.liyq.ap.model.Product;
import com.study.liyq.ap.model.ProductCreateRequest;
import com.study.liyq.ap.model.ProductUpdateRequest;

public interface IProductService {

    Product createProduct(ProductCreateRequest productRequest);

    Product updateProduct(ProductUpdateRequest productRequest);

    Product getProduct(Integer productId);

    boolean deleteProduct(Integer productId);
}
