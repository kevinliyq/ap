package com.study.liyq.ap.dao;

import com.study.liyq.ap.model.Product;
import com.study.liyq.ap.model.ProductCreateRequest;
import com.study.liyq.ap.model.ProductUpdateRequest;

public interface IProductDao {

    Product getProduct(int productId);

    Product createProduct(ProductCreateRequest request);

    Product updateProduct(ProductUpdateRequest request);

    boolean deleteProduct(int productId);
}
