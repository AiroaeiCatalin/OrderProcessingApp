package com.OrderProcessingApp.Service.Parsers.Implementations;

import com.OrderProcessingApp.Model.Product;
import com.OrderProcessingApp.Model.Products;
import com.OrderProcessingApp.Service.Parsers.WriteXmlFile;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteXmlFileImpl implements WriteXmlFile {

    public void write(HashMap<String, List<Product>> productsBySupplier, int orderNum)  {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
            //Creating marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

//            jaxbMarshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
//            jaxbMarshaller.setProperty("xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");



            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Products products = new Products();

            for (Map.Entry<String, List<Product>> entry : productsBySupplier.entrySet()) {
                //Setting root elemennt
                products.setProducts(entry.getValue());

                String supplier = entry.getKey();
                String filePath = String.format("../SacomOrdersProcessing/src/main/resources/output/%s%d.xml", supplier, orderNum);
                //Marshal the employees list in file
                jaxbMarshaller.marshal(products, new File(filePath));
                System.out.println("New file created: " + filePath);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
