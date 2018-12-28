package com.kwin.service.impl;

import com.kwin.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl service;

    @Test
    public void findOne() {
        ProductCategory productCategory = service.findOne(1);
        System.out.println(productCategory);
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = service.findAll();
        for (ProductCategory productCategory : list) {
            System.out.println(productCategory);
        }
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> li = Arrays.asList(1, 2, 3);
        List<ProductCategory> list = service.findByCategoryTypeIn(li);
        for (ProductCategory productCategory : list) {
            System.out.println(productCategory);
        }
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("女生最爱", 3);
        ProductCategory p = service.save(productCategory);
        System.out.println(p);
    }
}