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
    private Rectangle bodySize;
    private Rectangle screenSize;
    private String screenRatio;
    private String rearCamera;
    private String frontCamera;

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

    public Rectangle getBodySize() {
        return bodySize;
    }

    public void setBodySize(Rectangle bodySize) {
        this.bodySize = bodySize;
    }

    public Rectangle getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Rectangle screenSize) {
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
