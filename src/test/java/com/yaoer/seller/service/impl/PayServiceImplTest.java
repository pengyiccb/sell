package com.yaoer.seller.service.impl;

import com.yaoer.seller.dto.OrderDto;
import com.yaoer.seller.service.OrderService;
import com.yaoer.seller.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2018/3/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    private Logger logger= LoggerFactory.getLogger(PayServiceImplTest.class);

    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;

    @Test
    public void testCreate() throws Exception {
        OrderDto orderDto=orderService.findByOrderId("1519545725504564095");
        payService.create(orderDto);
    }
}