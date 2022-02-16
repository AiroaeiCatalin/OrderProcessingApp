package com.OrderProcessingApp.Service;

import com.OrderProcessingApp.Service.Parsers.ReadXmlFile;

import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectoryWatcher {

    private String directoryPath;
    private ReadXmlFile readXmlFile;
    private String patternForm;



    public DirectoryWatcher(String directoryPath, ReadXmlFile readXmlFile, String patternForm) {
        this.directoryPath = directoryPath;
        this.readXmlFile = readXmlFile;
        this.patternForm= patternForm;
    }

    public void watch() {
        try {
            //Creating the WatchService
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(this.directoryPath);
            //Telling which events are we going to track, in our case the create one
            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE
            );

            WatchKey key;

            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    //Getting the file path from the context
                    Path eventPath = (Path) event.context();
                    //Creating the entire file path
                    Path filePath = path.resolve(eventPath);
                    //Check if file has right pattern
                    boolean correctPattern = checkPattern(eventPath);
                    if(correctPattern){
                        readXmlFile.read(filePath.toString());
                    }
                }
                key.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkPattern(Path eventPath) {
        Pattern pattern = Pattern.compile(this.patternForm);
        Matcher matcher = pattern.matcher(eventPath.toString());
        boolean matchFound = matcher.find();
        if(matchFound) {
            System.out.println(
                    "New file detected: " + eventPath);
            return true;
        } else {
            System.out.println("File name does not match requested pattern: " + eventPath);
            return false;
        }
    }

}
