package com.carpapapa.domain;

/**
 * Created by chandler on 6/25/17.
 */
public class Customer {

    private long id;
    private String name;
    private String phone;
    private String email;
    private String wechat;
    private String notes;
    private long creationTimestamp;
    private long modificationTimestamp;

    public Customer() {
    }

    public Customer(long id, String name, String phone, String email, String wechat, String notes) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.wechat = wechat;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
