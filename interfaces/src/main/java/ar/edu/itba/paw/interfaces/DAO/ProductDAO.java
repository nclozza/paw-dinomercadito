package ar.edu.itba.paw.interfaces.DAO;

import ar.edu.itba.paw.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {

    /**
     * Creates a product POJO and stores it in the database. The product consists of a name, a brand, its RAM storage,
     * its general or ROM storage, its operative system, its processor chipset, its body size (measured in inches
     * diagonally), its screen size (measured in inches diagonally), its screen ratio, its rear camera specifications
     * and its front camera specifications.
     * @param productName The product's model name as a String.
     * @param brand The product's brand as a String.
     * @param ram The product's RAM storage as as String.
     * @param storage The product's ROM or general storage as a String.
     * @param operativeSystem The product's operative system as a String.
     * @param processor The product's processor chipset as a String.
     * @param bodySize The product's body size measured in inches diagonally as a String.
     * @param screenSize The product's screen size measured in inches diagonally as a String.
     * @param screenRatio The product's screen ratio as a String.
     * @param rearCamera The product's rear camera specifications as a String.
     * @param frontCamera The product's frontal camera specifications as a String.
     * @return A new POJO of a product. This method should never return null.
     *
     *
     * NOTE: This method should only be used by an admin of the site or someone with similar authority.
     */
    Product createProduct(final String productName, final String brand, final String ram, final String storage,
                          final String operativeSystem, final String processor, final String bodySize,
                          final String screenSize, final String screenRatio, final String rearCamera,
                          final String frontCamera);

    /**
     * Deletes the product through its specified ID, from the database.
     * @param productId The product's unique ID as an Integer to find and delete said product.
     * @return True if the product was successfully deleted from the database, false otherwise.
     */
    boolean deleteProduct(final Integer productId);

    /**
     * Finds and retrieves a product from the database, specified through its ID.
     * @param productId The product's ID as an Integer to find and retrieve said product.
     * @return An Optional object that contains either the product POJO if the product was successfully found and
     *      retrieved from the database, or an empty Optional otherwise.
     *
     *
     * NOTE: This method should only be used by an admin of the site or someone with similar authority.
     */
    Optional<Product> findProductByProductId(final Integer productId);

    /**
     * Updates a product's information in the database. All of its parameters can be updated except for its ID, which is
     * unique.
     * @param productId The product's ID as an Integer to find said product and update its information.
     * @param productName The supposedly product's new name as a String.
     * @param brand The supposedly product's new brand as a String.
     * @param ram The supposedly product's new RAM storage as a String.
     * @param storage The supposedly product's new ROM storage as a String.
     * @param operativeSystem The supposedly product's new operative system as a String.
     * @param processor The supposedly product's new processor chipset as a String.
     * @param bodySize The supposedly product's new body size as a String.
     * @param screenSize The supposedly product's new screen size as a String.
     * @param screenRatio The supposedly product's new screen ratio as a String.
     * @param rearCamera The supposedly product's new rear camera specifications as a String.
     * @param frontCamera The supposedly product's new frontal camera specifications as a String.
     * @return An Optional object that contains either the updated product POJO if the product was successfully found
     *      and updated from the database, or an empty Optional otherwise.
     *
     *
     * NOTE: This method should only be used by an admin of the site or someone with similar authority.
     */
    Optional<Product> updateProduct(final Integer productId, final String productName, final String brand, final String ram,
                          final String storage, final String operativeSystem, final String processor,
                          final String bodySize, final String screenSize, final String screenRatio,
                          final String rearCamera, final String frontCamera);

    /**
     * Finds and retrieves all products stored in the database.
     * @return A List with all the products stored in the database. The List will be empty if there are no products.
     */
    List<Product> findAllProducts();



    /**
     * ====================================================
     * The following section still needs to be implemented
     * ====================================================
     */

    List<Product> filterProducts(final Integer filterCount, final String attribute[], final String attributeValue[]);

    List<String> findAllAttributeValuesForFilter(final String attribute);

    List<Product> findProductsByFilter(final String filter);
}