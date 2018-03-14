package com.yaoer.seller.controller;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.net.URLEncoder;

/**
 * Created by lenovo on 2018/2/26.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {

    private static Logger logger= LoggerFactory.getLogger(WechatController.class);
    @Autowired
    private WxMpService wxMpService;

    @RequestMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl){

        String url="http://aifl.natapp1.cc/sell/wechat/userinfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    @RequestMapping("/userinfo")
    public String userinfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken=new WxMpOAuth2AccessToken();
        try {
             wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            e.printStackTrace();
            logger.error("【微信网页授权】e={}",e);
        }
        String openId= wxMpOAuth2AccessToken.getOpenId();
        logger.info("openid={}",openId);
        return "redirect:" + returnUrl + "?openid=" + openId;
    }
}
