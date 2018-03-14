package com.yaoer.seller.service;

import com.yaoer.seller.dto.OrderDto;

/**
 * Created by lenovo on 2018/2/25.
 */
public interface BuyerService {

    OrderDto findOrderOne(String openid,String orderId);

    OrderDto cancel(String openid,String orderId);
}
