package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.ProductDAO;
import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String attributesToFilter[] = {"brand", "operativeSystem", "ram", "storage"};

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

    public Product updateProduct(String productName, String brand, String ram, String storage, String operativeSystem, String processor, String bodySize, String screenSize, String screenRatio, String rearCamera, String frontCamera) {
        return null;
    }

    public List<Product> findAllProducts() {
        return productDAO.findAllProducts();
    }

    public List<Product> filterProducts(final Integer filterCount, final String attributes[],
                                        final String attributeValue[]) {
        if ((filterCount != attributes.length) || (filterCount != attributeValue.length))
            return null;

        return productDAO.filterProducts(filterCount, attributes, attributeValue);
    }

    public List<String> findAllAttributeValuesForFilter(final String attribute) {
        return productDAO.findAllAttributeValuesForFilter(attribute);
    }

    public List<String> getAllAttributesForFiltering() {
        return Arrays.asList(attributesToFilter);
    }
}
