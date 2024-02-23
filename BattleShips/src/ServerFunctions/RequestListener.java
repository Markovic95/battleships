/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServerFunctions;

import GameFrame.MultiePlayerMainFrame;
import GameFrame.RequestListener.StartGameMultie;
import LoginFrame.StartUpMenu;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.Clock;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/** extends Thread and listens for data to be send to the server for
 * communications with other players
 *
 * @author Dalibor Markovic
 */
public class RequestListener extends Thread {

    StartUpMenu startmenu;
    //////////////////////////////////////
    BufferedReader br;
    Socket socket = null;
    PrintWriter pw = null;
    String request;
    String data;
    boolean run = true;
    public RequestListener(StartUpMenu start, BufferedReader br1) {
        this.startmenu = start;
        this.br = br1;
    }

    @Override
    public void run() {
        
        while (run) {
            try {
                ArrayList<String> users = new ArrayList<>();
                String ServerRespose = br.readLine();
                String[] datafromserver = ServerRespose.split(":");

                data = datafromserver[0];
                request = datafromserver[1];
                //System.out.println(datafromserver[0] + " " + datafromserver[1]);
                if (ServerRespose.contains("exit")) {
                    doExit();

                } else if (ServerRespose.contains("refresh")) {
                    dorefresh(users, data);
                    
                
                /**
                 * Ο σερβερ μας γυριζει ενα ετοιμα και μας ξανα ρωταει εαν τον παιχτη που επιλεξαμε θελουμε να τον προκαλεσουμε σε μαχη!*/
                
                } else if (request.equals("playerfound")) {
                    PlayerFound();
                    
                    /**
                     * Methodos Challenge οταν μας ερθει request απο τον παιχτη που μας κανει challenge!!*/
                } else if (request.equals("challenge")) {
                    challenge(datafromserver[0]);
                 
                    
                    /*
                    Μετα που εκτελειται η μεθοδος challenge και στειλει στον σερβερ το αιτημα ωστε να βρεθει ποιος καλει
                    τον χρηστη σε παιχνιδι εκτελειται η παρακατω μεθοδος στον χρηστη που εκανε το αιτημα!!
                    */
                }else if(request.equals("challengeAccepted")){
                
                    ChallengeAccepted(datafromserver[0]);
                    System.out.println("Challenge Accepted info check : " + datafromserver[0]);
                
                }else if(request.equals("challengcanceled")){
                
                    JOptionPane.showMessageDialog(startmenu, "USERS CANCELED THE REQUEST");
                
                }

            } catch (IOException e) {
                System.out.println("ERRORRR DURING TRANSMISION FROM SERVER TO CLIENT");
            }

        }

    }
    
    /** ChallengeAccepted method used at the final stage prior to init the game session server
     * 
     * */
    private void ChallengeAccepted(String par) throws IOException{
        String GameSessionServerPort = par;
        System.out.println(GameSessionServerPort);
       
        
       
        MultiePlayerMainFrame multiePlayerMainFrame = new MultiePlayerMainFrame(startmenu, GameSessionServerPort);
        
        StartGameMultie newgame  = new StartGameMultie(startmenu,multiePlayerMainFrame);
        ActionEvent e = null;
        newgame.actionPerformed(e);
        
    
    }
    private void doExit() {
        
        run = false;
        startmenu.dispose();

    }

    private void dorefresh(ArrayList<String> users, String data) {
        users.add(data);
        startmenu.PlayersList.add(data);
        removedouplicates();
    }

    private void PlayerFound() {
        String[] opt = new String[2];
        opt[0] = "NO";
        opt[1] = "YES";
        int select = JOptionPane.showOptionDialog(startmenu,
                "PLAYER FOUND IN DATABASE || CHALLENGE PLAYER NOW?",
                "GAME REQUEST!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opt,
                opt[1]
        );

        switch (select) {
            case 1 -> {
                String choice = "YES";
                startmenu.sentdata(choice);

                break;
            }

            case 2 -> {
                String choice = "NO";
                startmenu.sentdata(choice);
                break;

            }
            default -> {

                String choice = "NO";
                startmenu.sentdata(choice);

            }

        }

    }
    /** Send and waits for a reply from the other player if the challenge is accepted of not and notifies the user that made the call
     * 
     * 
    */
    private void challenge(String challenger) throws IOException {
        String[] opt = new String[2];
        opt[0] = "NO";
        opt[1] = "YES";
        int select = JOptionPane.showOptionDialog(startmenu,
                "YOU ARE BEING CHALLENGED BY " + challenger,
                "GAME REQUEST",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opt,
                opt[1]
        );
         switch (select) {
            case 1 -> {
                String choice = ":findchallenger";
                String msg = challenger;
                startmenu.sentdata(msg+choice);
                break;
            }

            case 2 -> {
                String choice = ":NO";
                String msg = challenger;
                startmenu.sentdata(msg+choice);
                break;

            }
            default -> {
                String msg = challenger;
                String choice = ":NO";
                startmenu.sentdata(msg+choice);

            }

        }
    }

    private void removedouplicates() {
        /**METHOD αφαιρει τα διπλοτυπα ονοματα απο την λιστα των ενεργων χρηστων*/
        int size = startmenu.PlayersList.getItemCount();

        for (int i = 0; i < size; i++) {

            for (int j = i + 1; j < size; j++) {
                if (startmenu.PlayersList.getItem(i).equals(startmenu.PlayersList.getItem(j))) {

                    System.out.println("duplicate");
                    startmenu.PlayersList.remove(j);
                    size--;

                } else {
                    /** παρα πολλα prints καθως η μεθοδος κανει ελεγχο για καθε στοιχειο της λιστας με ολα τα αλλα*/
                    System.out.println("No problem");
                }

            }

        }

    }

}
