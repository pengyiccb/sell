package com.yaoer.seller.VO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.List;

/**
 * Created by lenovo on 2018/2/22.
 */
@Data
public class ProductVo {
    @JsonProperty("name")
    private String  categoryName;

    @JsonProperty("type")
    private int categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo>productInfoVoList;
  }
