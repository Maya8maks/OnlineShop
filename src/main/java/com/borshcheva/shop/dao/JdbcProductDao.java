package com.borshcheva.shop.dao;


import com.borshcheva.shop.dao.jdbc.mapper.ProductRowMapper;
import com.borshcheva.shop.entity.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JdbcProductDao implements ProductDao {

    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();
    private static final String FIND_ALL_SQL = "SELECT id, name, price, creation_date FROM product;";
    private static final String FIND_BY_ID_SQL = "SELECT id, name, price, creation_date FROM product WHERE id=?";
    private static final String DELETE_BY_ID = "DELETE FROM product WHERE id=?;";
    private static final String UPDATE = "UPDATE product SET name=?, price=? WHERE id=?;";
    public static final String INSERT = "INSERT INTO product(name, price) VALUES (?, ?);";

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/onlineShop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    @Override
    public List<Product> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = prepareStatement.executeQuery()) {

            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                products.add(PRODUCT_ROW_MAPPER.mapRow(resultSet));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Product findById(int id) {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(FIND_BY_ID_SQL)
        ) {

            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            resultSet.next();
            return PRODUCT_ROW_MAPPER.mapRow(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(DELETE_BY_ID)
        ) {
            prepareStatement.setInt(1, id);
            prepareStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void update(int id, String name, int price) {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(UPDATE)
        ) {
            prepareStatement.setString(1, name);
            prepareStatement.setInt(2, price);
            prepareStatement.setInt(3, id);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void create(String name, int price) {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(INSERT)) {
            prepareStatement.setString(1, name);
            prepareStatement.setInt(2, price);

            prepareStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
