/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Listeners;

import LoginFrame.StartUpMenu;
import LoginFrame.methods.methods;
import ServerFunctions.SentToServer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Implements ActionListenr for the Menu frame 
 * 
 * @author Dalibor Markovic
 */
public class ButtonsListeners implements ActionListener {

    StartUpMenu start;
    methods methods;
    SentToServer send;
    public ButtonsListeners(StartUpMenu aThis) {
        
        this.start = aThis;
        methods = new methods(start);
       
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
        if (e.getActionCommand().equals(start.loginbutton.getActionCommand())) {
            System.out.println("Login method called");
            
            methods.loginButton();

        }
        if (e.getActionCommand().equals(start.ChallengeButton.getActionCommand())) {
            System.out.println("Challenge method called");
            methods.challenge();

        }
        if(e.getActionCommand().equals(start.RefreshButton.getActionCommand())){
            
            System.out.println("Refresh method called");
            methods.refresh();
        
        }
        

    }

}
