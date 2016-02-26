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

    public void archive(String serverName, int port) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the username: ");
        String username = input.nextLine();
        System.out.println("Enter the text: ");
        String text = input.nextLine();

        doArchive(serverName, port, username, text);
    }

    public void doArchive(String serverName, int port, String username, String text) {
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
            client.close();

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public void retrieve(String serverName, int port) throws ClassNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the username: ");
        String username = input.nextLine();
        doRetrieve(serverName, port, username);
    }

    public void doRetrieve(String serverName, int port, String username) throws ClassNotFoundException{
        Note note = new Note(1, username);

        try {
            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(outToServer);

            out.writeObject(note);

            ObjectInputStream inObj = new ObjectInputStream(client.getInputStream());
            while (true) {
                note = (Note) inObj.readObject();
                if (note.getText().equals("")) {
                    System.out.println("Retrieval done");
                    break;
                }
                System.out.println(note.getText());
            }
            client.close();

        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public Client(String test) throws ClassNotFoundException {
        String serverName = "127.0.0.1";
        int port = 60000;
        doArchive(serverName, port, "zaheer", "zaheer's text");
        doRetrieve(serverName, port, "zaheer");

    }

    public Client() throws ClassNotFoundException {
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
            System.out.println("Enter 'q' to quit or any other key to continue...");
            q = in.nextLine().charAt(0);
        }

    }

    public static void main(String[] args) throws ClassNotFoundException {

        Client c = new Client();

    }
}
