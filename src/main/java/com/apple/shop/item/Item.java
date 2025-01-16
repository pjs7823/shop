package com.apple.shop.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // entity 가 추가될때마다 알아서 1씩 증가시켜줌, Auto increment 기능
    public Long id;
    // @Column(nullable = false) // null 금지
    private String title;
    private Integer price;




}
