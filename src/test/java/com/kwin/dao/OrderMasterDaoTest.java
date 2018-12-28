package com.kwin.dao;

import com.kwin.entity.OrderMaster;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao dao;

    private static final String OPENID = "110110";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("Kwin");
        orderMaster.setBuyerAddress("302");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setBuyerPhone("12345678910");
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = dao.save(orderMaster);
        assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenidTest() {
        PageRequest request = PageRequest.of(0, 3);
        Page<OrderMaster> page = dao.findByBuyerOpenid(OPENID, request);
//        System.out.println(page.getTotalElements());
        assertNotEquals(0,page.getSize());
    }
}