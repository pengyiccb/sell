package com.yaoer.seller.productStatusEnum;

import lombok.Getter;

/**
 * Created by lenovo on 2018/2/23.
 */
@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),

    ;

    private Integer code;

    private String message;

    PayStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}