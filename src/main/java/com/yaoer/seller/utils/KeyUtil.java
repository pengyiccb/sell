package com.yaoer.seller.utils;

import java.util.Random;

/**
 * Created by lenovo on 2018/2/23.
 */

public class KeyUtil {

    //生成订单号：时间加随机数
    public static synchronized String genUniqueKey(){
        Random random=new Random();
        Integer number=random.nextInt(900000)+100000;
        return System.currentTimeMillis()+number.toString();
    }
}
