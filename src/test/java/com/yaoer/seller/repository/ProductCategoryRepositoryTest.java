package com.yaoer.seller.repository;

import com.yaoer.seller.dataProject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lenovo on 2018/2/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOne(){
        ProductCategory productCategory= productCategoryRepository.findOne(1);
        System.out.println(productCategory.toString());
    }

    @Test
    public void save(){
        ProductCategory productCategory=new ProductCategory("男生最爱",4);
     ProductCategory result=   productCategoryRepository.save(productCategory);
        org.junit.Assert.assertNotNull(result);
    }

    @Test
    public void update(){
        ProductCategory productCategory=productCategoryRepository.findOne(3);
        productCategory.setCategoryType(3);
        productCategoryRepository.save(productCategory);

    }

    @Test
    public void findByCategoryType(){
        List<Integer>categoryTypeList= Arrays.asList(1,3,5,7);
        List<ProductCategory>productCategoryList=productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
        org.junit.Assert.assertNotEquals(0,productCategoryList);
    }
}