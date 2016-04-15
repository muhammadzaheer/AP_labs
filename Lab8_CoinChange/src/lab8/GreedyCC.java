/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author smzaheerabbas
 */
public class GreedyCC {
    
    // An array of integers representing denominations
    private int [] dnm;
    
    public GreedyCC( int [] dnm) {
        this.dnm = dnm;
    }
    
    
    public ArrayList <Integer> getChange (int total) {
    
        // Change will be represented by an ArrayList of coin
        ArrayList <Integer> change =  new ArrayList <Integer>();
        
        int curr = dnm.length - 1;
        while (total > 0) {
            
            if (total - dnm[curr] >= 0) {
                total-= dnm[curr];
                change.add(dnm[curr]);
                if (total == 0)     // Total has reached 0, return the change
                    return change;
            } else  { 
                // If current denomination negates the total, try a smaller denomination
                curr--;
            }
        }
        
        // No solution found
        System.out.println("No change found, Try add denomination 1");
        return null;
    }
    
}
