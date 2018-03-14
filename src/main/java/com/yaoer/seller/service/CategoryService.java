package com.yaoer.seller.service;

import com.yaoer.seller.dataProject.ProductCategory;

import java.util.List;

/**
 * Created by lenovo on 2018/2/22.
 */
public interface CategoryService {

    ProductCategory findOne();

    List<ProductCategory> findCategoryByTypeIn(List<Integer>categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory>findAll();


}
