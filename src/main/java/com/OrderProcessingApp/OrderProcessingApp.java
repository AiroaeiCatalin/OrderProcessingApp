package com.OrderProcessingApp;

import com.OrderProcessingApp.Service.DirectoryWatcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class OrderProcessingApp {



    public static void main(String[] args) throws InterruptedException, IOException {
//        String a = new String("order23.xml");
//        String b = new String("order");
//
//        String[] parts = a.split("[0-9]");
//        System.out.println(parts[0].equals(b));
//
//        String[] parts2 = a.split("order");
//        System.out.println(parts2[1]);
//
//
//        System.out.println(a.equals(b));

//        String s = new String("order25.xml");
//        Pattern pattern = Pattern.compile("\\Aorder\\d\\d.xml\\z");
//        Matcher matcher = pattern.matcher(s);
//        boolean matchFound = matcher.find();
//        if(matchFound) {
//            System.out.println("Match found");
//        } else {
//            System.out.println("Match not found");
//        }
//        int a = Integer.parseInt(s.replaceAll("[^\\d]", ""));
//        System.out.println(a);





        DirectoryWatcher.watch();

//        watch();
//        WatchService watchService
//                = FileSystems.getDefault().newWatchService();
//
//        Path path = Paths.get("../SacomOrdersProcessing/src/main/resources/");
//
//        path.register(
//                watchService,
//                StandardWatchEventKinds.ENTRY_CREATE
//);
//
//        WatchKey key;
//
//        while ((key = watchService.take()) != null) {
//            for (WatchEvent<?> event : key.pollEvents()) {
//                Path eventPath = (Path) event.context();
//                System.out.println(
//                        "Event kind:" + event.kind()
//                                + ". File affected: " + event.context() + "path: " + eventPath);
//                try {
//
//                    File file = new File("../SacomOrdersProcessing/src/main/resources/" + eventPath);
//                    JAXBContext jaxbContext = JAXBContext.newInstance(QuestionsDTO.class);
//
//                    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//                    QuestionsDTO questionDTO = (QuestionsDTO) jaxbUnmarshaller.unmarshal(file);
//
//
//                    List<Question> questionList = questionDTO.getQuestions();
//                    for(Question que: questionList) {
//                        System.out.println(que.getId() + " " + que.getQuestionname());
//                        System.out.println("Answers:");
//                        List<Answer> list = que.getAnswers();
//                        for (Answer ans : list)
//                            System.out.println(ans.getId() + " " + ans.getAnswername() + "  " + ans.getPostedby());
//                    }
//
//                } catch (JAXBException e) {
//                    e.printStackTrace();
//                }
//            }
//            key.reset();
//        }
    }


    public static void watch(){
        //Deque<Path> wPathsToFiles = new ArrayDeque<>();
        //We create a new watch service
        try (WatchService service = FileSystems.getDefault().newWatchService()){
            //each path of a directory will have its own watchkey
            Map<WatchKey, Path> keyMap = new HashMap<>();
            Path path = Paths.get("../SacomOrdersProcessing/src/main/resources/");
            //We watch for events of creation type
            keyMap.put(path.register(service,
                    StandardWatchEventKinds.ENTRY_CREATE
            ),path);
            WatchKey watchKey;

            do{
                //Return a queued key

                watchKey = service.take();
                System.out.println(watchKey);
                Path eventDir = keyMap.get(watchKey);

                //process the pending events for the specified key
                for (WatchEvent<?> event: watchKey.pollEvents()){
                    //Test if we are getting the events we signed up for
                    WatchEvent.Kind<?> kind = event.kind();
                    //Retrieve the path which is stored as the context of the event
                    Path eventPath = (Path) event.context();
                    System.out.println(eventDir + ": " + kind + ": " + eventPath);
                    System.out.println(path + ": " + kind + ": " + eventPath);
                    Path pathToFile = path.resolve(eventPath);
                    //Transform form path to file
                    File pathFile = pathToFile.toFile();
                    //String absolutePath = pathToFile.getAbsolutePath();
                    //wPathsToFiles.addLast(pathToFile);
                    System.out.println(pathToFile);
                    //Read our file
//                    ReadXMLFile.process(pathFile);
                }
                //Reset watchKey
            }while(watchKey.reset());

        }catch(Exception e){
            e.printStackTrace();
        }
        //return wPathsToFiles;
    }

}



