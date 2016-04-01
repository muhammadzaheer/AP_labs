/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;
import java.io.File;
/**
 *
 * @author smzaheerabbas
 */
public class Test {

    public static void main(String[] args) {

        Spider s = new Spider();
        
        File root = new File ("/Users/smzaheerabbas/NetBeansProjects/Lab6/test");
        s.index(root);
        
        
        System.out.println("Test persistence");
        s.persistIndex("index");
        System.out.println();
        
        
        System.out.println("Test: Searching directory");
        s.search("1");
        System.out.println();
        s.search("2");
        System.out.println();
        s.search("3");
        System.out.println();
        
        System.out.println("\nTest: Searching file name");
        s.search("5.txt");
        
        System.out.println("\nTest: Searching content");
        s.search("name");
        
    }

}
