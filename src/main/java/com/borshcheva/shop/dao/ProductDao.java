package com.borshcheva.shop.dao;

import com.borshcheva.shop.entity.Product;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    Product findById(int id);

    void delete(int id);

    void update(int id, String name, int price);

    void create(String name, int price);
}
