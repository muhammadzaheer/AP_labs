/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nust.spidermaven;

/**
 *  Pair class encapsulates a number with String for searching over attributes
 * @author smzaheerabbas
 */
public class Pair {
    
    public double number;

    public String name;
    
    /**
     *
     * @param number
     * @param name
     */
    public Pair (double number, String name) {
    
        this.number = number;
        this.name = name;
    
    }
}
