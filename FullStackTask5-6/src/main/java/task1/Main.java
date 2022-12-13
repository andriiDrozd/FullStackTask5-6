package task1;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main Class is testing time of working programme with different number of threads.
 */
public class Main {

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException, InterruptedException {

        String pathToRecords = "src/main/resources/task1";
        String pathToSaveReport = "src/main/resources/task1/report.json";
        File file = new File("src/main/resources/task1/report.json");

        // If test the programme using if:
        for (int i = 1; i <= 8; i++) {
            file.delete();
            long before1 = System.currentTimeMillis();
            Report report1 = new Report(pathToSaveReport, pathToRecords, i);
            report1.createReport();
            long after1 = System.currentTimeMillis();
            System.out.println("Number of threads: " + i + ", Time of work-" + (after1 - before1) + " milliseconds");
        }
        // Number of threads: 1, Time of work-1644 milliseconds
        // Number of threads: 1, Time of work-1641 milliseconds
        // Number of threads: 1, Time of work-1691 milliseconds

        // Number of threads: 2, Time of work-578 milliseconds
        // Number of threads: 2, Time of work-500 milliseconds
        // Number of threads: 2, Time of work-494 milliseconds

        // Number of threads: 4, Time of work-438 milliseconds
        // Number of threads: 4, Time of work-390 milliseconds
        // Number of threads: 4, Time of work-359 milliseconds

        // Number of threads: 8, Time of work-338 milliseconds
        // Number of threads: 8, Time of work-328 milliseconds
        // Number of threads: 8, Time of work-422 milliseconds


        //But if check programme manually changing number of threads and start one by one:
        file.delete();
        int numberOfThreads=2;
        long before2 = System.currentTimeMillis();
        Report report1 = new Report(pathToSaveReport, pathToRecords, numberOfThreads);
        report1.createReport();
        long after2 = System.currentTimeMillis();
        System.out.println("Number of threads: " + numberOfThreads + ", Time of work-" + (after2 - before2) + " milliseconds");

    //        Number of threads: 1, Time of work-2732 milliseconds
    //        Number of threads: 1, Time of work-2316 milliseconds
    //        Number of threads: 1, Time of work-2433 milliseconds

    //        Number of threads: 2, Time of work-1207 milliseconds
    //        Number of threads: 2, Time of work-1183 milliseconds
    //        Number of threads: 2, Time of work-1517 milliseconds

    //        Number of threads: 4, Time of work-1119 milliseconds
    //        Number of threads: 4, Time of work-1106 milliseconds
    //        Number of threads: 4, Time of work-1137 milliseconds

    //        Number of threads: 5, Time of work-1109 milliseconds
    //        Number of threads: 5, Time of work-1108 milliseconds
    //        Number of threads: 5, Time of work-1097 milliseconds

    //        Number of threads: 8, Time of work-1143 milliseconds
    //        Number of threads: 8, Time of work-1203 milliseconds
    //        Number of threads: 8, Time of work-1216 milliseconds

    }
}