/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servers;

import database.Database;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/** Main Server Class that accepts connections and creating thread for each one
 *
 * @author Dalibor Markovic
 */
public class Server {

    Database S1database = new Database();

    ArrayList<Socket> clients = new ArrayList<>();
    ArrayList<Thread> threads = new ArrayList<>();
    int userindex = 0;
    int id = 0;
    int sessioport = 27015;
    int gameindex = 0;
    int port;
    int S1port = 4242;

    public Server() {
        this.port = S1port;

        ServerSocket serverSocket = null;

        try {
            InetAddress address = InetAddress.getByName("192.168.0.147");
            serverSocket = new ServerSocket(port, id, address);
        } catch (IOException ex) {
            System.err.println("Error" + ex);
        }

        while (true) {
            Socket connections = null;

            try {
                System.out.println("Server 1 at port " + port);
                connections = serverSocket.accept();

                //##########################################
                clients.add(connections);
                //##########################################
                if (gameindex >= S1database.getGameCounter()) {
                    
                    System.out.println("");
                } else if (gameindex < S1database.getGameCounter()) {
                    
                    System.out.println("New GameFound");
                    sessioport+=S1database.getGameCounter();
                } 

                

                ThreadedClient t = new ThreadedClient(connections, S1database, userindex, clients, sessioport);
                t.start();
                threads.add(t);

                //################
                ++userindex;
                ++id;
                //################
            } catch (IOException e) {
                System.err.println(e);
            }

        }
    }

}
