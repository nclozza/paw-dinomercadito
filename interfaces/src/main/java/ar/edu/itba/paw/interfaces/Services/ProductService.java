package ar.edu.itba.paw.interfaces.Services;

import ar.edu.itba.paw.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(final String productName, final String brand, final String ram, final String storage,
                          final String operativeSystem, final String processor, final String bodySize,
                          final String screenSize, final String screenRatio, final String rearCamera,
                          final String frontCamera);

    public boolean deleteProduct(Integer productId);

    Optional<Product> findProductByProductId(Integer productId);

    Optional<Product> updateProduct(final Integer productId, final String productName, final String brand, final String ram,
                          final String storage, final String operativeSystem, final String processor,
                          final String bodySize, final String screenSize, final String screenRatio,
                          final String rearCamera, final String frontCamera);

    public List<Product> findAllProducts();

    /**
     * Provides the caller with a list of products whose attributes indicated in the attributes array match the values
     * specified in the attributeValue array. Both array sizes should be equal since only one value per attribute filter
     * is allowed, meaning that the caller cannot ask for phones which match two or more brands.
     *
     * @param filterCount:    amount of attributes to filter.
     * @param attributes:     attributes to filter. Their names passed as Strings should be consistent with the names of the
     *                        'products' table's columns.
     * @param attributeValue: attribute values to filter. Only one value per attribute is allowed.
     * @return a list of products whose attributes match the ones specified. It is the caller's responsibility to call
     * this method with the proper parameters in the proper amount and order. Should there be any mistake, this
     * method will return null.
     */
    public List<Product> filterProducts(final Integer filterCount, final String attributes[],
                                 final String attributeValue[]);

    /**
     * Finds and returns all the distinct values for a certain product attribute (e.g. brand) as a list of Strings.
     *
     * @param attribute: the product's attribute to filter
     * @return a list of Strings which represent all the available values for the called attribute.
     */
    public List<String> findAllAttributeValuesForFilter(final String attribute);

    public List<String> getAllAttributesForFiltering();

    public List<Product> findProductsByFilter(final String filter);
}
