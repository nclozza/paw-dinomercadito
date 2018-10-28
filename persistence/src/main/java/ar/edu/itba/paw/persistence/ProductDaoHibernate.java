package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.DAO.ProductDAO;
import ar.edu.itba.paw.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;



@Repository
public class ProductDaoHibernate implements ProductDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoHibernate.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Product createProduct(String productName, String brand, String ram, String storage, String operativeSystem, String processor, String bodySize, String screenSize, String screenRatio, String rearCamera, String frontCamera) {
        Product product = new Product(productName, brand, ram, storage, operativeSystem, processor,bodySize, screenSize, screenRatio, rearCamera, frontCamera);
        em.persist(product);
        LOGGER.info("Product inserted with productId = {}", product.getProductId().intValue());
        return product;
    }

    @Transactional
    @Override
    public boolean deleteProduct(Integer productId) {
        Product product = em.find(Product.class, productId);

        if (product != null) {
            em.remove(product);
            LOGGER.info("Product deleted with productId = {}", productId);

            return true;
        }
        LOGGER.info("Product not found with productId = {}", productId);

        return false;
    }

    @Override
    public Optional<Product> findProductByProductId(Integer productId) {
        return Optional.ofNullable(em.find(Product.class, productId));
    }

    @Transactional
    @Override
    public Optional<Product> updateProduct(Integer productId, String productName, String brand, String ram, String storage,
                                           String operativeSystem, String processor, String bodySize, String screenSize,
                                           String screenRatio, String rearCamera, String frontCamera) {
        Product product = em.find(Product.class, productId);

        if(product != null){
            product.setProductName(productName);
            product.setBrand(brand);
            product.setRam(ram);
            product.setStorage(storage);
            product.setOperativeSystem(operativeSystem);
            product.setProcessor(processor);
            product.setBodySize(bodySize);
            product.setScreenSize(screenSize);
            product.setScreenRatio(screenRatio);
            product.setRearCamera(rearCamera);
            product.setFrontCamera(frontCamera);
            em.merge(product);
            LOGGER.info("Product updated with productId = {}", productId);
        } else {
            LOGGER.info("Product not found with productId = {}", productId);
        }

        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findAllProducts() {
        final TypedQuery<Product> query = em.createQuery("from Product", Product.class);
        final List<Product> list = query.getResultList();

        return list;
    }

    @Override
    public List<Product> filterProducts(Integer filterCount, String[] attribute, String[] attributeValue) {

        //TODO

        return null;
    }


    @Override
    public List<String> findAllAttributeValuesForFilter(String attribute) {
        final Query query = em.createQuery("SELECT DISTINCT p FROM Product p");
        List<Product> productList = query.getResultList();
        List<String> resultList = new LinkedList<String>();

        switch (attribute.toUpperCase()){
            case "BRAND": for (Product p : productList) {
                                resultList.add(p.getBrand());
                            }
                            break;
            case "OPERATIVESYSTEM": for (Product p : productList) {
                                       resultList.add(p.getOperativeSystem());
                                    }
                                    break;

            case "RAM": for (Product p : productList) {
                            resultList.add(p.getRam());
                        }
                        break;

            case "STORAGE": for (Product p : productList) {
                                resultList.add(p.getStorage());
                            }
                            break;

            default: break;
        }

        return resultList;
    }


    @Override
    public List<Product> findProductsByFilter(String filter) {
        final String filterFormatted = "%" + filter.toLowerCase() + "%";

        final TypedQuery<Product> query = em.createQuery("SELECT DISTINCT p FROM Product p WHERE LOWER(p.productName) LIKE " +
                ":filter OR LOWER(p.brand) LIKE :filter OR LOWER(p.operativeSystem) LIKE :filter", Product.class);
        query.setParameter("filter", filterFormatted);
        final List<Product> list = query.getResultList();

        return list;
    }

}
