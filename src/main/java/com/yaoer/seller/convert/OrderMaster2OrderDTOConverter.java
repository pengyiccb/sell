package com.yaoer.seller.convert;

import com.yaoer.seller.dataProject.OrderMaster;
import com.yaoer.seller.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lenovo on 2018/2/24.
 */
public class OrderMaster2OrderDTOConverter {

    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto=new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    public static List<OrderDto>convert(List<OrderMaster>orderMasterList){
      return   orderMasterList.stream().map(e->
        convert(e)
        ).collect(Collectors.toList());
    }
}
