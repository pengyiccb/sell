package com.yaoer.seller.service.impl;

import com.yaoer.seller.dataProject.OrderDetail;
import com.yaoer.seller.dto.CartDto;
import com.yaoer.seller.dto.OrderDto;
import com.yaoer.seller.productStatusEnum.OrderStatusEnum;
import com.yaoer.seller.productStatusEnum.PayStatusEnum;
import com.yaoer.seller.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2018/2/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    public final  String OPEN_ID="110110";
    private final String ORDER_ID="1519402865055716101";
    private static Logger logger= LoggerFactory.getLogger(OrderServiceImplTest.class);

    @Test
    public void testCreate() throws Exception {
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerOpenid(OPEN_ID);
        orderDto.setBuyerPhone("121321212");
        orderDto.setBuyerAddress("福州");
        orderDto.setBuyerName("斩招");
        List<OrderDetail> orderDetailList=new ArrayList<>();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("123127");
        orderDetail.setProductQuantity(2);
        OrderDetail o1=new OrderDetail();
        o1.setProductId("123126");
        o1.setProductQuantity(3);
        orderDetailList.add(o1);
        orderDetailList.add(orderDetail);
        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result=orderService.create(orderDto);
        logger.info("【订单详情】result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void testFindByOrderId() throws Exception {

      OrderDto orderDto=  orderService.findByOrderId(ORDER_ID);
        logger.info("【查询单个订单】order={}",orderDto);
        Assert.assertNotNull(orderDto);
    }

    @Test
    public void testFindOrderList() throws Exception {
        PageRequest request=new PageRequest(0,2);
      Page page=orderService.findOrderList(OPEN_ID,request);
        System.out.println( page.getTotalElements()+"..........");
        logger.info("【page详情】page={}",page);
        Assert.assertNotNull(page);
    }

    @Test
    public void testCancel() throws Exception {
       OrderDto orderDto=orderService.findByOrderId(ORDER_ID);
       OrderDto result=orderService.cancel(orderDto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void testFinish() throws Exception {
        OrderDto orderDto=orderService.findByOrderId(ORDER_ID);
        OrderDto result=orderService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void testPaid() throws Exception {
        OrderDto orderDto=orderService.findByOrderId(ORDER_ID);
        OrderDto result=orderService.paid(orderDto);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }
}