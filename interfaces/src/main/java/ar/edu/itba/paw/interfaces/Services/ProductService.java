package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.Product;

import java.awt.*;
import java.util.List;

public interface ProductService {
    public Product createProduct(String productName, String brand, String ram, String storage,
                          String operativeSystem, String processor, String bodySize, String screenSize,
                          String screenRatio, String rearCamera, String frontCamera);
    public boolean deleteProduct(Integer productId);
    public Product findProductByProductId(Integer productId);
    public Product updateProduct(String productName, String brand, String ram, String storage,
                          String operativeSystem, String processor, String bodySize, String screenSize,
                          String screenRatio, String rearCamera, String frontCamera);
    public List<Product> findAllProducts();
    public List<Product> filterProducts(Integer filterCount, final String filters[]);
}
