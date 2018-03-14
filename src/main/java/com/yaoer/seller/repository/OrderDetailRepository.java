package com.yaoer.seller.repository;

import com.yaoer.seller.dataProject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lenovo on 2018/2/23.
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    List<OrderDetail>findByOrderId(String orderId);
}
