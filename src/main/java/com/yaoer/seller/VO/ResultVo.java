package com.yaoer.seller.VO;

import lombok.Data;

import java.util.List;

/**
 * Created by lenovo on 2018/2/22.
 */
@Data
public class ResultVo <T>{

    private int code;

    private String message;

    private T data;

}
