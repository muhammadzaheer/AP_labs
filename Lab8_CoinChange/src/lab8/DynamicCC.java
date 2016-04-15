/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8;

import java.util.ArrayList;

/**
 *
 * @author smzaheerabbas
 */
public class DynamicCC {

    // An array of integers representing denominations
    private int[] dnm;

    public DynamicCC(int[] dnm) {
        this.dnm = dnm;
    }

    public ArrayList<Integer> getChange(int total) {

        // change ArrayList will hold the actual coins
        ArrayList<Integer> change = new ArrayList<Integer>();
        
        // c[i] will contain the optimal no of coins for i
        int c[] = new int[total + 1];
        
        // An array for keeping track of actual denominations for the change
        int coins[] = new int [total + 1];
        
        c[0] = 0;
        for (int i = 1; i <= total; i++) {
            // Initializing to max value
            c[i] = (int) Double.MAX_VALUE;
            
            // Checking all denomination
            for (int k = 0; k < dnm.length; k++) {
                // Update rule
                if (i >= dnm[k] && ((1 + c[i - dnm[k]]) < c[i])) {

                    c[i] = 1 + c[i - dnm[k]];
                    // Keeping track of denomination
                    coins[i] = dnm[k];
                }

            }

        }
        
        // Extracting the actual coins needed to make the change by backtracking
        while (total!= 0) {
            change.add(coins[total]);
            total-= coins[total];
        }
        
        return change;
    }

}
