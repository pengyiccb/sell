package com.yaoer.seller.service.impl;

import com.sun.xml.internal.ws.developer.Serialization;
import com.yaoer.seller.dto.OrderDto;
import com.yaoer.seller.productStatusEnum.ResultEnum;
import com.yaoer.seller.sellExeception.SellExeception;
import com.yaoer.seller.service.BuyerService;
import com.yaoer.seller.service.OrderService;
import com.yaoer.seller.utils.ResultVoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2018/2/25.
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    private static Logger logger= LoggerFactory.getLogger(BuyerServiceImpl.class);

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {

        return checkOwnerOrder(openid,orderId);
    }

    @Override
    public OrderDto cancel(String openid, String orderId) {

        OrderDto orderDto=checkOwnerOrder(openid,orderId);
        if (orderDto==null){
            logger.error("【取消订单】orderId={}",orderId);
            throw new SellExeception(ResultEnum.ORDER_CANCEL_FAIL);
        }
        OrderDto result=orderService.cancel(orderDto);

        return result;
    }

    private OrderDto checkOwnerOrder(String openid,String orderId){
        OrderDto orderDto=orderService.findByOrderId(orderId);
        if (orderDto==null){
            return null;
        }
        if (!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
            logger.error("【取消订单】buyerOpenid={},openid={},orderId={}",orderDto.getBuyerOpenid(),openid,orderId);
            throw new SellExeception(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
