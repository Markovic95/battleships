/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servers;

import SessionServer.Isactive;
import SessionServer.SessionServer;
import database.Database;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/** ThreadedClient Class that binds for each connections/client connected to the server
 *
 * @author Dalibor Markovic
 */
public class ThreadedClient extends Thread {

    Socket clientsocket;
    PrintWriter P;
    BufferedReader br;
    String requestId;
    boolean run = true;
    int index;
    //#################
    ArrayList<Socket> cl;
    Database database;
    int ptr = 0;
    int sesport;
    boolean sessionServisactive = true;
    public ThreadedClient(Socket clientSocket, Database base, int index, ArrayList<Socket> clients, int sessioport) throws IOException {
        //Client <--> Server
        //#############################################
        this.clientsocket = clientSocket;
        this.cl = clients;
        this.index = index;
        this.database = base;
        this.sesport = sessioport;
        
        P = new PrintWriter(clientsocket.getOutputStream());
        br = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));

        // Server <--> Server
        //#############################################
    }

    @Override
    public void run() {
        System.out.println("Thread OK");
        try {
            while (run) {

                String DataRecieved = br.readLine();
                System.out.println(DataRecieved);
                String[] tableofcontent = DataRecieved.split(":");
                requestId = tableofcontent[1];
                System.out.println(tableofcontent[0] + " " + tableofcontent[1]);
                switch (requestId) {
                    case "1" ->
                        System.out.println(tableofcontent[0]);
                    case "e" -> {
                        exit();

                    }
                    case "databaseinsert" -> {
                        insert(tableofcontent[0]);

                    }
                    case "refresh" -> {
                        refresh();

                    }
                    /*
                    απο κουμπι challenge καλειται η παρακατω μεθοδος
                     */
                    case "playersearch" -> {
                        SearchPlayerandNotify(tableofcontent[0]);

                    }
                    case "findchallenger" -> {
                        String challengertofind = tableofcontent[0];
                        findChallenger(challengertofind);

                    }
                    case "NO" -> {
                        String msg = tableofcontent[0];
                        canceled(msg);

                    }

                    default -> {
                        System.out.println("NO CASE");
                    }
                }

            }
        } catch (IOException ex) {

            System.out.println(ex + " ARRAYLIST OF SOCKETS " + cl + "\n" + " DATABASE + " + database.getHash());
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadedClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("TOTAL EXIT");

    }

    private void canceled(String par) throws IOException {
        String findchallenger = par;
        Object key = null;
        Object val = null;
        HashMap<String, Socket> temphashmap = database.getHash();
        System.out.println(par);
        for (Map.Entry<String, Socket> entry : temphashmap.entrySet()) {
            
            if (findchallenger.equals(entry.getKey())) {
                key = entry.getKey();
                val = entry.getValue();

            }

        }
        System.out.println(val);
         Socket ss = (Socket) val;
        PrintWriter P2;
        P2 = new PrintWriter(ss.getOutputStream());
        String request = ":challengcanceled";
        String data = " ";

        String tempfinalrequest = data + request;
        P2.println(tempfinalrequest);
        P2.flush();

    }

    private void findChallenger(String challengertofind) throws IOException, InterruptedException {

         ptr += 1;
        System.out.println("PTR IN CLIENT THREAD " + ptr);
        setGamecnt(ptr);

        System.out.println("FindChallenger ok");
        String finalrequest = null;
        Object key = null;
        Object val = null;
        String myname = "";
        Socket mysocket = null;
        String nametosearch = challengertofind;
        HashMap<String, Socket> temphashmap2 = database.getHash();

        for (Map.Entry<String, Socket> entry : temphashmap2.entrySet()) {
            if (entry.getValue().equals(clientsocket)) {

                myname = entry.getKey();
                mysocket = entry.getValue();
            }
            if (nametosearch.equals(entry.getKey())) {
                key = entry.getKey();
                val = entry.getValue();

            }

        }
        //στελνει μυνημα πισω στον καλουντα για να ενημερωση οτι το η προκληση εγινε δεκτη

        Socket ss = (Socket) val;
        PrintWriter P2;
        P2 = new PrintWriter(ss.getOutputStream());

        String request = ":challengeAccepted";
        String data = String.valueOf(sesport);

        String tempfinalrequest = data + request;
        P2.println(tempfinalrequest);
        P2.flush();
        System.out.println("Accept info sent!!");
        //
        P.println(tempfinalrequest);
        P.flush();

        /*SOS SOS SOS 
        
        οταν γινει δεκτη η προκληση ΤΟΤΕ!!! ο threadedClient(child of server) φτιαχνει ενα Game GameSessionServer για τους 2 παιχτες με 2 threads 
        ΤΟ Τ1 για τον user1 και Τ2 για τον user2!
         */
        /*
        Sessio port for each client starts the same, this must be changed in order to prevent server socket timeout errors
        by providing to the database to session port currently occupaied we can prevent that
        */
        
        Isactive active  = new Isactive();
        active.setBool(true);
        SessionServer sessionsServer = new SessionServer(sessionServisactive,sesport,index,database,active);
        sessionsServer.start();
        sesport+=1;
        
    }

    private void exit() {
        System.out.println("Exit ok");

        Socket tempsoc = clientsocket;

        if (cl.contains(clientsocket)) {
            System.out.println("thread" + clientsocket);
            System.out.println("Exited");
            cl.remove(clientsocket);

        }
        updatedatabase(tempsoc);

        //##############################
        String empty = "0";
        String exit = ":exit";
        P.println(empty + exit);
        P.flush();

    }

    private void updatedatabase(Socket tempsoc) {
        HashMap<String, Socket> usertodelete = database.getHash();
        Object key = null;
        for (Map.Entry<String, Socket> entry : usertodelete.entrySet()) {
            if (entry.getValue().equals(tempsoc)) {

                key = entry.getKey();

            } else if (key == null) {

                System.out.println("KEY IS NULL ABORDING!!");

            }
        }
        database.update(key);
        System.out.println("DATABASE UPDATED INFO " + database.getHash());
        run = false;

    }

    private void insert(String content) {
        System.out.println("DatabaseInsert ok");
        // patima login 1 fora mono // to prwto stoixeio einai to onoma to 2 to tag
        String usernamename = content;
        database.setHash(usernamename, clientsocket);
        System.out.println("DATABASE ON INSERT " + database.getHash());

    }

    private void refresh() {
        System.out.println("Refresh ok");
        HashMap<String, Socket> temphashmap = database.getHash();
        String responsetag = ":refresh";
        for (Map.Entry<String, Socket> entry : temphashmap.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            String finalrequest = key + responsetag;
            System.out.println(key);
            P.println(finalrequest);
            P.flush();

        }

    }

    private void SearchPlayerandNotify(String content) throws IOException {
        System.out.println("PlayerSearch ok");
        String finalrequest = null;
        Object key = null;
        Object val = null;
        String myname = "";
        String nametosearch = content;
        HashMap<String, Socket> temphashmap2 = database.getHash();
        /*βρισκει τον παιχτη στην βαση και ετοιμαζει ενα TAG playerfound για να στειλει πισω σε αυτον που την καλεσαι
        εαν θελει σιγουρα να κανει την προκληση στον παιχτη που βρηκε*/
        String responsetag = ":playerfound";
        for (Map.Entry<String, Socket> entry : temphashmap2.entrySet()) {
            if (entry.getValue().equals(clientsocket)) {

                myname = entry.getKey();
            }
            if (nametosearch.equals(entry.getKey())) {
                key = entry.getKey();
                val = entry.getValue();
                finalrequest = key + "|" + val + "|" + responsetag;

            }

        }
        System.out.println("TEST : " + finalrequest);
        P.println(finalrequest);
        P.flush();
        /*εδω ο σερβερ περιμενει αφου εχει στειλει το ετοιμα επιβεβαιωσεις στον χρηση
            εαν ο χρηστης πατησει Ναι τοτε ο σερβερ ΣΤΕΛΝΕΙ ΣΤΟΝ ΚΑΛΟΥΝΤΑ ΧΡΗΣΤΗ ΕΝΑ ΕΤΟΙΜΑ με TAG challenge 
        
         */
        String tempread = br.readLine();
        switch (tempread) {
            case "YES" -> {
                Socket ss = (Socket) val;
                PrintWriter P2;
                P2 = new PrintWriter(ss.getOutputStream());
                String request = ":challenge";
                String challengername = myname;
                String tempfinalrequest = challengername + request;
                P2.println(tempfinalrequest);
                P2.flush();
                System.out.println("challenge sent!!");

                break;
            }

            case "NO" -> {

                break;

            }
            default -> {

                break;
            }

        }

    }

    public void setGamecnt(int p) {
        ptr = p;
        database.setGameCounter(ptr);

    }

    public int getGamecnt() {

        return ptr;

    }
}
