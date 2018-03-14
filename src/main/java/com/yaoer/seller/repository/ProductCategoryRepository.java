package com.yaoer.seller.repository;

import com.yaoer.seller.dataProject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by lenovo on 2018/2/21.
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer>categoryTypeList);
}
