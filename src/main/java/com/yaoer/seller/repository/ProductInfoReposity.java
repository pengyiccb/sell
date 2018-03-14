package com.yaoer.seller.repository;

import com.yaoer.seller.dataProject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lenovo on 2018/2/22.
 */
public interface ProductInfoReposity extends JpaRepository<ProductInfo,String> {

    List<ProductInfo>findByProductStatus(Integer productStates);
}
