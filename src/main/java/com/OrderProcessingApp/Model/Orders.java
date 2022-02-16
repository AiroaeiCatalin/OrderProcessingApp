package com.OrderProcessingApp.Model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name="orders")
public class Orders {

    private List<Order> orders;

    public Orders() {
    }

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    @XmlElement(name = "order")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
