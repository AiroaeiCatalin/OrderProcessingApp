package com.OrderProcessingApp.Model;

import jakarta.xml.bind.annotation.*;

@XmlType(propOrder={"description", "gtin", "price", "orderId", "supplier"})
public class Product {

    private String description;
    private String gtin;
    private Price price;
    private Long orderId;
    private String supplier;

    public Product() {
    }

    public Product(String description, String gtin, Price price, Long orderId, String supplier) {
        this.description = description;
        this.gtin = gtin;
        this.price = price;
        this.orderId = orderId;
        this.supplier = supplier;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    @XmlElement
    public String getGtin() {
        return gtin;
    }

    @XmlElement
    public Price getPrice() {
        return price;
    }

    @XmlElement(name="orderid")
    public Long getOrderId() {
        return orderId;
    }

    @XmlElement
    public String getSupplier() {
        return supplier;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }


}
