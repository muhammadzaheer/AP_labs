/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author smzaheerabbas
 */
public class Spider {

    private static File root;

    private HashMap<String, ArrayList<String>> index;
    private Queue<File> filesToIndex;

    private static AtomicInteger at = new AtomicInteger(0);
    private Thread[] crawlers;
    private Thread[] indexers;

    public Spider() {
        index = new HashMap<String, ArrayList<String>>();
        crawlers = new Thread[3];
        indexers = new Thread[3];
        filesToIndex = new LinkedList<File>();
    }

    int isCrawlerThreadAvailable() {
        return at.getAndIncrement();
    }

    public void index(File rootDirectory) {

        startIndexingThreads();

        this.root = rootDirectory;
        this.recursiveRead(root);
        try {
            for (int i = 0; i < 3; i++) {
                if (crawlers[i] != null) {
                    crawlers[i].join();

                }
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        /* 
            Crawler threads have return, let's wait for the Queue to be empty before asking
            Indexer threads to call it quits
         */
        while (!filesToIndex.isEmpty()) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }

        try {
            for (int i = 0; i < 3; i++) {
                if (indexers[i] != null) {
                    indexers[i].interrupt();
                    indexers[i].join();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        /*
        for (String key : index.keySet()) {
            System.out.print(key);
            System.out.print(" " + index.get(key));
            System.out.println();
        
        
        }*/
        //System.out.println(index);
    }

    private void startIndexingThreads() {

        for (int i = 0; i < 3; i++) {
            Runnable indexer = new IndexerThread(index, "Indexer-" + Integer.toString(i), filesToIndex);
            indexers[i] = new Thread(indexer, "Indexer-" + Integer.toString(i));
            indexers[i].setDaemon(true);
            indexers[i].start();
        }

    }

    private void recursiveRead(File root) {
        // file is a directory

        for (File file : root.listFiles()) {
            // If file is a directory
            if (file.isDirectory()) {
                int i = isCrawlerThreadAvailable();
                if (i < 3) {
                    Runnable crawler = new CrawlerThread(file, index, "Crawler-" + Integer.toString(i), filesToIndex);

                    crawlers[i] = new Thread(crawler, "Crawler-" + Integer.toString(i));
                    crawlers[i].setDaemon(true);
                    crawlers[i].start();

                } else {
                    System.out.println("Spider is also fucking up");
                    indexDirectory(file.getAbsolutePath());
                    recursiveRead(file);
                }
            } // If file is a .txt file
            else if (file.getName().toLowerCase().endsWith(".txt")) {
                indexDirectory(file.getAbsolutePath());
                indexFile(file);
            } else {
                // Ignoring if extension is not .txt
            }
        }

    }

    private void indexDirectory(String directoryPath) {
        synchronized (index) {
            String[] tokens = directoryPath.split("/");

            for (String token : tokens) {
                if (index.containsKey(token)) {
                    index.get(token).add(directoryPath);
                } else {
                    ArrayList<String> list = new ArrayList<String>();
                    list.add(directoryPath);
                    index.put(token, list);
                }

            }
        }
    }

    private void indexFile(File textFile) {
        synchronized (index) {
            try {
                Scanner in = new Scanner(textFile);

                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    String[] tokens = line.split(" ");

                    for (String token : tokens) {
                        if (index.containsKey(token)) {
                            index.get(token).add(textFile.getAbsolutePath());
                        } else {
                            ArrayList<String> list = new ArrayList<String>();
                            list.add(textFile.getAbsolutePath());
                            index.put(token, list);
                        }

                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void search(String keyword) {

        if (index.containsKey(keyword)) {
            ArrayList<String> list = index.get(keyword);
            Set<String> unique = new HashSet<String>(list);
            for (String result : unique) {
                System.out.println(result);
            }
        } else {
            System.out.println("Keyword not found");
        }
    }

    public void persistIndex(String name) {
        try {
            FileOutputStream fileOut
                    = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(index);
            out.close();
            fileOut.close();
            System.out.println("Index is saved in " + name);
        } catch (IOException i) {
            i.printStackTrace();
        }

    }

}
