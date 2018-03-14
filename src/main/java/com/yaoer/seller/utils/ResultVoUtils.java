package com.yaoer.seller.utils;

import com.yaoer.seller.VO.ResultVo;

import java.util.List;
import java.util.Objects;

/**
 * Created by lenovo on 2018/2/22.
 */
public class ResultVoUtils {

    public static ResultVo success(Object object){
        ResultVo resultVo=new ResultVo();
        resultVo.setData(object);
        resultVo.setMessage("成功");
        resultVo.setCode(0);
        return resultVo;
    }

    public static ResultVo success(){

        return success(null);
    }
    public static ResultVo failed(int code,String message){
        ResultVo resultVo=new ResultVo();
        resultVo.setCode(code);
        resultVo.setMessage(message);
        return resultVo;
    }


}
