package com.yaoer.seller.service.impl;

import com.yaoer.seller.convert.OrderMaster2OrderDTOConverter;
import com.yaoer.seller.dataProject.OrderDetail;
import com.yaoer.seller.dataProject.OrderMaster;
import com.yaoer.seller.dataProject.ProductInfo;
import com.yaoer.seller.dto.CartDto;
import com.yaoer.seller.dto.OrderDto;
import com.yaoer.seller.productStatusEnum.OrderStatusEnum;
import com.yaoer.seller.productStatusEnum.PayStatusEnum;
import com.yaoer.seller.productStatusEnum.ResultEnum;
import com.yaoer.seller.repository.OrderDetailRepository;
import com.yaoer.seller.repository.OrderMasterRepository;
import com.yaoer.seller.sellExeception.SellExeception;
import com.yaoer.seller.service.OrderService;
import com.yaoer.seller.utils.KeyUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by lenovo on 2018/2/23.
 */
@Service
public class OrderServiceImpl implements OrderService{

    private static org.slf4j.Logger logger= LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ProductInfoServiceImpl service;
    @Autowired
    private OrderDetailRepository repository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        //查询商品
        String orderId=KeyUtil.genUniqueKey();
        BigDecimal totalAmount=new BigDecimal(0);

        for (OrderDetail orderDetail:orderDto.getOrderDetailList()){
        ProductInfo productInfo= service.findOne(orderDetail.getProductId());
            System.out.println(productInfo+".......");
            if (productInfo==null){
                throw new SellExeception(ResultEnum.ORDER_NOT_EXIST);
            }
            //计算总价
        totalAmount= productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(totalAmount);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(productInfo,orderDetail);
            repository.save(orderDetail);
           /* CartDto cartDto=new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDtoList.add(cartDto);*/
        }
        //写入订单数据库(OrderMaster,OrderDetail)
        OrderMaster orderMaster=new OrderMaster();
        System.out.println(orderMaster.getOrderAmount()+"............................................");

        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderAmount(totalAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        System.out.println(orderMaster);
        orderMasterRepository.save(orderMaster);
        //扣库存
        List<CartDto>cartDtoList= orderDto.getOrderDetailList().stream().map(e->new CartDto(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        System.out.println(cartDtoList);
        service.increaseStock(cartDtoList);
        return orderDto;
    }

    @Override
    public OrderDto findByOrderId(String orderId) {
        OrderMaster orderMaster=orderMasterRepository.findOne(orderId);
        if (orderMaster==null){
            throw new SellExeception(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail>orderDetailList=repository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            throw new SellExeception(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDto orderDto=new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page findOrderList(String buyerOpenid, Pageable pageable) {
      Page<OrderMaster> findList=orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
      Page page=new PageImpl(OrderMaster2OrderDTOConverter.convert(findList.getContent()),pageable,findList.getTotalElements());

        return page;
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {

        OrderMaster orderMaster=new OrderMaster();

        //判断订单状态
          if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
              logger.error("【取消订单】orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
              throw new SellExeception(ResultEnum.ORDER_STATUS_NOT_TRUE);
          }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster result=orderMasterRepository.save(orderMaster);

        if (result==null){
            logger.error("【取消订单】orderMaster={}",orderMaster);
            throw new SellExeception(ResultEnum.ORDER_CANCEL_FAIL);
        }
        //增加库存
        List<CartDto>cartDtoList=orderDto.getOrderDetailList().stream().map(e->new CartDto(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        service.decreaseStock(cartDtoList);
        //已支付需要退款
        //// TODO: 2018/2/24
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            logger.error("【完结订单】orderStatus={}",orderDto.getOrderStatus());
            throw new SellExeception(ResultEnum.ORDER_STATUS_NOT_TRUE);
        }
        OrderMaster orderMaster=new OrderMaster();
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster result= orderMasterRepository.save(orderMaster);
        if (result==null){
            logger.error("【完结订单】orderMaster={}",orderMaster);
            throw new SellExeception(ResultEnum.ORDER_CANCEL_FAIL);
        }
        //修改订单状态

        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            logger.error("【支付订单】orderStatus={}",orderDto.getOrderStatus());
            throw new SellExeception(ResultEnum.ORDER_STATUS_NOT_TRUE);
        }
        if (!orderDto.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            logger.error("【支付订单】orderStatus={}",orderDto.getOrderStatus());
            throw new SellExeception(ResultEnum.ORDER_STATUS_NOT_TRUE);
        }
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster ordermaster=new OrderMaster();
        BeanUtils.copyProperties(orderDto,ordermaster);
        OrderMaster result= orderMasterRepository.save(ordermaster);
        if (result==null){
            logger.error("【完结订单】orderMaster={}",ordermaster);
            throw new SellExeception(ResultEnum.ORDER_PAY_FAIL);
        }
        return orderDto;
    }
}
