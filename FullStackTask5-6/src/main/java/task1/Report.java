package task1;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Creating final Report
 */
public class Report {
    private final String pathToSaveReport;
    private final String pathToRecords;

    private volatile Map<String, Double> map = new ConcurrentHashMap<>();
    private final int threads;

    public Report(String pathToSaveReport, String pathToRecords, int threads) {
        this.pathToSaveReport = pathToSaveReport;
        this.pathToRecords = pathToRecords;
        this.threads = threads;
    }


    public void createReport() {
        ExecutorService ex = Executors.newFixedThreadPool(threads);
        Lock lock = new ReentrantLock();

        File directory = new File(pathToRecords);
        //Collecting XML files from directory
        File[] listOfFiles = directory.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile() && FileType.getFileExtension(file).equals("xml")) {
                StaxParser staxParser = new StaxParser();
                CompletableFuture.supplyAsync(() -> file, ex).thenAccept(e -> {
                    try {
                        Map<String, Double> periodMap = new HashMap<>(staxParser.parse(file.getAbsolutePath()));
                        lock.lock();
                            map=connect(map, periodMap);
                            lock.unlock();
                    } catch (FileNotFoundException | XMLStreamException exc) {
                        throw new RuntimeException(exc);
                    }
                });
            }
        }
       shutdownAndAwaitTermination(ex);

        //Sorting map by amount
        Map<String, Double> stringDoubleMap = new LinkedHashMap<>();

        List<Map.Entry<String, Double>> listFinal = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList();

        //Saving 'listFinal' to LinkedHashMap to work with JsonWriter

        for (Map.Entry<String, Double> stringDoubleEntry : listFinal) {
            stringDoubleMap.put(stringDoubleEntry.getKey(), stringDoubleEntry.getValue());
        }
        //Calling JsonWriter to save LinkedHashMap to JSON file
        JsonWriter jsonWriter = new JsonWriter(pathToSaveReport);
        jsonWriter.write(stringDoubleMap);
    }

    void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

//Putting receiving Map from Thread to general Map for collecting statistic
    public static Map<String, Double> connect(Map<String, Double> map, Map<String, Double> periodMap){
        Set<String> treeSet=  periodMap.keySet();
        for (Object x: treeSet) {

            if(map.containsKey(x.toString())){
                map.get(x.toString());
                periodMap.get(x.toString());
                map.replace(x.toString(),map.get(x.toString()),map.get(x.toString())+periodMap.get(x.toString()));
            }else{
                map.put(x.toString(),periodMap.get(x.toString()));
            }
        }
        return map;
    }
}

