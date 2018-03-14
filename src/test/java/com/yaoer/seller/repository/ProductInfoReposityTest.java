package com.yaoer.seller.repository;

import com.yaoer.seller.dataProject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2018/2/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoReposityTest {

    @Autowired
    private ProductInfoReposity reposity;

    @Test
    public void testFindByProductStates() throws Exception {
      List<ProductInfo>productInfoList= reposity.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }


    @Test
    public void save(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("123123");
        productInfo.setProductName("龙卷风辣条");
        productInfo.setProductPrice(new BigDecimal(3.0));
        productInfo.setProductDescription("炒鸡辣");
        productInfo.setProductStock(10);
        productInfo.setProductStatus(0);
        productInfo.setProductIcon("http://www.baidu.com");
        productInfo.setCategoryType(3);
        ProductInfo result=  reposity.save(productInfo);
        Assert.assertNotNull(result);
    }

}