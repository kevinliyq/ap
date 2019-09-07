package com.study.liyq.ap.service;

import com.study.liyq.ap.dao.IProductDao;
import com.study.liyq.ap.model.Product;
import com.study.liyq.ap.model.ProductCreateRequest;
import com.study.liyq.ap.model.ProductUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IProductDao productDao;

    @Override
    public Product createProduct(ProductCreateRequest productRequest) {
        logger.info("createProduct input:{}", productRequest);

        return productDao.createProduct(productRequest);
    }

    @Override
    public Product updateProduct(ProductUpdateRequest productRequest) {
        logger.info("updateProduct input:{}", productRequest);

        return productDao.updateProduct(productRequest);
    }

    @Override
    public Product getProduct(Integer productId) {
        logger.info("getProduct input:{}", productId);

        Product product = productDao.getProduct(productId);

        return product;
    }

    @Override
    public boolean deleteProduct(Integer productId) {
        logger.info("deleteProduct input:{}", productId);

        return productDao.deleteProduct(productId);
    }
}
