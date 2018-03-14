package com.yaoer.seller.productStatusEnum;

/**
 * Created by lenovo on 2018/2/22.
 */
public enum ProductStatusEnum {
    up(0,"上架"),
    down(1,"下架")
    ;

    private int code;

    private String decription;

    ProductStatusEnum(int code, String decription) {
        this.code = code;
        this.decription = decription;
    }

    public int getCode() {
        return code;
    }

    public String getDecription() {
        return decription;
    }
}
