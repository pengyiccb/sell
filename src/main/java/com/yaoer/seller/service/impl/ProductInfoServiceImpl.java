package com.yaoer.seller.service.impl;

import com.yaoer.seller.dataProject.OrderDetail;
import com.yaoer.seller.dataProject.ProductInfo;
import com.yaoer.seller.dto.CartDto;
import com.yaoer.seller.productStatusEnum.ProductStatusEnum;
import com.yaoer.seller.productStatusEnum.ResultEnum;
import com.yaoer.seller.repository.ProductInfoReposity;
import com.yaoer.seller.sellExeception.SellExeception;
import com.yaoer.seller.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lenovo on 2018/2/22.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoReposity reposity;

    @Override
    public ProductInfo findOne(String productId) {
        return  reposity.findOne(productId);
    }

    @Override
    public List<ProductInfo> findByStatusUp() {
        return reposity.findByProductStatus(ProductStatusEnum.up.getCode());
    }

    @Override
    public Page findAll(Pageable pageable) {
        PageRequest pageRequest=new PageRequest(0,2);
        return reposity.findAll(pageRequest);
    }


    @Override
    public ProductInfo save(ProductInfo productInfo) {
       return reposity.save(productInfo);
    }


    //减少库存
    @Override
    @Transactional//失败的话事物回滚
    public void increaseStock(List<CartDto> cartDtoList) {

        for (CartDto cartDto:cartDtoList){
            ProductInfo productInfo=reposity.findOne(cartDto.getProductId());
            if (productInfo==null){
                throw new SellExeception(ResultEnum.ORDER_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()-cartDto.getProductQuantity();
            if (result<0){
                throw new SellExeception(ResultEnum.STOCK_NOT_FULL);
            }
            productInfo.setProductStock(result);
            reposity.save(productInfo);
        }


    }


    //增加库存
    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {

        for (CartDto cartDto:cartDtoList){
            ProductInfo productInfo=reposity.findOne(cartDto.getProductId());
            if (productInfo==null){
                throw new SellExeception(ResultEnum.STOCK_DECREASE_FAIL);
            }
            Integer result=productInfo.getProductStock()+cartDto.getProductQuantity();
            productInfo.setProductStock(result);
            reposity.save(productInfo);
        }
    }


}
