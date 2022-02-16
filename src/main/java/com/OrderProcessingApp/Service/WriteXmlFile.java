package com.OrderProcessingApp.Service;

import com.OrderProcessingApp.Model.Product;
import com.OrderProcessingApp.Model.ProductsDTO;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteXmlFile {

    public static void write(HashMap<String, List<Product>> productsBySupplier, int orderNum) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ProductsDTO.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // pt rmv standalone


        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        ProductsDTO products = new ProductsDTO();

        for (Map.Entry<String, List<Product>> entry : productsBySupplier.entrySet()) {
//            List<Product> productList = entry.getValue();
            products.setProducts(entry.getValue());
            String supplier = entry.getKey();
            String filePath = String.format("../SacomOrdersProcessing/src/main/resources/output/%s%d.xml", supplier, orderNum);
            //Marshal the employees list in file
            jaxbMarshaller.marshal(products, new File(filePath));
        }

    }
}
