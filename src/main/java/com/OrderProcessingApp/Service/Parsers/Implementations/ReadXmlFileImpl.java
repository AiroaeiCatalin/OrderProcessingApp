package com.OrderProcessingApp.Service.Parsers.Implementations;

import com.OrderProcessingApp.Model.*;
import com.OrderProcessingApp.Service.Parsers.ReadXmlFile;
import com.OrderProcessingApp.Service.Parsers.WriteXmlFile;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.*;

public class ReadXmlFileImpl implements ReadXmlFile {

    private WriteXmlFile writeXmlFile;

    public ReadXmlFileImpl(WriteXmlFile writeXmlFile) {
        this.writeXmlFile = writeXmlFile;
    }

    public void read(String filePath){
        try {

            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Orders.class);

            //Creating unmarshaller
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Orders orders = (Orders) jaxbUnmarshaller.unmarshal(file);
            HashMap<String, List<Product>> productsBySupplier = new HashMap<>();

            //Extracting the order number from the file
            int orderNum = Integer.parseInt(filePath.replaceAll("[^\\d]", ""));

            List<Order> ordersList = orders.getOrders();

            //Sort orders list descending so the latest products are always first
            ordersList.sort(Collections.reverseOrder());
            //creating hashMap with products lists
            productsBySupplier = createHashMap(ordersList);
            writeXmlFile.write(productsBySupplier, orderNum);


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, List<Product>> createHashMap(List<Order> ordersList) {
        HashMap<String, List<Product>> productsBySupplier = new HashMap<>();
        for(Order o: ordersList){
            List<Product> products = o.getProducts();
            for(Product p : products){
                //check if we already have the supplier as a key in the hashMap
                if (productsBySupplier.containsKey(p.getSupplier())) {
                    //if we do we just add the new product to the list
                    productsBySupplier.get(p.getSupplier()).add(p);
                } else {
                    //if we don't then we have to create a new list and put it to the hashmap
                    ArrayList<Product> newProductList = new ArrayList<Product>();
                    newProductList.add(p);
                    productsBySupplier.put(p.getSupplier(), newProductList);
                }
                //We set the order id to the product as it isn't mapped by JAXL
                p.setOrderId(o.getId());
                //We have to set supplier to null so it doesn't appear in the generated XML after marshalling
                //This isn't a problem as we have it stored in the Key of the HashMap and can recover it by using the setter
                p.setSupplier(null);
            }
        }
        return productsBySupplier;
    }
}
