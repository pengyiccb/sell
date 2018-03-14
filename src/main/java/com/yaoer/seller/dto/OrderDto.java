package com.yaoer.seller.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yaoer.seller.dataProject.OrderDetail;
import com.yaoer.seller.utils.Date2LongSerlalizer;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2018/2/23.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    /** 订单id. */
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus ;

    /** 创建时间. */
    @JsonSerialize(using = Date2LongSerlalizer.class)
    private Date createTime;

    /** 更新时间. */
    @JsonSerialize(using = Date2LongSerlalizer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

}
