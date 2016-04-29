/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

/**
 *
 * @author smzaheerabbas
 */
public class Test {

    public static void main(String[] args) {

        Interpreter in = new Interpreter();

        System.out.println("TEST CASE - I");
        /* test.txt contents:
            Let x = 5;
            Let y = 20;
            Print y;
            x = x + 100;
            y = x;
            Print x;
            Print y;
         */
        in.readFile("test.txt");
        in.parseCodes();

        /* test2.txt contents:
            Let y = 0;  
            Let x = 5;
            Let y+x = 3;
            y+x = x;
            Print y+x;
        */
        System.out.println("\nTEST CASE - II");
        in.readFile("test2.txt");
        in.parseCodes();
        
        /* test3.txt contents:
            Let x = 0;
            Let y = 5;
            Print x;
            Print y;
            Let 0 = 5;
        */
        
        
        
        System.out.println("\nTEST CASE - III");
        in.readFile("test3.txt");
        in.parseCodes();

        
    }

}
