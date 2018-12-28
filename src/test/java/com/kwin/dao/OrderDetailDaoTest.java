package com.kwin.dao;

import com.kwin.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao detailDao;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("111111");
        orderDetail.setOrderId("1111111");
        orderDetail.setProductId("11111111");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductPrice(new BigDecimal(2.2));
        orderDetail.setProductQuantity(2);

        OrderDetail result = detailDao.save(orderDetail);
        assertNotNull(result);
    }

    @Test
    public void findByOrderIdTest() {
        List<OrderDetail> list = detailDao.findByOrderId("1111111");
        assertNotEquals(0, list.size());
    }
}