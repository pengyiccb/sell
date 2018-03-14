package com.yaoer.seller.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by lenovo on 2018/2/25.
 */
@RestController
@RequestMapping("/weixin")
public class WeixinController {

    private static Logger logger= LoggerFactory.getLogger(WeixinController.class);

    @GetMapping("/auth")
    public void auth(@RequestParam(value = "code")String code){

        System.out.println("进入方法");
        logger.info("【微信开发】code={}",code);
        String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx1fd5a541e6226a2e&secret=4e9566ba7373315cfeb75348b25379a5&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate=new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
       logger.info("response={}",response);
    }
}
