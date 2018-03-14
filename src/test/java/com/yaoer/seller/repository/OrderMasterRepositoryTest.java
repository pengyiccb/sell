package com.yaoer.seller.repository;

import com.yaoer.seller.dataProject.OrderMaster;
import com.yaoer.seller.productStatusEnum.OrderStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2018/2/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void testFindByBuyerOpenId() throws Exception {

        PageRequest request=new PageRequest(0,2);
      Page<OrderMaster>page= repository.findByBuyerOpenid("110110",request);
        System.out.println(page.getTotalElements());

    }


    @Test
    public void findAllTest(){
        List<OrderMaster>orderMasterList=repository.findAll();
        Assert.assertNotEquals(0,orderMasterList.size());
    }

    @Test
    public void testFindOne() {

        OrderMaster orderMaster = repository.findOne("123123");

        Assert.assertNotNull(orderMaster);
    }


    @Test
    public void testSave(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("123124");
        orderMaster.setBuyerName("彭师弟");
        orderMaster.setBuyerOpenid("8008208820");
        orderMaster.setBuyerAddress("江西省抚州市");
        orderMaster.setBuyerPhone("12132132312");
        orderMaster.setOrderAmount(new BigDecimal(65.0));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
       OrderMaster master= repository.save(orderMaster);
        Assert.assertNotNull(master);
    }
}