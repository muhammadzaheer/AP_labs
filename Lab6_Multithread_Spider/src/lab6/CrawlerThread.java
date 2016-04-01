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
public class CrawlerThread implements Runnable {

    private String name;

    private File root;
    private HashMap<String, ArrayList<String>> shared_index;
    private Queue sharedFilesToIndex;

    public CrawlerThread(File root, HashMap<String, ArrayList<String>> shared_index, String name,
            Queue filesToIndex) {
        
        //System.out.println(root + " " + name);

        this.root = root;
        this.name = name;
        this.shared_index = shared_index;
        this.sharedFilesToIndex = filesToIndex;

    }

    public void run() {
        //System.out.println("In thread" + name + "got file:" + root);
        recursiveRead(root);
    
    }

    private void  recursiveRead(File troot) {
        //System.out.println("File: " + troot.getName() + " " + name);
        for (File file : troot.listFiles()) {
            //System.out.println("--" + file.getName() + "--");
            if (file.isDirectory()) {
                synchronized (shared_index) {
                    indexDirectory(file.getAbsolutePath());
                }
                recursiveRead(file);
            } else if (file.getName().toLowerCase().endsWith(".txt")) {
                synchronized (shared_index) {
                    indexDirectory(file.getAbsolutePath());
                }
                indexFile(file);

            } else {
                // Ignoring if extension is not .txt
            }
        }

    }

    private void indexDirectory(String directoryPath) {

        String[] tokens = directoryPath.split("/");

        for (String token : tokens) {
            if (shared_index.containsKey(token)) {

                ArrayList<String> list = shared_index.get(token);
                try {
                    shared_index.get(token).add(directoryPath);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("ArrayIndexOutOfBoundsException");
                    System.out.println(list);
                    System.exit(-1);
                }

            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(directoryPath);
                shared_index.put(token, list);
            }

        }
    }

    private void indexFile(File textFile) {

        synchronized (sharedFilesToIndex) {
            sharedFilesToIndex.add(textFile);
            sharedFilesToIndex.notifyAll();

        }
    }
}
