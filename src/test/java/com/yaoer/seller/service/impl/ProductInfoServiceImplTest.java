package com.yaoer.seller.service.impl;

import com.yaoer.seller.dataProject.ProductInfo;
import com.yaoer.seller.productStatusEnum.ProductStatusEnum;
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
 * Created by lenovo on 2018/2/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void testFindOne() throws Exception {
       ProductInfo productInfo= productInfoService.findOne("123123");
        Assert.assertNotNull(productInfo);

    }

    @Test
    public void testFindByStatusUp() throws Exception {

    List<ProductInfo>productInfoList= productInfoService.findByStatusUp();
    Assert.assertNotEquals(0,productInfoList.size());
    }

    @Test
    public void testFindAll() throws Exception {

        PageRequest pageRequest=new PageRequest(0,2);

       Page page= productInfoService.findAll(pageRequest);
        System.out.println(page.getTotalElements());
    }

    @Test
    public void testSave() throws Exception {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("123127");
        productInfo.setProductName("纯牛奶");
        productInfo.setProductPrice(new BigDecimal(6.5));
        productInfo.setProductDescription("炒好喝");
        productInfo.setProductStock(10);
        productInfo.setProductStatus(0);
        productInfo.setProductIcon("http://www.baidu.com");
        productInfo.setCategoryType(5);
        ProductInfo result=  productInfoService.save(productInfo);
        Assert.assertNotNull(result);
    }
}