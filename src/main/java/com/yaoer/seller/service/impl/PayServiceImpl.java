package com.yaoer.seller.service.impl;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.yaoer.seller.config.WechatAccountConfig;
import com.yaoer.seller.dto.OrderDto;
import com.yaoer.seller.service.PayService;
import com.yaoer.seller.utils.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2018/3/9.
 */
@Service
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME="微信支付订单";
    private static final Logger logger= LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Override
    public void create(OrderDto orderDto) {
        PayRequest request=new PayRequest();
        request.setOpenid(orderDto.getBuyerOpenid());
        request.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        request.setOrderId(orderDto.getOrderId());
        request.setOrderName(ORDER_NAME);
        request.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        bestPayService.pay(request);
        logger.info("【微信支付】request={}", GsonUtils.object2Json(request));
        PayResponse response=bestPayService.pay(request);
        logger.info("【微信支付】response={}",GsonUtils.object2Json(response));
    }
}
