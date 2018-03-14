package com.yaoer.seller.dto;

import lombok.Data;

/**
 * Created by lenovo on 2018/2/23.
 */
@Data
public class CartDto {

    private String productId;

    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
