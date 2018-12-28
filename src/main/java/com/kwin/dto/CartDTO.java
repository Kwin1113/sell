package com.kwin.dto;

import lombok.Data;

@Data
public class CartDTO {

    /** 商品id */
    private String productId;

    /** productQuantity */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
