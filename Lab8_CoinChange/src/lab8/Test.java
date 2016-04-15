/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author smzaheerabbas
 */
public class Test {
    
   public static void main(String[] args) {
        
        GreedyCC gcc = new GreedyCC(new int []{1, 5,10, 25});
        DynamicCC dcc = new DynamicCC(new int[]{1,5, 10, 25});
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            
            // Generating a random number between (0, 100000)
            int total = rand.nextInt(100000);
            
            /*
                gcc.getChange(total) | dcc.getChange(total)
                
                returns an ArrayList of Integers the size of which represents the
                number of coins needed to make the change. 
                The elements of ArrayList are the actual coins needed to make the change
            
            */
            System.out.println("Total: " + total);
            System.out.println("Greedy:" + gcc.getChange(total).size());
            System.out.println("Dynamic: " + dcc.getChange(total).size());
            System.out.println();
        }
    }
}
