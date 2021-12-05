package com.borshcheva.shop.dao.jdbc.mapper;


import com.borshcheva.shop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductRowMapper {
    public Product mapRow(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");
        Timestamp creationDate = resultSet.getTimestamp("creation_date");
        Product product = Product.builder()
                .id(id)
                .price(price)
                .name(name)
                .creationDate(creationDate)
                .build();

        return product;
    }
}
