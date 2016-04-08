/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nust.spidermaven;
import java.io.File;
/**
 * Provides basic unit tests of Spider API
 * Tests: Search with regex as well as Attributes
 * @author smzaheerabbas
 */
public class Test {
    
    /**
     *  Provides basic unit tests of Spider API
     * Tests: Search with regex as well as Attributes
     * @param args
     */

    public static void main(String[] args) {

        Spider s = new Spider();
        // Provide absolute path
        File root = new File ("/Users/smzaheerabbas/NetBeansProjects/Lab4/test");
        s.index(root);
        
        System.out.println("Test: Searching directory");
        s.search("nested");

        System.out.println("\nTest: Searching file name");
        s.search("aunn.txt");
        
        System.out.println("\nTest: Searching content");
        s.search("name");
        
        
        /*
            
        
        */
        System.out.println("\nTest: Searching regex");
        s.searchRegex("^na");
        
        System.out.println("\nTest: File Size");
        s.searchSize(17, 19);
        
        System.out.println("\nTest: Search sub items");
        // SubItems counts the hidden files as well
        s.searchSubItems(3,10);
        
        System.out.println("\nTest: Last Modified (long)");
        long low = 1457071630000L;
        long high = 1457071630005L;
        s.searchLastModified(low, high);
        
        
        
        
        
        
    }

}
