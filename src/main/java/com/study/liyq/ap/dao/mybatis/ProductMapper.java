package com.study.liyq.ap.dao.mybatis;

import com.study.liyq.ap.model.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ProductMapper {

    Product selectProductById(Integer id);

    List<Product> selectAll();

    void insert(Product product);

    Integer update(Product product);

    Integer delete(Integer id);
}