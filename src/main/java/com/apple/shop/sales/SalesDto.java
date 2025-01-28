package com.apple.shop.sales;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesDto{
    private String itemName;
    private Integer price;
    private Integer count; // 추가 필드
    private String userName;
}