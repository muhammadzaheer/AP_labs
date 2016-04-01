/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author smzaheerabbas
 */
public class IndexerThread implements Runnable {

    private String name;
    private static File root;
    private HashMap<String, ArrayList<String>> shared_index;
    private Queue sharedFilesToIndex;

    public IndexerThread(HashMap<String, ArrayList<String>> shared_index, String name, Queue filesToIndex) {

        this.name = name;
        this.shared_index = shared_index;
        this.sharedFilesToIndex = filesToIndex;

    }

    public void run() {

        while (true && !Thread.currentThread().isInterrupted()) {
            try {
                indexFiles();
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                break;
            }

        }
    }

    private void indexFiles() throws InterruptedException {
        
        synchronized (sharedFilesToIndex) {
            
            while (sharedFilesToIndex.isEmpty()) {
                sharedFilesToIndex.wait();
            }
            
            File textFile = (File) sharedFilesToIndex.remove();
            try {
                Scanner in = new Scanner(textFile);

                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    String[] tokens = line.split(" ");
                    //System.out.println(name + "get shared_index");
                    synchronized (shared_index) {
                        for (String token : tokens) {
                            if (shared_index.containsKey(token)) {
                                shared_index.get(token).add(textFile.getAbsolutePath());
                            } else {
                                ArrayList<String> list = new ArrayList<String>();
                                list.add(textFile.getAbsolutePath());
                                shared_index.put(token, list);
                            }

                        }
                    }
                    //System.out.println(name + "release shared_index");
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
            }
            sharedFilesToIndex.notifyAll();
        }
 
    }
}
