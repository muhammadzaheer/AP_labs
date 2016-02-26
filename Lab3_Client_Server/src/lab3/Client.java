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
import java.util.Scanner;

public class Client {

    public static void archive(String serverName, int port) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the username: ");
        String username = input.nextLine();
        System.out.println("Enter the text: ");
        String text = input.nextLine();

        Note note = new Note(0, username, text);

        try {
            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outToServer);

            out.writeObject(note);

            InputStream inFromServer = client.getInputStream();
            DataInputStream in
                    = new DataInputStream(inFromServer);
            System.out.println(in.readUTF());
            /*
            Note rcvNote = new Note(1, "zaheer");
            out.writeObject(rcvNote);

            ObjectInputStream inObj = new ObjectInputStream(client.getInputStream());
            rcvNote = (Note) inObj.readObject();
            System.out.println(rcvNote);*/
            client.close();

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void retrieve(String serverName, int port) throws ClassNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the username: ");
        String username = input.nextLine();

        Note note = new Note(1, username);

        try {
            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outToServer);

            out.writeObject(note);

            ObjectInputStream inObj = new ObjectInputStream(client.getInputStream());
            note = (Note) inObj.readObject();
            System.out.println(note);
            client.close();

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        String serverName = "127.0.0.1";
        int port = 60000;

        Scanner in = new Scanner(System.in);
        char q = 'z';
        while (q != 'q') {
            System.out.println("Enter 0 for Archive and 1 for Retrieve");
            int operation = Integer.parseInt(in.nextLine());
            if (operation == 0) {
                archive(serverName, port);
            } else {
                retrieve(serverName, port);
            }
            System.out.println("Press 'q' to quit or any other key to continue...");
            q = in.nextLine().charAt(0);
        }

    }
}
