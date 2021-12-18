package com.borshcheva.shop.dao.jdbc.mapper;

import com.borshcheva.shop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper {
    public User mapRow(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String sole = resultSet.getString("sole");

        User user = User.builder()
                .id(id)
                .email(email)
                .password(password)
                .sole(sole)
                .build();

        return user;
    }
}
