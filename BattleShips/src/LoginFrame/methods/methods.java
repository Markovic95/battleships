/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginFrame.methods;

import GameFrame.SinglePlayerMainFrame;
import GameFrame.RequestListener.StartGameSingle;
import LoginFrame.StartUpMenu;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/** 
 *
 * @author Dalibor Markovic
 */
public class methods {

    StartUpMenu root;

    public methods(StartUpMenu rootPane) {
        this.root = rootPane;

    }

    public void loginButton() {
        if (root.SinglePlayerBox.isSelected()) {
            String[] opt = new String[2];
            opt[0] = "No i want to fight online";
            opt[1] = "Fight with Computer";
            String choise = (String) JOptionPane.showInputDialog(root, "Options", "Bot Fight", 2, null, opt, opt[1]);
            if (choise == null) {
                return;
            }
            if (choise.equals(opt[0])) {

                return;
            } else {
                SinglePlayerMainFrame siglePlayer = new SinglePlayerMainFrame();
                StartGameSingle newgame = new StartGameSingle(root,siglePlayer);
                ActionEvent e = null;
                newgame.actionPerformed(e);

            }
        } else {

            if (root.ServerIP == null) {
                JOptionPane.showMessageDialog(root, """
                                                Δωσε Server IP για δυνατοτητα Multie Player
                                                Settings --> Server Settings""", "IP", 2);
            } else {
                if (root.NameInputField.getText().equals("")) {
                    JOptionPane.showMessageDialog(root, "Δωσε Ονομα");
                } else {
                    String finalrequest = root.NameInputField.getText() + ":databaseinsert";
                    root.sentdata(finalrequest);
                    root.UserLabel.setText(root.NameInputField.getText());
                    root.NameInputField.setEditable(false);

                }

                root.NameInputField.setText("");

            }
        }
    }

    public void challenge() {
        if (root.SinglePlayerBox.isSelected()) {

            System.out.println("Single Player is selected");
            JOptionPane.showMessageDialog(root, "Single Player Mode");

        }

        if (root.MultiePlayerBox.isSelected()) {

            System.out.println("Multie Player is selected");
            //JOptionPane.showMessageDialog(root, "Multie Player Mode");
            if (root.PlayersList.getItemCount() == 0 || root.PlayersList.getItemCount() < 2) {

                JOptionPane.showMessageDialog(root, "Δεν υπάρχουν αρκετοί παίχτες online!");
            }
            /*Οταν πατηθει το κουμπι challenge τοτε θα στειλει στον  σερβερ ενα request με τα tag
                playersearch KAI ΣΤΟΝ ΣΕΡΒΕΡ ΘΑ ΕΚΤΕΛΕΣΤΕΙ Η ΜΕΘΟΔΟς SearchPlayerandNotify(); 
            ΟΠΟΥ ΘΑ ΒΡΕΙ ΤΟΝ τον παιχτη στην βαση και ο σερβερ θα στειλει μυνημα στον χρηστη που κανει το
            challenge και ταυτοχρονα ο σερβερ περιμενει την απαντηση για να επιβεβαιωση και εαν ΝΑΙ τοτε ο σερβερ στελνει μυνημα
            στον παιχτη που καλειτε να απαντησει εαν θελει να δεχτει την προκληση η ΟΧΙ!!
            */
            if (root.PlayersList.getItemCount() >= 2) {
                root.challengerTextField.setText(root.PlayersList.getSelectedItem());
                String request = ":playersearch";
                String name = root.PlayersList.getSelectedItem();
                String finalrequest = name + request;
                root.sentdata(finalrequest);
            } else {

            }

        }

    }

    public void refresh() {
        String data = "empty";
        String request = ":refresh";
        String finalrequest = data + request;
        root.sentdata(finalrequest);

    }

}
