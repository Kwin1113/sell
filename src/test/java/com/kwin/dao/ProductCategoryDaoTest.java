package com.kwin.dao;

import com.kwin.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao dao;

    @Test
    public void findById() {
        ProductCategory productCategory = dao.findById(1).get();
        System.out.println(productCategory);
    }

    @Test
    public void insert() {
        ProductCategory productCategory = dao.findById(2).get();
        productCategory.setCategoryType(4);
        dao.save(productCategory);
    }

    @Test
    public void findByCategoryInTest() {
        List<Integer> list = Arrays.asList(2, 3, 4);
        List<ProductCategory> result = dao.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, list.size());
    }
}