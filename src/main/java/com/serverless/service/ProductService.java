package com.serverless.service;

import com.serverless.dao.ProductDao;
import com.serverless.model.Product;
import com.serverless.request.ProductRequest;

import java.util.List;
import java.util.UUID;

public class ProductService {

    private final ProductDao productDao;

    public ProductService() {
        this.productDao = new ProductDao();
    }

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProductById(String id) {
        return productDao.getProductById(id);
    }

    public void createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        productDao.createProduct(product);
    }

    public void updateProduct(String id, ProductRequest productRequest) {
        Product existingProduct = productDao.getProductById(id);
        if (existingProduct != null) {
            existingProduct.setName(productRequest.getName());
            existingProduct.setPrice(productRequest.getPrice());
            productDao.updateProduct(existingProduct);
        }
    }

    public void deleteProduct(String id) {
        productDao.deleteProduct(id);
    }
}

