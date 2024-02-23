/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SessionServer;

import Servers.ThreadedClient;
import database.Database;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/** SessionThread class corresponds to each client connected to the SessionServer 
 *
 * @author Dalibor Markovic
 */
public class SessionThreads extends Thread {

    ArrayList<Socket> clientssocs;
    Socket conns;
    Database db;
    PrintWriter P;
    BufferedReader br;
    Isactive active;
    PrintWriter enemyP;
    
    public SessionThreads(Socket connections, ArrayList<Socket> clients, Database databse, Isactive active) throws IOException {
        this.active = active;
        this.clientssocs = clients;
        this.conns = connections;
        this.db = databse;
        P = new PrintWriter(conns.getOutputStream());
        br = new BufferedReader(new InputStreamReader(conns.getInputStream()));

    }

    @Override
    public void run() {

       
        boolean run = true;
        System.out.println("Thread OK");
        try {
            while (active.getBool()) {

                String DataRecieved = br.readLine();
                System.out.println(DataRecieved);
                String[] tableofcontent = DataRecieved.split(":");
                String data = tableofcontent[0];
                String requestId = tableofcontent[1];
                System.out.println(tableofcontent[0] + " " + tableofcontent[1]);
                switch (requestId) {
                    case "findenemy" -> {
                        System.out.println("MY SOCKET" + conns);
                        for (Socket s : clientssocs) {
                            if (conns != s) {
                               
                                    enemyP = new PrintWriter(s.getOutputStream());
                                    System.out.println("ENEMY SOCKET " + s);
                                    

                            }
                        }
                        System.out.println("MY ENEMY IS " + enemyP);

                    }

                    case "SESSIONEXIT" -> {
                        System.out.println("CASE SESSIONNEXIT OK");
                        String exitcode = "1" + ":EXITSESSIONTHREAD";
                        
                        P.println(exitcode);
                        P.flush();
                        
                        
                        try {
                            br.close();
                            P.close();
                        } catch (IOException ex1) {
                            Logger.getLogger(SessionThreads.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                        active.setBool(false);
                        System.out.println("SetBool : " + active.getBool());
                        run = false;
                        

                    }
                    case "SEND" -> {
                        System.out.println(tableofcontent[0] + tableofcontent[1] + "Thread" + this.getName() + "AT PORT AND IP " + conns.getPort() + " " + conns.getInetAddress());

                    }
                    case "shot" ->{
                        
                        String buttonshot = data + ":shotat";
                        enemyP.println(buttonshot);
                        enemyP.flush();
                    
                    }
                    
                    case "shiphit"->{
                    
                        String temp = data;
                        String color = tableofcontent[2];
                        String request = ":shiphitpriview";
                        String finals = temp+request+":"+color;
                        enemyP.println(finals);
                        enemyP.flush();
                    
                    }
                    case "shipmiss"->{
                    
                        String temp = data;
                        String request = ":shipmisspriview";
                        String finals = temp+request;
                        enemyP.println(finals);
                        enemyP.flush();
                    
                    }

                }
            }
            System.out.println("EXITED WHILE LOOP IN SESSIONTHREADS FOR " + this.getName());

        } catch (IOException ex) {

            System.out.println(ex + " ARRAYLIST OF SOCKETS " + clientssocs);

        }
        System.out.println("Session threat exited");

    }

}
