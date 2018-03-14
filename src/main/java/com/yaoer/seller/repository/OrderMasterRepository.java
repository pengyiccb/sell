package com.yaoer.seller.repository;

import com.yaoer.seller.dataProject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lenovo on 2018/2/23.
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster>findByBuyerOpenid(String openid, Pageable pageable);

}
