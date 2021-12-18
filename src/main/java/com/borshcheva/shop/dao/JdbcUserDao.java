package com.borshcheva.shop.dao;

import java.sql.*;
import java.util.Objects;

import com.borshcheva.shop.dao.jdbc.mapper.UserRowMapper;
import com.borshcheva.shop.entity.User;
import org.apache.commons.codec.digest.DigestUtils;


public class JdbcUserDao implements UserDao {
    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/onlineShop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String FIND_BY_EMAIL = "SELECT id, password, sole, email FROM users WHERE email = ?";
    private static final String INSERT_INTO = "INSERT INTO users (email, password, sole) VALUES (?, ?, ?);";

    @Override
    public User findUser(String email, String password) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String sole = resultSet.getString("sole");
                String passwordHash = DigestUtils.md5Hex(sole + password);
                if (Objects.equals(passwordHash, resultSet.getString("password"))) {
                    return USER_ROW_MAPPER.mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public void create(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO)
        ) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
