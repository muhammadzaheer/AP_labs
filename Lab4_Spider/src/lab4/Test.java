/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;
import java.io.File;
/**
 *
 * @author smzaheerabbas
 */
public class Test {

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
    }

}
