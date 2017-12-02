package com.carpapapa.domain;

/**
 * Created by chandler on 5/12/17.
 */
public class ProductOption {

    private long id;
    private long productId;
    private String type;
    private String option;
    private String description;
    private long creationTimestamp;
    private long modificationTimestamp;

    public ProductOption() {
    }

    public ProductOption(long id, long productId, String type, String option, String description, long creationTimestamp, long modificationTimestamp) {
        this.id = id;
        this.productId = productId;
        this.type = type;
        this.option = option;
        this.description = description;
        this.creationTimestamp = creationTimestamp;
        this.modificationTimestamp = modificationTimestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
