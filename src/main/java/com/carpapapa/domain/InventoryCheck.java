package com.carpapapa.domain;

/**
 * Created by chandler on 4/14/18.
 */
public class InventoryCheck {
    private int id;
    private String vin;
    private boolean isDeleted;
    private long lastCheckTimestamp;
    private long modificationTimestamp;

    public InventoryCheck() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public long getLastCheckTimestamp() {
        return lastCheckTimestamp;
    }

    public void setLastCheckTimestamp(long lastCheckTimestamp) {
        this.lastCheckTimestamp = lastCheckTimestamp;
    }

    public long getModificationTimestamp() {
        return modificationTimestamp;
    }

    public void setModificationTimestamp(long modificationTimestamp) {
        this.modificationTimestamp = modificationTimestamp;
    }
}
