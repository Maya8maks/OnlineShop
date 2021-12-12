package com.borshcheva.shop.dao.jdbc.mapper;

import com.borshcheva.shop.entity.Product;
import com.borshcheva.shop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserRowMapper {
    public User mapRow(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String token = resultSet.getString("token");

        User user = User.builder()
                .id(id)
                .email(email)
                .password(password)
                .token(token)
                .build();

        return user;
    }
}
