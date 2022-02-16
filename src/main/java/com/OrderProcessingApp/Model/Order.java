package com.OrderProcessingApp.Model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

import javax.xml.datatype.XMLGregorianCalendar;

import java.util.List;

public class Order implements Comparable<Order>{
    private Long id;
    private XMLGregorianCalendar created;
    private List<Product> products;

    public Order() {
    }

    public Order(Long id, XMLGregorianCalendar created, List<Product> products) {
        this.id = id;
        this.created = created;
        this.products = products;
    }

    @XmlAttribute(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlAttribute
    public XMLGregorianCalendar getCreated() {
        return created;
    }

    public void setCreated(XMLGregorianCalendar created) {
        this.created = created;
    }

    @XmlElement(name = "product")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public int compareTo(Order o){
        return this.created.toGregorianCalendar().compareTo(o.created.toGregorianCalendar());
    }

}
