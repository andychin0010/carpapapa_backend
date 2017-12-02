package com.carpapapa.domain;

import java.util.List;

/**
 * Created by chishingchin on 3/2/16.
 */
public class Product {

    private long id;
    private boolean state;
    private String make;
    private String model;
    private String vin;
    private String exColor;
    private String inColor;
    private int year;
    private int mileage;
    private int price;
    private String engine;
    private int horsePower;
    private int torque;
    private String drivetrain;
    private int mpgCity;
    private int mpgHighway;
    private ProductStatus status;
    private String description;
    private List<Image> images;
    private List<ProductOption> options;
    private long soldDate;
    private long creationTimestamp;
    private long modificationTimestamp;

    public Product() {
    }

    public Product(long id,
                   boolean state,
                   String make,
                   String model,
                   String vin,
                   String exColor,
                   String inColor,
                   int year,
                   int mileage,
                   int price,
                   ProductStatus status,
                   String description,
                   List<Image> images,
                   long soldDate,
                   long creationTimestamp,
                   long modificationTimestamp) {
        this.id = id;
        this.state = state;
        this.make = make;
        this.model = model;
        this.vin = vin;
        this.exColor = exColor;
        this.inColor = inColor;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
        this.status = status;
        this.description = description;
        this.images = images;
        this.soldDate = soldDate;
        this.creationTimestamp = creationTimestamp;
        this.modificationTimestamp = modificationTimestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getExColor() {
        return exColor;
    }

    public void setExColor(String exColor) {
        this.exColor = exColor;
    }

    public String getInColor() {
        return inColor;
    }

    public void setInColor(String inColor) {
        this.inColor = inColor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    public String getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(String drivetrain) {
        this.drivetrain = drivetrain;
    }

    public int getMpgCity() {
        return mpgCity;
    }

    public void setMpgCity(int mpgCity) {
        this.mpgCity = mpgCity;
    }

    public int getMpgHighway() {
        return mpgHighway;
    }

    public void setMpgHighway(int mpgHighway) {
        this.mpgHighway = mpgHighway;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<ProductOption> getOptions() {
        return options;
    }

    public void setOptions(List<ProductOption> options) {
        this.options = options;
    }

    public long getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(long soldDate) {
        this.soldDate = soldDate;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public long getModificationTimestamp() {
        return modificationTimestamp;
    }

    public void setModificationTimestamp(long modificationTimestamp) {
        this.modificationTimestamp = modificationTimestamp;
    }
}
