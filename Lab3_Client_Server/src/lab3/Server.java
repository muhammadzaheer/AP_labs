/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

/**
 *
 * @author smzaheerabbas
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    
    
    private ServerSocket mainSock;
    private ArrayList<Note> archive = new ArrayList<Note> ();
    
    public void doArchive(Socket current, Note note) throws IOException {
        archive.add(note);
        DataOutputStream out = new DataOutputStream(current.getOutputStream());
        out.writeUTF("Archived Successfully");
    }
    
    public void doRetrieve(Socket current, Note note) throws IOException{
        ObjectOutputStream outObj = new ObjectOutputStream(current.getOutputStream());
        for (Note a : archive){
            if (note.getUserName().equals(a.getUserName())){
                outObj.writeObject(a);
            }
        }
        note = new Note(0,note.getUserName(),"");
        outObj.writeObject(note);
        outObj.close();
    }
    
    public Server() throws ClassNotFoundException{
        try {
            mainSock = new ServerSocket(60000);
            while (true) {
                
                Socket current = mainSock.accept();
                //System.out.println("Connected to " + current.getRemoteSocketAddress());
                
                ObjectInputStream in = new ObjectInputStream(current.getInputStream());
                Note note = (Note)in.readObject();
                
                if (note.getOperation() == 0) {
                    doArchive(current, note);
                }
                else if (note.getOperation() == 1){
                    doRetrieve(current, note);
                }
                else {//System.out.println("We're screwed");
                    
                }
                
                current.close();
            }
        } catch (IOException e) {
            //e.printStackTrace(System.out);
        }

    }
    
    public static void main(String args[]) throws ClassNotFoundException {

            Server s = new Server();
    }

}
