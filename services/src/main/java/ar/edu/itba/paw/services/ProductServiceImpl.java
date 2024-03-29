package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.ProductDAO;
import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private static final String attributesToFilter[] = {"brand", "operativeSystem", "ram", "storage"};

    @Autowired
    private ProductDAO productDAO;


    @Override
    public Product createProduct(String productName, String brand, String ram, String storage, String operativeSystem,
                                 String processor, String bodySize, String screenSize, String screenRatio,
                                 String rearCamera, String frontCamera) {
        return productDAO.createProduct(productName, brand, ram, storage, operativeSystem, processor, bodySize,
                screenSize, screenRatio, rearCamera, frontCamera);
    }

    @Override
    public boolean deleteProduct(Integer productId) {
        return productDAO.deleteProduct(productId);
    }

    @Transactional (readOnly = true)
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

    @Transactional (readOnly = true)
    @Override
    public List<Product> findAllProducts() {
        return productDAO.findAllProducts();
    }

    @Transactional (readOnly = true)
    @Override
    public List<Product> filterProducts(final Integer filterCount, final String attributes[],
                                        final String attributeValue[]) {
        if ((filterCount != attributes.length) || (filterCount != attributeValue.length))
            return null;

        return productDAO.filterProducts(filterCount, attributes, attributeValue);
    }

    @Transactional (readOnly = true)
    @Override
    public List<String> findAllAttributeValuesForFilter(final String attribute) {
        return productDAO.findAllAttributeValuesForFilter(attribute);
    }

    @Transactional (readOnly = true)
    @Override
    public List<String> getAllAttributesForFiltering() {
        return Arrays.asList(attributesToFilter);
    }

    @Transactional (readOnly = true)
    @Override
    public List<Product> findProductsByFilter(String filter) {
        return productDAO.findProductsByFilter(filter);
    }

}
