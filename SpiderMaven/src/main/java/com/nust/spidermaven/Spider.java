/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nust.spidermaven;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Spider has main logic for searching and indexing over a directory
 * @author smzaheerabbas
 */
public class Spider {

    private static File root;

    private HashMap<String, ArrayList<String>> index;
    private ArrayList<Pair> fileSizes;
    private ArrayList<Pair> subItems;
    private ArrayList<Pair> lastModified;

    public Spider() {
        index = new HashMap<String, ArrayList<String>>();
        fileSizes = new ArrayList<Pair>();
        subItems = new ArrayList<Pair>();
        lastModified = new ArrayList<Pair>();
    }

    public void index(File rootDirectory) {
        fileSizes.add(new Pair(rootDirectory.length(), rootDirectory.getAbsolutePath()));
        subItems.add(new Pair(rootDirectory.listFiles().length, rootDirectory.getAbsolutePath()));
        lastModified.add(new Pair(rootDirectory.lastModified(), rootDirectory.getAbsolutePath()));
        recursiveRead(rootDirectory);
    }

    private void recursiveRead(File root) {
        // file is a directory

        for (File file : root.listFiles()) {
            fileSizes.add(new Pair(file.length(), file.getAbsolutePath()));
            lastModified.add(new Pair(file.lastModified(), file.getAbsolutePath()));
            if (file.isDirectory()) {
                subItems.add(new Pair(file.listFiles().length, file.getAbsolutePath()));
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
            Set<String> unique = new HashSet<String>(list);
            for (String result : unique) {
                System.out.println(result);
            }
        } else {
            System.out.println("Keyword not found");
        }
    }

    public void searchRegex(String pattern) {
        Pattern r = Pattern.compile(pattern);
        boolean found = false;
        for (String key : index.keySet()) {

            Matcher m = r.matcher(key);

            if (m.find()) {
                found = true;
                ArrayList<String> list = index.get(key);
                Set<String> unique = new HashSet<String>(list);
                for (String result : unique) {
                    System.out.println(result);
                }

            }

        }

        if (!found) {
            System.out.println("Regex not found");
        }

    }

    // low and high inclusive
    public void searchSize(double low, double high) {

        for (Pair p : fileSizes) {
            if (p.number >= low && p.number <= high) {
                System.out.println(p.name + " : " + p.number + " bytes");
            }

        }

    }

    public void searchSubItems(int low, int high) {

        for (Pair p : subItems) {
            if (p.number >= low && p.number <= high) {
                System.out.println(p.name + " : " + p.number + " items");
            }
        }
    }
    
    public void searchLastModified(long low, long high) {
    
        for (Pair p: lastModified) {
            if (p.number >= low && p.number <= high) {
                System.out.println(p.name + " : " + p.number);
            }
        }
    }
}
