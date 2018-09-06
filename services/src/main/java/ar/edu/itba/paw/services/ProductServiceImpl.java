package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.ProductDAO;
import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Product createProduct(String productName, String brand, String ram, String storage, String operativeSystem,
                                 String processor, String bodySize, String screenSize, String screenRatio,
                                 String rearCamera, String frontCamera) {
        return productDAO.createProduct(productName,brand, ram, storage, operativeSystem, processor, bodySize,
                screenSize, screenRatio, rearCamera, frontCamera);
    }

    @Override
    public boolean deleteProduct(Integer productId) {
        return false;
    }

    @Override
    public Product findProductByProductId(Integer productId) {
        return productDAO.findProductByProductId(productId);
    }

    @Override
    public Product updateProduct(String productName, String brand, String ram, String storage, String operativeSystem, String processor, Rectangle bodySize, Rectangle screenSize, String screenRatio, String rearCamera, String frontCamera) {
        return null;
    }
}
