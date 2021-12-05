package com.borshcheva.shop.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
public class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Timestamp creationDate;

}
