package com.kwin.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    UP(1,"在架"),
    DOWN(0,"下架")
    ;
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
