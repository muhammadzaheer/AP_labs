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
    
    
    private static ServerSocket mainSock;
    private static ArrayList<Note> archive = new ArrayList<Note> ();
    
    public static void doArchive(Socket current, Note note) throws IOException {
        archive.add(note);
        DataOutputStream out = new DataOutputStream(current.getOutputStream());
        out.writeUTF("Archived Successfully");
    }
    
    public static void doRetrieve(Socket current, Note note) throws IOException{
        int flag = 0;
        for (Note a : archive){
            if (note.getUserName().equals(a.getUserName())){
                note.setText(a.getText());
                flag = 1;
                break;
            }
        }
        if (flag == 0)
            note.setText("No notes for the given username");
        ObjectOutputStream outObj = new ObjectOutputStream(current.getOutputStream());
        outObj.writeObject(note);
    }
    
    public static void main(String args[]) throws ClassNotFoundException {

        try {
            mainSock = new ServerSocket(60000);
            while (true) {
                
                Socket current = mainSock.accept();
                System.out.println("Connected to " + current.getRemoteSocketAddress());
                
                ObjectInputStream in = new ObjectInputStream(current.getInputStream());
                Note note = (Note)in.readObject();
                
                if (note.getOperation() == 0) {
                    doArchive(current, note);
                }
                else if (note.getOperation() == 1){
                    doRetrieve(current, note);
                }
                else {System.out.println("We're screwed");}
                
                current.close();
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

    }

}
