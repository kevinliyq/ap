package com.study.liyq.ap.service;

import com.google.common.base.Strings;
import com.study.liyq.ap.dao.IProductDao;
import com.study.liyq.ap.exception.ApException;
import com.study.liyq.ap.model.Product;
import com.study.liyq.ap.model.ProductCreateRequest;
import com.study.liyq.ap.model.ProductUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.study.liyq.ap.exception.ErrorEnum.INVALID_REQUEST;
import static com.study.liyq.ap.exception.ErrorEnum.NO_FOUND;

@Service
public class ProductService implements IProductService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("productDao")
    private IProductDao productDao;

    @Override
    public Product createProduct(ProductCreateRequest productRequest) {
        logger.info("createProduct input:{}", productRequest);

        if (Strings.isNullOrEmpty(productRequest.getProductName()))
        {
            throw new ApException("productName is empty", INVALID_REQUEST.getErrorCode(), INVALID_REQUEST.getErrorMsg());
        }

        return productDao.createProduct(productRequest);
    }

    @Override
    public Product updateProduct(ProductUpdateRequest productRequest) {
        logger.info("updateProduct input:{}", productRequest);

        if (Strings.isNullOrEmpty(productRequest.getProductName()))
        {
            throw new ApException("productName is empty", INVALID_REQUEST.getErrorCode(), INVALID_REQUEST.getErrorMsg());
        }

        return productDao.updateProduct(productRequest);
    }

    @Override
    public Product getProduct(Integer productId) {
        logger.info("getProduct input:{}", productId);

        Product product = productDao.getProduct(productId);

        if (Objects.isNull(product))
        {
            throw new ApException("Result is not found", NO_FOUND.getErrorCode(), NO_FOUND.getErrorMsg());
        }

        return product;
    }

    @Override
    public boolean deleteProduct(Integer productId) {
        logger.info("deleteProduct input:{}", productId);

        return productDao.deleteProduct(productId);
    }
}
