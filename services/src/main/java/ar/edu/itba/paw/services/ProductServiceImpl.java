package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.ProductDAO;
import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String attributesToFilter[] = {"brand", "operativeSystem", "ram", "storage"};

    @Autowired
    private ProductDAO productDAO;

    @Override
    public Product createProduct(final String productName, final String brand, final String ram, final String storage,
                                 final String operativeSystem, final String processor, final String bodySize,
                                 final String screenSize, final String screenRatio, final String rearCamera,
                                 final String frontCamera) {
        return productDAO.createProduct(productName, brand, ram, storage, operativeSystem, processor, bodySize,
                screenSize, screenRatio, rearCamera, frontCamera);
    }

    @Override
    public boolean deleteProduct(final Integer productId) {
        return false;
    }

    @Override
    public Optional<Product> findProductByProductId(final Integer productId) {
        return productDAO.findProductByProductId(productId);
    }

    @Override
    public Optional<Product> updateProduct(final Integer productId, final String productName, final String brand, final String ram,
                                 final String storage, final String operativeSystem, final String processor,
                                 final String bodySize, final String screenSize, final String screenRatio,
                                 final String rearCamera, final String frontCamera) {
        return productDAO.updateProduct(productId, productName, brand, ram, storage, operativeSystem, processor,
                bodySize, screenSize, screenRatio, rearCamera, frontCamera);
    }

    @Override
    public List<Product> findAllProducts() {
        return productDAO.findAllProducts();
    }

    @Override
    public List<Product> filterProducts(final Integer filterCount, final String attributes[],
                                        final String attributeValue[]) {
        if ((filterCount != attributes.length) || (filterCount != attributeValue.length))
            return null;

        return productDAO.filterProducts(filterCount, attributes, attributeValue);
    }

    @Override
    public List<String> findAllAttributeValuesForFilter(final String attribute) {
        return productDAO.findAllAttributeValuesForFilter(attribute);
    }

    @Override
    public List<String> getAllAttributesForFiltering() {
        return Arrays.asList(attributesToFilter);
    }

    @Override
    public List<Product> findProductsByFilter(String filter) {
        return productDAO.findProductsByFilter(filter);
    }
}
