package com.kwin.dao;

import com.kwin.entity.ProductInfo;
import org.hibernate.validator.constraints.NotEmpty;
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
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao dao;


    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的粥。");
        productInfo.setProductStatus(1);
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setCategoryType(2);
        ProductInfo productInfo1 = dao.save(productInfo);
//        System.out.println(productInfo1);
        assertNotNull(productInfo1);
    }

    @Test
    public void findByProductStatusTest() {
        List<ProductInfo> list = dao.findByProductStatus(1);
//        for (ProductInfo productInfo : list) {
//            System.out.println(productInfo);
//        }
        assertNotEquals(0,list.size());
    }
}