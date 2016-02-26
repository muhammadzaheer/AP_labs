/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.*;

/**
 *
 * @author smzaheerabbas
 */
public class TestApp {
    
    public static void main (String args[]) throws ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        
        System.setOut(ps);
        
        Client c = new Client ("test");
        System.setOut(old);
        if(baos.toString().equals("Archived Successfully\nzaheer's text\nRetrieval done\n"))
            System.out.println("Echo test pass");
        else
            System.out.println("Echo test fail");
    }
}
