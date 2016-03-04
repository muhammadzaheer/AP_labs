/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.io.*;
import java.util.*;

/**
 *
 * @author smzaheerabbas
 */
public class Spider {

    private static File root;

    private HashMap<String, ArrayList<String>> index;

    public Spider() {
        index = new HashMap<String, ArrayList<String>>();
    }

    public void index(File rootDirectory) {
        recursiveRead(rootDirectory);
    }

    private void recursiveRead(File root) {
        // file is a directory

        for (File file : root.listFiles()) {
            // If file is a directory
            if (file.isDirectory()) {

                indexDirectory(file.getAbsolutePath());
                recursiveRead(file);
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

    private void indexFile(File textFile) {

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
    
    public void search(String keyword) {

        if (index.containsKey(keyword)) {
            ArrayList<String> list = index.get(keyword);
            Set <String> unique = new HashSet<String> (list);
            for (String result : unique) {
                System.out.println(result);
            }
        } else {
            System.out.println("Keyword not found");
        }
    }

}
