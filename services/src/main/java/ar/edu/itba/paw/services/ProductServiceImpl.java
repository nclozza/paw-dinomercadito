package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.DAO.ProductDAO;
import ar.edu.itba.paw.interfaces.Services.ProductService;
import ar.edu.itba.paw.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    private static final String attributesToFilter[] = {"brand", "operativeSystem", "ram", "storage"};

    @Autowired
    private ProductDAO productDAO;

    public Product createProduct(String productName, String brand, String ram, String storage, String operativeSystem,
                                 String processor, String bodySize, String screenSize, String screenRatio,
                                 String rearCamera, String frontCamera) {
        return productDAO.createProduct(productName,brand, ram, storage, operativeSystem, processor, bodySize,
                screenSize, screenRatio, rearCamera, frontCamera);
    }

    public boolean deleteProduct(Integer productId) {
        return productDAO.deleteProduct(productId);
    }

    @Transactional (readOnly = true)
    public Product findProductByProductId(Integer productId) {
        return productDAO.findProductByProductId(productId);
    }

    public Product updateProduct(Integer productId, String productName, String brand, String ram, String storage,
                                 String operativeSystem, String processor, String bodySize, String screenSize,
                                 String screenRatio, String rearCamera, String frontCamera) {
        return productDAO.updateProduct(productId, productName, brand, ram, storage, operativeSystem, processor,
                bodySize, screenSize, screenRatio, rearCamera, frontCamera);
    }

    @Transactional (readOnly = true)
    public List<Product> findAllProducts() {
        return productDAO.findAllProducts();
    }

    @Transactional (readOnly = true)
    public List<Product> filterProducts(final Integer filterCount, final String attributes[],
                                        final String attributeValue[]) {
        if ((filterCount != attributes.length) || (filterCount != attributeValue.length))
            return null;

        return productDAO.filterProducts(filterCount, attributes, attributeValue);
    }

    @Transactional (readOnly = true)
    public List<String> findAllAttributeValuesForFilter(final String attribute) {
        return productDAO.findAllAttributeValuesForFilter(attribute);
    }

    @Transactional (readOnly = true)
    public List<String> getAllAttributesForFiltering() {
        return Arrays.asList(attributesToFilter);
    }

    @Transactional (readOnly = true)
    public List<Product> findProductsByFilter(String filter) {
        return productDAO.findProductsByFilter(filter);
    }
}
