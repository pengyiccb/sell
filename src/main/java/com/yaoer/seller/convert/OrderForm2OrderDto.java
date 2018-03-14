package com.yaoer.seller.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yaoer.seller.dataProject.OrderDetail;
import com.yaoer.seller.dto.OrderDto;
import com.yaoer.seller.form.OrderForm;
import com.yaoer.seller.productStatusEnum.ResultEnum;
import com.yaoer.seller.sellExeception.SellExeception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/2/24.
 */
public class OrderForm2OrderDto {

    private static Logger logger= LoggerFactory.getLogger(OrderForm2OrderDto.class);


    public static OrderDto concert(OrderForm orderForm){
        Gson gson=new Gson();
        OrderDto orderDto=new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail>orderDetailList=new ArrayList<>();

        try {
            orderDetailList=gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){

            logger.error("【json数据转化】String={}",orderForm.getItems());
            throw new SellExeception(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

}
