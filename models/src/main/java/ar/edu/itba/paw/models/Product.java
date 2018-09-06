package ar.edu.itba.paw.models;

import java.awt.*;

public class Product {

    private Integer productId;
    private String productName;
    private String brand;
    private String ram;
    private String storage;
    private String operativeSystem;
    private String processor;
    private String bodySize; // inches diagonally
    private String screenSize; // inches diagonally
    private String screenRatio;
    private String rearCamera;
    private String frontCamera;

    public Product(Integer productId, String productName, String brand, String ram, String storage,
                   String operativeSystem, String processor, String bodySize, String screenSize,
                   String screenRatio, String rearCamera, String frontCamera) {
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getOperativeSystem() {
        return operativeSystem;
    }

    public void setOperativeSystem(String operativeSystem) {
        this.operativeSystem = operativeSystem;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getBodySize() {
        return bodySize;
    }

    public void setBodySize(String bodySize) {
        this.bodySize = bodySize;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getScreenRatio() {
        return screenRatio;
    }

    public void setScreenRatio(String screenRatio) {
        this.screenRatio = screenRatio;
    }

    public String getRearCamera() {
        return rearCamera;
    }

    public void setRearCamera(String rearCamera) {
        this.rearCamera = rearCamera;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public void setFrontCamera(String frontCamera) {
        this.frontCamera = frontCamera;
    }
}
