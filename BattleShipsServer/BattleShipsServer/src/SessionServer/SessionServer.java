/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SessionServer;

import Servers.ThreadedClient;
import database.Database;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;

/**
 * SessionServer class extends thread and is created for each new game is made
 * and holds 2 thread/clients in its database
 *
 * @author Dalibor Markovic
 */
public class SessionServer extends Thread {

    ServerSocket serverSocket = null;
    ArrayList<Socket> clients = new ArrayList<>();
    Database databse;
    int sesPort;
    int backlog;
    boolean run;
    Isactive active;
    Socket connection_threaded_client;

    public SessionServer(boolean sessionServisactive, int sesport, int index, Database database, Isactive active) {
        this.sesPort = sesport;
        this.backlog = index;
        this.databse = database;
        this.run = sessionServisactive;
        this.active = active;
        setInstance();

    }

    private void setInstance() {
        if (serverSocket == null || serverSocket.isClosed()) {
            InetAddress address;
            try {
                address = InetAddress.getByName("192.168.0.147");
                serverSocket = new ServerSocket(sesPort, backlog, address);
                serverSocket.setReuseAddress(true);
               // serverSocket.bind(new InetSocketAddress(sesPort));
                System.out.println("Session Server server socker creation passed");
                serverSocket.setSoTimeout(20000);
            } catch (IOException ex) {
                Logger.getLogger(SessionServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void run() {
        System.out.println("Sessions Server at port " + sesPort);
        System.out.println("Session Server active value : " + active.getBool());
        while (active.getBool()) {
            try {

                //connection_threaded_client = null;
                connection_threaded_client = serverSocket.accept();

                //##########################################
                clients.add(connection_threaded_client);
                //##########################################

                SessionThreads sesThreads = new SessionThreads(connection_threaded_client, clients, databse, active);
                sesThreads.start();

                //################
                //################
            } catch (IOException e) {

                System.out.println(e.getCause());

            }

        }
        if (serverSocket != null) {
            try {
                serverSocket.close();
                //connection.close();
                run = false;
                databse.setisActive(run);
                System.out.println("Session Server Socket Status : " + serverSocket.isClosed());
            } catch (IOException ex) {
                Logger.getLogger(SessionServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}
