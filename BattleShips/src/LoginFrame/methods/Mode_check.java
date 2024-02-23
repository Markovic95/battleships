/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginFrame.methods;

import LoginFrame.StartUpMenu;
import java.awt.Color;

/** Mode_check 
 *
 * @author Dalibor Markovic
 */
public class Mode_check {

    StartUpMenu start;

    public Mode_check(StartUpMenu start) {
        this.start = start;

    }

    public void SinglePlayer() {

        if (start.SinglePlayerBox.isSelected()) {
            System.out.println("Selected");
            start.MultiePlayerBox.setEnabled(false);
            start.ChallengeButton.setEnabled(false);
            start.PlayersList.setBackground(Color.BLACK);
            start.PlayersList.setEnabled(false);

        } else if (!start.SinglePlayerBox.isSelected()) {
            System.out.println("Unselected");
            start.PlayersList.setBackground(Color.white);
            start.PlayersList.setEnabled(true);
            start.MultiePlayerBox.setEnabled(true);
           

        }

    }

    public void MultiePlayer() {
        if (start.MultiePlayerBox.isSelected()) {
            System.out.println("Selected");
            start.PlayersList.setBackground(Color.white);
            start.PlayersList.setEnabled(true);
            start.SinglePlayerBox.setEnabled(false);
             start.ChallengeButton.setEnabled(true);
        } else if (!start.MultiePlayerBox.isSelected()) {
            System.out.println("Unselected");
            start.ChallengeButton.setEnabled(false);
            start.SinglePlayerBox.setEnabled(true);
        }

    }

}
