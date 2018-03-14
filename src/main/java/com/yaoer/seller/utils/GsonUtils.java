package com.yaoer.seller.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by lenovo on 2018/3/13.
 */
public class GsonUtils {
    public static String object2Json(Object object){
      GsonBuilder gsonBuilder=  new GsonBuilder();
      gsonBuilder.setPrettyPrinting();
        Gson gson=gsonBuilder.create();
        return gson.toJson(object);
    }
}
