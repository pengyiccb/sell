package com.yaoer.seller.service.impl;

import com.yaoer.seller.dataProject.ProductCategory;
import com.yaoer.seller.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2018/2/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private   ProductCategoryRepository productCategoryRepository;

    @Test
    public void testFindOne() throws Exception {

     ProductCategory result= productCategoryRepository.findOne(1);
        Assert.assertNotNull(0);
    }

    @Test
    public void testFindCategoryByTypeIn() throws Exception {

        List<Integer>categoryTypeList= Arrays.asList(1,3,5);
        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
        Assert.assertNotNull(result);
    }

    @Test
    public void testSave() throws Exception {

        ProductCategory productCategory=new ProductCategory("热销榜",3);
        ProductCategory result=productCategoryRepository.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void testFindAll() throws Exception {

        List<ProductCategory> productCategoryList=productCategoryRepository.findAll();
        Assert.assertNotNull(productCategoryList);
    }
}