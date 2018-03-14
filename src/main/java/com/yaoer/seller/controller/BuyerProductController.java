package com.yaoer.seller.controller;

import com.yaoer.seller.VO.ProductInfoVo;
import com.yaoer.seller.VO.ProductVo;
import com.yaoer.seller.VO.ResultVo;
import com.yaoer.seller.dataProject.ProductCategory;
import com.yaoer.seller.dataProject.ProductInfo;
import com.yaoer.seller.productStatusEnum.ProductStatusEnum;
import com.yaoer.seller.service.CategoryService;
import com.yaoer.seller.service.ProductInfoService;
import com.yaoer.seller.utils.ResultVoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by lenovo on 2018/2/22.
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVo list(){
        List<ProductInfo>productInfoList= productInfoService.findByStatusUp();
        List<ProductVo>productVoList=this.find(productInfoList);
        return ResultVoUtils.success(productVoList);
    }


    private List<ProductVo> find( List<ProductInfo>productInfoList) {
        List<Integer>list= productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
       List<ProductCategory>productCategoryList= categoryService.findCategoryByTypeIn(list);
        List<ProductVo>productVoList=new ArrayList<ProductVo>();
        for (ProductCategory productCategory:productCategoryList){
           ProductVo productVo=new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVo>productInfoVoList=new ArrayList<ProductInfoVo>();
            for (ProductInfo productInfo:productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                ProductInfoVo productInfoVo=new ProductInfoVo();
                BeanUtils.copyProperties(productInfo,productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
                }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
    return productVoList;

    }
}
