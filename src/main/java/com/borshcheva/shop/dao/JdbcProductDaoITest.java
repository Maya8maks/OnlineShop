package com.borshcheva.shop.dao;

import com.borshcheva.shop.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcProductDaoITest {

    @Test
    public void testFindAllReturnCorrectData() {
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        List<Product> products = jdbcProductDao.findAll();
        assertFalse(products.isEmpty());
        for (Product product : products) {
            assertNotEquals(0, product.getId());
            assertNotNull(product.getName());
            assertNotNull(product.getPrice());
            assertNotNull(product.getCreationDate());
        }
    }
}
