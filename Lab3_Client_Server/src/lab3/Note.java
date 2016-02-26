/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.Serializable;

public class Note implements Serializable{
    private int operation;
    private String username;
    private String text;
    
    public Note (int operation, String username, String text){
        this.operation = operation;
        this.username = username;
        this.text = text;
    }
    
    public Note (int operation ,String username) {
        this.operation = operation;
        this.username = username;
        
    }
    public String toString () {
        return this.username + " " + this.text;
    }
    
    public void setText (String text){
        this.text = text;
    }
    
    public int getOperation(){ return this.operation;}
    public String getUserName() { return this.username;}
    public String getText() { return this.text;}
    
}
