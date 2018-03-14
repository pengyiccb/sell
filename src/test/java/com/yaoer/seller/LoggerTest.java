package com.yaoer.seller;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lenovo on 2018/2/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoggerTest {

    private final Logger logger= LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test(){

        String name="张三";
        int age =18;
        logger.info("name:{},age:{}",name,age);
        logger.info("info.....");
        logger.error("error....");
    }
}
