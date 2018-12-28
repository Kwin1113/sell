package com.kwin.dao;

import com.kwin.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoDao extends JpaRepository<ProductInfo, String> {

    /** 通过商家商品查找商品信息 */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
