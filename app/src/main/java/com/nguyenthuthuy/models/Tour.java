package com.nguyenthuthuy.models;

public class Tour {
    private int id;
    private String code;
    private String name;
    private String des;
    private Double count;
    private String scheldule;
    private Double price;
    private byte[] image;

    public Tour(int id, String code, String name, String des, Double count, String scheldule, Double price, byte[] image) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.des = des;
        this.count = count;
        this.scheldule = scheldule;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getScheldule() {
        return scheldule;
    }

    public void setScheldule(String scheldule) {
        this.scheldule = scheldule;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
