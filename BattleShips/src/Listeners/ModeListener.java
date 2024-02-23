/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listeners;

import LoginFrame.StartUpMenu;
import LoginFrame.methods.Mode_check;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Implements ActionListener for mode checking single or multi player
 * 
 * @author Dalibor Markovic
 */
public class ModeListener implements ActionListener {

    StartUpMenu start;
    Mode_check mode;
    public ModeListener(StartUpMenu aThis) {
        this.start = aThis;
        mode = new Mode_check(start);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(start.SinglePlayerBox.getActionCommand())) {

            System.out.println("Single");
            mode.SinglePlayer();
            

        } else if (e.getActionCommand().equals(start.MultiePlayerBox.getActionCommand())) {

            System.out.println("Multie");
            mode.MultiePlayer();
        }

    }

}
