package com.yaoer.seller.service;

import com.yaoer.seller.dataProject.OrderDetail;
import com.yaoer.seller.dataProject.ProductInfo;
import com.yaoer.seller.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lenovo on 2018/2/22.
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    List<ProductInfo>findByStatusUp();

    Page findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDto>cartDtoList);
    //减库存
    void decreaseStock(List<CartDto> cartDtoList);
}
