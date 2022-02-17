package com.OrderProcessingApp;

import com.OrderProcessingApp.Service.DirectoryWatcher;
import com.OrderProcessingApp.Service.Parsers.Implementations.ReadXmlFileImpl;
import com.OrderProcessingApp.Service.Parsers.Implementations.WriteXmlFileImpl;
import com.OrderProcessingApp.Service.Parsers.ReadXmlFile;
import com.OrderProcessingApp.Service.Parsers.WriteXmlFile;

import java.io.IOException;


public class OrderProcessingApp {

    public static void main(String[] args) throws InterruptedException, IOException {
        String directoryPath = "../SacomOrdersProcessing/src/main/resources/input/";
        String outputDirPath = "../SacomOrdersProcessing/src/main/resources/output/";
        String patternForm = "\\Aorders\\d\\d.xml\\z";
        //Creating new writeXmlFile service
        WriteXmlFile writeXmlFile = new WriteXmlFileImpl(outputDirPath);
        //Creating new readXmlFile service and injecting the writer to it
        ReadXmlFile readXmlFile = new ReadXmlFileImpl(writeXmlFile);
        //Creating new directoryWatcher and injecting the reader to it
        DirectoryWatcher directoryWatcher = new DirectoryWatcher(directoryPath, readXmlFile, patternForm);
        directoryWatcher.watch();
    }

}



