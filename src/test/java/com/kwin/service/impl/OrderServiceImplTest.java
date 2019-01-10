package com.kwin.service.impl;

import com.kwin.dto.OrderDTO;
import com.kwin.entity.OrderDetail;
import com.kwin.enums.OrderStatusEnum;
import com.kwin.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl service;

    private static final String BUYER_OPENID = "110110";

    private static final String ORDER_ID = "1545986466117887816";

    @Test
    public void createOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("302");
        orderDTO.setBuyerName("Kwin");
        orderDTO.setBuyerPhone("12345678910");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1234567");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO orderDTO1 = service.createOrder(orderDTO);
        log.info("[创建订单]result={}", orderDTO1);
        assertNotNull(orderDTO1);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = service.findOne(ORDER_ID);
        log.info("[查询单个订单]result={}", orderDTO);
        assertEquals(ORDER_ID,orderDTO.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = service.findList(BUYER_OPENID, request);
        log.info("[查询列表]result={}", orderDTOPage);
        assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = service.findOne(ORDER_ID);
        OrderDTO result = service.cancel(orderDTO);
        assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = service.findOne("1545986317038608939");
        OrderDTO result = service.finish(orderDTO);
        assertEquals(OrderStatusEnum.FINISH.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = service.findOne("123456");
        OrderDTO result = service.paid(orderDTO);
        assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    public void list() {
        PageRequest request = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = service.findList(request);
        log.info("[查询列表]result={}", orderDTOPage);
        assertNotEquals(0,orderDTOPage.getTotalElements());
    }
}