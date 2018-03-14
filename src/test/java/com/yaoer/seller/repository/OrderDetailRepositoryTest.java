package com.yaoer.seller.repository;

import com.yaoer.seller.dataProject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2018/2/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void testFindByOrderId() throws Exception {

        List<OrderDetail>orderDetailList=repository.findByOrderId("123123");
        Assert.assertNotEquals(0,orderDetailList.size());
    }

    @Test
    public void testSave(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("121212");
        orderDetail.setProductIcon("www.baidu.comm");
        orderDetail.setProductId("123123");
        orderDetail.setOrderId("123123");
        orderDetail.setProductName("纯牛奶");
        orderDetail.setProductPrice(new BigDecimal(6.3));
        orderDetail.setProductQuantity(2);
        OrderDetail result=repository.save(orderDetail);
        Assert.assertNotNull(result);
    }
}