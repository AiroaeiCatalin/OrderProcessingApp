package com.OrderProcessingApp.Service.Parsers;

import com.OrderProcessingApp.Model.Product;

import java.util.HashMap;
import java.util.List;

public interface WriteXmlFile {
    public void write(HashMap<String, List<Product>> productsBySupplier, int orderNum);
}
