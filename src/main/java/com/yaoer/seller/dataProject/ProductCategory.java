package com.yaoer.seller.dataProject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lenovo on 2018/2/21.
 */
@Entity
@Table(name = "product_category")
@DynamicUpdate
@Data
public class ProductCategory {

    public ProductCategory() {
    }

    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;



    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}