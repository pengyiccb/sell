package com.yaoer.seller.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2018/2/26.
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    private String mpAppId;

    private String mchId;

    private String mchKey;

    private String keyPath;

    private String notifyUrl;
}
