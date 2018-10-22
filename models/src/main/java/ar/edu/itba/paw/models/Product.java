package ar.edu.itba.paw.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_productId_seq")
    @SequenceGenerator(sequenceName = "products_productId_seq", name = "products_productId_seq", allocationSize = 1)
    @Column(name = "productId")
    private Integer productId;

    @Column(length = 32, nullable = false, unique = true)
    private String productName;

    @Column(length = 32)
    private String brand;

    @Column(length = 16)
    private String ram;

    @Column(length = 16)
    private String storage;

    @Column(length = 32)
    private String operativeSystem;

    @Column(length = 32)
    private String processor;

    @Column(length = 32)
    private String bodySize; // inches diagonally

    @Column(length = 32)
    private String screenSize; // inches diagonally

    @Column(length = 32)
    private String screenRatio;

    @Column(length = 128)
    private String rearCamera;

    @Column(length = 128)
    private String frontCamera;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "product")
    private List<Post> postList;


    public Product(final Integer productId, final String productName, final String brand, final String ram,
                   final String storage, final String operativeSystem, final String processor, final String bodySize,
                   final String screenSize, final String screenRatio, final String rearCamera,
                   final String frontCamera) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.operativeSystem = operativeSystem;
        this.processor = processor;
        this.bodySize = bodySize;
        this.screenSize = screenSize;
        this.screenRatio = screenRatio;
        this.rearCamera = rearCamera;
        this.frontCamera = frontCamera;
    }

    public Product(final String productName, final String brand, final String ram,
                   final String storage, final String operativeSystem, final String processor, final String bodySize,
                   final String screenSize, final String screenRatio, final String rearCamera,
                   final String frontCamera) {
        this.productName = productName;
        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.operativeSystem = operativeSystem;
        this.processor = processor;
        this.bodySize = bodySize;
        this.screenSize = screenSize;
        this.screenRatio = screenRatio;
        this.rearCamera = rearCamera;
        this.frontCamera = frontCamera;
    }

    public Product(){
        //Just for Hibernate
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(final Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(final String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(final String storage) {
        this.storage = storage;
    }

    public String getOperativeSystem() {
        return operativeSystem;
    }

    public void setOperativeSystem(final String operativeSystem) {
        this.operativeSystem = operativeSystem;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(final String processor) {
        this.processor = processor;
    }

    public String getBodySize() {
        return bodySize;
    }

    public void setBodySize(final String bodySize) {
        this.bodySize = bodySize;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(final String screenSize) {
        this.screenSize = screenSize;
    }

    public String getScreenRatio() {
        return screenRatio;
    }

    public void setScreenRatio(final String screenRatio) {
        this.screenRatio = screenRatio;
    }

    public String getRearCamera() {
        return rearCamera;
    }

    public void setRearCamera(final String rearCamera) {
        this.rearCamera = rearCamera;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(final String frontCamera) {
        this.frontCamera = frontCamera;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
