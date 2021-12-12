package com.borshcheva.shop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class User {
    private Integer id;
    private String email;
    private String password;
    private String sole;
    private String token;

}
