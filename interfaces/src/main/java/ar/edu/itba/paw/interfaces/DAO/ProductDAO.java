package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

    Product createProduct(final String productName, final String brand, final String ram, final String storage,
                          final String operativeSystem, final String processor, final String bodySize,
                          final String screenSize, final String screenRatio, final String rearCamera,
                          final String frontCamera);

    boolean deleteProduct(final Integer productId);

    Optional<Product> findProductByProductId(final Integer productId);

    Optional<Product> updateProduct(final Integer productId, final String productName, final String brand, final String ram,
                          final String storage, final String operativeSystem, final String processor,
                          final String bodySize, final String screenSize, final String screenRatio,
                          final String rearCamera, final String frontCamera);

    List<Product> findAllProducts();

    List<Product> filterProducts(final Integer filterCount, final String attribute[],
                                 final String attributeValue[]);

    List<String> findAllAttributeValuesForFilter(final String attribute);

    List<Product> findProductsByFilter(final String filter);
}