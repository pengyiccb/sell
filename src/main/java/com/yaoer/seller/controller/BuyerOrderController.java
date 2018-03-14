package com.yaoer.seller.controller;

import com.yaoer.seller.VO.ResultVo;
import com.yaoer.seller.convert.OrderForm2OrderDto;
import com.yaoer.seller.dto.OrderDto;
import com.yaoer.seller.form.OrderForm;
import com.yaoer.seller.productStatusEnum.ResultEnum;
import com.yaoer.seller.sellExeception.SellExeception;
import com.yaoer.seller.service.BuyerService;
import com.yaoer.seller.service.OrderService;
import com.yaoer.seller.utils.ResultVoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/2/24.
 */
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    private static Logger logger= LoggerFactory.getLogger(BuyerOrderController.class);

    @PostMapping("/create")
    public ResultVo< Map<String,String>>create(@Validated OrderForm orderForm,
                                               BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            logger.error("【创建订单】购物车={}",orderForm.getItems());
            throw new SellExeception(ResultEnum.PARAM_ERROR);
        }
        OrderDto orderDto= OrderForm2OrderDto.concert(orderForm);
        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            logger.error("【创建订单】orderDetail={}",orderDto.getOrderDetailList());
            throw new SellExeception(ResultEnum.PARAM_ERROR);
        }
        OrderDto result=orderService.create(orderDto);
        Map<String,String>map=new HashMap<>();
        map.put("orderId",result.getOrderId());
        return ResultVoUtils.success(map);
    }

    @GetMapping("/list")
    public ResultVo<List<OrderDto>>list(@RequestParam(value = "openid") String openid,
                                        @RequestParam(value = "page",defaultValue = "0") Integer page,
                                        @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            logger.error("【查询列表】openid={}",openid);
            throw new SellExeception(ResultEnum.PARAM_ERROR);
        }
        PageRequest request=new PageRequest(page,size);
       Page<OrderDto>orderDtoPage=orderService.findOrderList(openid,request);
       return ResultVoUtils.success(orderDtoPage.getContent());
    }

    @GetMapping("/detail")
    public ResultVo<OrderDto>detail(@RequestParam(value = "openid") String openid,
                                    @RequestParam(value = "orderId") String orderId ){
        if (StringUtils.isEmpty(openid)){
            logger.error("【获取单个订单】openid={}",openid);
            throw new SellExeception(ResultEnum.PARAM_ERROR);
        }
       OrderDto orderDto= buyerService.findOrderOne(openid,orderId);
        return ResultVoUtils.success(orderDto);
    }

    @PostMapping("/cancel")
    public ResultVo<OrderDto>cancel(@RequestParam(value = "openid") String openid,
                                    @RequestParam(value = "orderId") String orderId ){
        if (StringUtils.isEmpty(openid)){
            logger.error("【获取单个订单】openid={}",openid);
            throw new SellExeception(ResultEnum.PARAM_ERROR);
        }
        buyerService.cancel(openid,orderId);
        return ResultVoUtils.success();
    }

}
