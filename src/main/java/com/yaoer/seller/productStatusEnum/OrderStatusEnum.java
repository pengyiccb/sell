package com.yaoer.seller.productStatusEnum;

import lombok.Getter;

/**
 * Created by lenovo on 2018/2/23.
 */
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    CANCEL(1,"已取消"),
    FINISHED(2,"完结"),
    ;


    private Integer code;
    private String message;

    OrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
