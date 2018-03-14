package com.yaoer.seller.controller;

import com.yaoer.seller.dto.OrderDto;
import com.yaoer.seller.productStatusEnum.OrderStatusEnum;
import com.yaoer.seller.productStatusEnum.ResultEnum;
import com.yaoer.seller.sellExeception.SellExeception;
import com.yaoer.seller.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lenovo on 2018/3/9.
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public void create(@RequestParam(value = "orderId") String orderId,
                       @RequestParam(value = "returnUrl") String returnUrl){
    OrderDto orderDto= orderService.findByOrderId(orderId);
        if (orderDto==null){
            throw new SellExeception(ResultEnum.PARAM_ERROR);
        }


    }
}
