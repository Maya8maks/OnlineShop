package com.borshcheva.shop.service;


import com.borshcheva.shop.dao.ProductDao;
import com.borshcheva.shop.entity.Product;

import java.util.List;


public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product getProduct(int id) {
        return productDao.findById(id);
    }

    public void deleteProduct(int id) {

            productDao.delete(id);

    }

    public void update(int id, String name, int price) {
        productDao.update(id, name, price);
    }

    public void create(String name, int price) {
        productDao.create(name, price);
    }
}
