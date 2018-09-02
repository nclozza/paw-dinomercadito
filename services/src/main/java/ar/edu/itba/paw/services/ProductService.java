package ar.edu.itba.paw.services;

import ar.edu.itba.paw.models.Product;

public interface ProductService {
    boolean createProduct(Product product);
    boolean deleteProduct(String productName);
    Product getProduct(String productName);
    Product updateProduct(Product product);
}
