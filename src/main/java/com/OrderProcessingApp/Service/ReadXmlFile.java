package com.OrderProcessingApp.Service;

import com.OrderProcessingApp.Model.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.*;

public class ReadXmlFile {

    public static void read(String filePath){
        try {

            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(OrdersDTO.class);



            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            OrdersDTO ordersDTO = (OrdersDTO) jaxbUnmarshaller.unmarshal(file);
            HashMap<String, List<Product>> productsBySupplier = new HashMap<>();

            int orderNum = Integer.parseInt(filePath.replaceAll("[^\\d]", ""));
            System.out.println(orderNum);


            List<Order> ordersList = ordersDTO.getOrders();
            Collections.sort(ordersList, Collections.reverseOrder());
            for(Order o: ordersList){
                System.out.println(o.getId() + " " + o.getCreated());
                List<Product> products = o.getProducts();
                for(Product p : products){
                    if (productsBySupplier.containsKey(p.getSupplier())) {
                        productsBySupplier.get(p.getSupplier()).add(p);
                    } else {
                        ArrayList<Product> newProductList = new ArrayList<Product>();
                        newProductList.add(p);
                        productsBySupplier.put(p.getSupplier(), newProductList);
                    }
                    p.setOrderId(o.getId());
                    p.setSupplier(null);
                }
            }
            WriteXmlFile.write(productsBySupplier, orderNum);


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
