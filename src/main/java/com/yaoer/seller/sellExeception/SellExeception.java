package com.yaoer.seller.sellExeception;

import com.yaoer.seller.productStatusEnum.ResultEnum;

/**
 * Created by lenovo on 2018/2/23.
 */
public class SellExeception extends RuntimeException {

    private int code;

    public SellExeception(ResultEnum resultEnum) {

        super(resultEnum.getMessage());
        this.code=code;
    }
}
