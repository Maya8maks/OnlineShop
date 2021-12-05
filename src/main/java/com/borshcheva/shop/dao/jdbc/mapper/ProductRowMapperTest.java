package com.borshcheva.shop.dao.jdbc.mapper;

import com.borshcheva.shop.entity.Product;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductRowMapperTest {

    @Test
    public void testMapRow() throws SQLException {
        ProductRowMapper productRowMapper = new ProductRowMapper();
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.getString("name")).thenReturn("product1");
        when(resultSetMock.getInt("id")).thenReturn(1);
        when(resultSetMock.getInt("price")).thenReturn(100);
        when(resultSetMock.getTimestamp("creation_date")).thenReturn(Timestamp.valueOf("2021-12-12 10:50:50"));

        Product product = productRowMapper.mapRow(resultSetMock);
        assertEquals(1, product.getId());
        assertEquals("product1", product.getName());
        assertEquals(100, product.getPrice());
        assertEquals(Timestamp.valueOf("2021-12-12 10:50:50"), product.getCreationDate());
    }
}
