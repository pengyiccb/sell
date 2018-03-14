package com.yaoer.seller.service;

import com.yaoer.seller.dataProject.OrderMaster;
import com.yaoer.seller.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by lenovo on 2018/2/23.
 */
public interface OrderService {
    //创建订单
    OrderDto create(OrderDto orderDto);
    //查询单个订单

    OrderDto findByOrderId(String orderId);
    //查询订单列表
    Page findOrderList(String buyerOpenid, Pageable pageable);
    //取消订单
    OrderDto cancel(OrderDto orderDto);
    //完结订单

    OrderDto finish(OrderDto orderDto);
    //支付订单
    OrderDto paid(OrderDto orderDto);
}
