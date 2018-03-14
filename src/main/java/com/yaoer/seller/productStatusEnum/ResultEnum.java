package com.yaoer.seller.productStatusEnum;

import lombok.Getter;

/**
 * Created by lenovo on 2018/2/23.
 */
@Getter
public enum ResultEnum {
    PARAM_ERROR(1,"参数错误"),
    ORDER_OWNER_ERROR(2,"订单不是自己的"),
    ORDER_NOT_EXIST(10,"订单不存在"),
    STOCK_NOT_FULL(20,"库存不足"),
    ORDERDETAIL_NOT_EXIST(30,"订单详情不存在"),
    ORDER_STATUS_NOT_TRUE(40,"订单状态不正确"),
    ORDER_CANCEL_FAIL(50,"订单取消失败"),
    STOCK_DECREASE_FAIL(60,"增加库存失败"),
    ORDER_PAY_FAIL(70,"订单支付失败"),
    ;


    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
