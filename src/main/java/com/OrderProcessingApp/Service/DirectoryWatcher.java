package com.OrderProcessingApp.Service;

import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectoryWatcher {

    private static final String DIRECTORY_PATH = "../SacomOrdersProcessing/src/main/resources/input/";

    public static void watch() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(DIRECTORY_PATH);

            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE
            );

            WatchKey key;

            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {


                    Path eventPath = (Path) event.context();
                    Pattern pattern = Pattern.compile("\\Aorders\\d\\d.xml\\z");
                    System.out.println(eventPath);
                    Matcher matcher = pattern.matcher(eventPath.toString());
                    boolean matchFound = matcher.find();
                    if(matchFound) {
                        System.out.println(
                                "Event kind:" + event.kind()
                                        + ". File affected: " + eventPath);
                        Path filePath = path.resolve(eventPath);
                        ReadXmlFile.read(String.valueOf(filePath));
                    } else {
                        System.out.println("File name does not match requested pattern");
                    }
                }
                key.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
